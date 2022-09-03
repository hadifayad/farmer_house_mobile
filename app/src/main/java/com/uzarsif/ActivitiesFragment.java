package com.uzarsif;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.R;
import com.uzarsif.Adapters.ActivitiesRecyclerViewAdapter;
import com.uzarsif.models.UserActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView activitiesRecyclerView;

    public ActivitiesFragment() {
        // Required empty public constructor
    }


    public static ActivitiesFragment newInstance(String param1, String param2) {
        ActivitiesFragment fragment = new ActivitiesFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_activities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activitiesRecyclerView = view.findViewById(R.id.activitiesRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        activitiesRecyclerView.setLayoutManager(layoutManager);
        getActivities();
    }

    public void getActivities(){




        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "الرجاء الإنتظار ...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_USER_ACTIVITIES);
        Log.d("url", url.toString());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userId = prefs.getString("userId", "");
        if(!userId.equals(null) && !userId.equals("")){
            Map<String, String> params = new HashMap();
                    params.put("user_id", userId);
            GsonRequest<UserActivity[]> myGsonRequest = new GsonRequest<com.uzarsif.models.UserActivity[]>(Request.Method.POST, url, com.uzarsif.models.UserActivity[].class, null, params,
                    new Response.Listener<com.uzarsif.models.UserActivity[]>() {
                        @Override
                        public void onResponse(com.uzarsif.models.UserActivity[] response) {
//                        Log.d("TAG", "onResponse: "+response[0].getDate());
                            dialog.dismiss();
                            ActivitiesRecyclerViewAdapter activitiesRecyclerViewAdapter = new ActivitiesRecyclerViewAdapter(Arrays.asList(response));
                            activitiesRecyclerView.setAdapter(activitiesRecyclerViewAdapter);
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
        else{
            Toast.makeText(getContext(), "سجل حسابك لتابعة انشطتك", Toast.LENGTH_LONG).show();

        }

    }
}