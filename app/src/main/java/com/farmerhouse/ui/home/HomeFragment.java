package com.farmerhouse.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.farmerhouse.Adapters.MessagesRecyclerViewAdapter;
import com.farmerhouse.Adapters.NewsRecyclerViewAdapter;
import com.farmerhouse.GsonRequest;
import com.farmerhouse.Inbox;
import com.farmerhouse.NetworkHelper;
import com.farmerhouse.R;
import com.farmerhouse.VolleySingleton;
import com.farmerhouse.databinding.FragmentHomeBinding;
import com.farmerhouse.models.Message;
import com.farmerhouse.models.New;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    RecyclerView newsRecycleView;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsRecycleView.setLayoutManager(layoutManager);
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_NEWS);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
//        params.put("userId", "2");
        GsonRequest<New[]> myGsonRequest = new GsonRequest<com.farmerhouse.models.New[]>(Request.Method.POST, url, com.farmerhouse.models.New[].class, null, params,
                new Response.Listener<com.farmerhouse.models.New[]>() {
                    @Override
                    public void onResponse(com.farmerhouse.models.New[] response) {
                        Log.d("TAG", "onResponse: "+response[0].getDate());
                        dialog.dismiss();
                        NewsRecyclerViewAdapter newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(Arrays.asList(response));
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