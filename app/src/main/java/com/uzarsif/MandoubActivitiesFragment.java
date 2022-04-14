package com.uzarsif;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.Adapters.MandoubActivitiesRecyclerViewAdapter;
import com.uzarsif.models.MandoubActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MandoubActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MandoubActivitiesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView mandoobActivitiesRecyclerView;
    Button addActivity;

    public MandoubActivitiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MandoubActivitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MandoubActivitiesFragment newInstance(String param1, String param2) {
        MandoubActivitiesFragment fragment = new MandoubActivitiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mandoub_activities, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mandoobActivitiesRecyclerView = view.findViewById(R.id.mandoobActivitiesRecyclerView);
        addActivity = view.findViewById(R.id.addActivity);
        Log.d("TAG", "onViewCreated: asdsda");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mandoobActivitiesRecyclerView.setLayoutManager(layoutManager);

        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext() ,MandoubNewActivity.class);
                startActivity(i);
            }
        });
        getActivities();
    }


    public void getActivities(){




        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.GET_MANDOUB_ACTIVTIES);
        Log.d("url", url.toString());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userId = prefs.getString("userId", "");
        if(!userId.equals(null) && !userId.equals("")){
            Map<String, String> params = new HashMap();
            params.put("mandoobId", userId);
            GsonRequest<MandoubActivity[]> myGsonRequest = new GsonRequest<com.uzarsif.models.MandoubActivity[]>(Request.Method.POST, url, com.uzarsif.models.MandoubActivity[].class, null, params,
                    new Response.Listener<com.uzarsif.models.MandoubActivity[]>() {
                        @Override
                        public void onResponse(com.uzarsif.models.MandoubActivity[] response) {
//                        Log.d("TAG", "onResponse: "+response[0].getDate());
                            dialog.dismiss();
                            MandoubActivitiesRecyclerViewAdapter mandoubActivitiesRecyclerViewAdapter = new MandoubActivitiesRecyclerViewAdapter(Arrays.asList(response));
                            mandoobActivitiesRecyclerView.setAdapter(mandoubActivitiesRecyclerViewAdapter);
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