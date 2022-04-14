package com.uzarsif.OkoodAnwa3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.Adapters.OfficialAkedRecyclerViewAdapter;
import com.uzarsif.GsonRequest;
import com.uzarsif.MandoobAkedActivity;
import com.uzarsif.NetworkHelper;
import com.uzarsif.R;
import com.uzarsif.VolleySingleton;
import com.uzarsif.models.MandoubAked;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OfficialFragment extends Fragment {
    EditText phoneNumberView , passwordView;
    Button add;
    CountryCodePicker ccp;
    FirebaseAuth auth;
    TextView processText;
    private CountryCodePicker countryCodePicker;
    private ProgressDialog dialog;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    RecyclerView officialAkedRecyclerView;

    public OfficialFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_official, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Bundle bundle = getArguments();
//        userId = bundle.getString("userId");

//        Toast.makeText(getActivity(), userId, Toast.LENGTH_SHORT).show();

//        postInProfileRecyclerView = view.findViewById(R.id.postInProfileRecyclerView);
        officialAkedRecyclerView = view.findViewById(R.id.officialAkedRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        officialAkedRecyclerView.setLayoutManager(layoutManager);
        add = view.findViewById(R.id.addOf);
        FirebaseApp.initializeApp(getContext());
        auth = FirebaseAuth.getInstance();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userId = prefs.getString("userId", "");
        Log.d("tag", "onReselectItem: off " + userId);

        String role = prefs.getString("role", "");
        Log.d("tag", "role:bl official " + role);
        if(role.contains("1")) {
            add.setVisibility(View.VISIBLE);
        }
        else{
            add.setVisibility(View.GONE);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MandoobAkedActivity.class);
                startActivity(i);
            }
        });

        getAkeds();



    }

    public void getAkeds(){




        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "Please wait...", true);
        String url;
        // volley

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String role = prefs.getString("role", "");
        if(role.contains("1")){

             url = NetworkHelper.getUrl(NetworkHelper.GET_MANDOUB_AKEDS);
        }
        else{
            url = NetworkHelper.getUrl(NetworkHelper.GET_FARMER_OFFICIAL_AKEDS);
        }




        String userId = prefs.getString("userId", "");
        if(!userId.equals(null) && !userId.equals("")){
            Map<String, String> params = new HashMap();
            params.put("mandoobId", userId);
            GsonRequest<MandoubAked[]> myGsonRequest = new GsonRequest<com.uzarsif.models.MandoubAked[]>(Request.Method.POST, url, com.uzarsif.models.MandoubAked[].class, null, params,
                    new Response.Listener<com.uzarsif.models.MandoubAked[]>() {
                        @Override
                        public void onResponse(com.uzarsif.models.MandoubAked[] response) {
//                        Log.d("TAG", "onResponse: "+response[0].getDate());
                            dialog.dismiss();
                            OfficialAkedRecyclerViewAdapter officialAkedRecyclerViewAdapter = new OfficialAkedRecyclerViewAdapter(Arrays.asList(response));
                            officialAkedRecyclerView.setAdapter(officialAkedRecyclerViewAdapter);
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