package com.uzarsif;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.models.ProfileData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
ImageView editProfile;
    List valuesArray = new ArrayList() ;
    public String villageId , landVillageId;




    TextView fullnameView,secondPhone,email,addressView,villages,mandoob;
    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        villages =  view.findViewById(R.id.SearchableSpinner);



        addressView =  view.findViewById(R.id.addressView);

        fullnameView =  view.findViewById(R.id.fullnameView);
        secondPhone =  view.findViewById(R.id.secondPhone);

        email =  view.findViewById(R.id.email);
        mandoob =  view.findViewById(R.id.mandoob);
        editProfile = view.findViewById(R.id.editProfile);
        getProfileData();
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),EditProfileActivity.class);
                startActivity(i);
            }
        });
    }


    public void getProfileData(){

        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_PROFILE_DATA);
        Log.d("url", url.toString());


//        testanim object=new testanim(AddPost.this);
//        object.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        object.show();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userId = prefs.getString("userId", "");

        Map<String, String> params1 = new HashMap();
        params1.put("userId",userId);

        GsonRequest<ProfileData> myGsonRequest = new GsonRequest<ProfileData>(Request.Method.POST, url, ProfileData.class, null, params1,
                new Response.Listener<ProfileData>() {
                    @Override
                    public void onResponse(ProfileData response)





                    {
//                    Log.d("TAG", "onResponse: "+response.toString());
//                    villages = findViewById(R.id.SearchableSpinner);
//                    landVillage = findViewById(R.id.landVillage);
//                    landLegal = findViewById(R.id.landLegal);
//
//                    landWater = findViewById(R.id.landWater);
//                    landPond = findViewById(R.id.landPond);
//                    landPublic = findViewById(R.id.landPublic);
//                    landWell = findViewById(R.id.landWell);


                        addressView.setText(response.getAddress());

                        fullnameView.setText(response.getFullname());
                        secondPhone.setText(response.getSecond_phone());
                        email.setText(response.getEmail());
                        mandoob.setText(response.getMandoob());
                        villages.setText(response.getVillage());

//                        villageId =  response.getVillage();




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        object.dismiss();
                        NetworkHelper.handleError(error);
                        Log.d("error", "onErrorResponse: " + error.getMessage());
                    }
                });

        VolleySingleton.getInstance(getContext()).addToRequestQueue(myGsonRequest);
    }
    }