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
import com.uzarsif.Adapters.UnOfficialRecyclerViewAdapter;
import com.uzarsif.FarmerAkedActivity;
import com.uzarsif.GsonRequest;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class UnofficialFragment extends Fragment {
    EditText phoneNumberView , passwordView;
    Button addu;
    CountryCodePicker ccp;
    FirebaseAuth auth;
    TextView processText;
    private CountryCodePicker countryCodePicker;
    private ProgressDialog dialog;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    RecyclerView unOfficialRecyclerView;

    public UnofficialFragment() {
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
        return inflater.inflate(R.layout.fragment_unofficial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Bundle bundle = getArguments();
//        userId = bundle.getString("userId");

//        Toast.makeText(getActivity(), userId, Toast.LENGTH_SHORT).show();

//        postInProfileRecyclerView = view.findViewById(R.id.postInProfileRecyclerView);
        unOfficialRecyclerView = view.findViewById(R.id.unOfficialRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        unOfficialRecyclerView.setLayoutManager(layoutManager);
        addu = view.findViewById(R.id.addUn);
        FirebaseApp.initializeApp(getContext());
        auth = FirebaseAuth.getInstance();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userId = prefs.getString("userId", "");
        Log.d("tag", "onReselectItem: Unof" + userId);

        addu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), FarmerAkedActivity.class);
                startActivity(i);
            }
        });

        String role = prefs.getString("role", "");
        Log.d("tag", "role:bl unofficial " + role);
        if(role.contains("1")) {
            addu.setVisibility(View.GONE);
        }
        else{
            addu.setVisibility(View.VISIBLE);
        }


        getAkeds();



    }

    public void getAkeds(){




        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "",
                "Please wait...", true);
        String url = null;

        // volley
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String role = prefs.getString("role", "");
        if(role.contains("1")){
          url = NetworkHelper.getUrl(NetworkHelper.GET_MANDOUB_FARMER_AKEDS);
        }
        else{
         url = NetworkHelper.getUrl(NetworkHelper.GET_FARMER_AKEDS);
        }

//        Log.d("url", url.toString());


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
                            UnOfficialRecyclerViewAdapter unOfficialAkedRecyclerViewAdapter = new UnOfficialRecyclerViewAdapter(Arrays.asList(response));
                            unOfficialRecyclerView.setAdapter(unOfficialAkedRecyclerViewAdapter);
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