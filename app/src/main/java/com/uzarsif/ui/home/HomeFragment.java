package com.uzarsif.ui.home;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.Adapters.NewsRecyclerViewAdapter;
import com.uzarsif.CircularProgressBar;
import com.uzarsif.GsonRequest;
import com.uzarsif.NetworkHelper;
import com.uzarsif.R;
import com.uzarsif.VolleySingleton;
import com.uzarsif.databinding.FragmentHomeBinding;
import com.uzarsif.models.New;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    RecyclerView newsRecycleView;
    CircularProgressBar circularProgressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsRecycleView = view.findViewById(R.id.newsRecyclerView);
        circularProgressBar = view.findViewById(R.id.circularProgress);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsRecycleView.setLayoutManager(layoutManager);
        circularProgressBar.setProgressColor(R.color.app_brown);
        circularProgressBar.setTextColor(R.color.app_green);

        circularProgressBar.setProgress(60);


        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "الرجاء الإنتظار ...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_NEWS);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
//        params.put("userId", "2");
        GsonRequest<New[]> myGsonRequest = new GsonRequest<com.uzarsif.models.New[]>(Request.Method.POST, url, com.uzarsif.models.New[].class, null, params,
                new Response.Listener<com.uzarsif.models.New[]>() {
                    @Override
                    public void onResponse(com.uzarsif.models.New[] response) {
                        Log.d("TAG", "onResponse: "+response[0].getDate());
                        dialog.dismiss();
                        NewsRecyclerViewAdapter newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(Arrays.asList(response),HomeFragment.this);
                        newsRecycleView.setAdapter(newsRecyclerViewAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(getContext()).addToRequestQueue(myGsonRequest);

    }

    }