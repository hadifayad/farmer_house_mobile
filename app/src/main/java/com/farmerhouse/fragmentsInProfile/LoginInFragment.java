package com.farmerhouse.fragmentsInProfile;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.farmerhouse.GsonRequest;
import com.farmerhouse.MainActivity;
import com.farmerhouse.MyPreferencesUtils;
import com.farmerhouse.NetworkHelper;
import com.farmerhouse.R;
import com.farmerhouse.VolleySingleton;
import com.farmerhouse.models.User;
import com.farmerhouse.splash;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class LoginInFragment extends Fragment {
Button login;
EditText phone,password;
CountryCodePicker ccp;

    public LoginInFragment() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        login = view.findViewById(R.id.login);
        phone = view.findViewById(R.id.phone);
        password = view.findViewById(R.id.passwordView);
        ccp = view.findViewById(R.id.ccp);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent( getContext() , MainActivity.class);
//                startActivity(i);


                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                String token = prefs.getString("token", "");


                    String url = NetworkHelper.getUrl(NetworkHelper.ACTION_LOGIN_USER);
//

                    Map<String, String> params = new HashMap();
                    String usernamestring = phone.getText().toString();
                    String phone =ccp.getSelectedCountryCode()+usernamestring;
                Log.d("TAG", "onClick: "+phone);

                    String passwordstring = password.getText().toString();





                    params.put("phone", phone);

                    params.put("password", passwordstring);

//                params.put("role", role);
                    params.put("token", token);


                    Log.d("tag", params.toString());
                    GsonRequest<User> myGsonRequest = new GsonRequest<User>(Request.Method.POST, url, User.class, null, params,
                            response -> {

                                Log.d("TAG", "onClick: "+response.toString());

                                    SharedPreferences.Editor ed = prefs.edit();
                                    ed.putString("phone", response.getPhone());
//                                    ed.putString(KEY_PASSWORD, response.getPassword().toString());
                                    ed.putString("profile", response.getProfile_picture());

                                    ed.putString("fullname", response.getFullname().toString());
                                    ed.putString("userId", response.getId().toString());
                                    ed.putString("token", response.getToken().toString());

//                            ed.putString(KEY_TOKEN, response.getRole().toString());
//                            if (response.getLink() != null) {
//                                ed.putString(KEY_LINK, response.getLink().toString());
//                            }


                                    ed.commit();

                                    Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();


                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getContext(), "Incorrect Username or Password", Toast.LENGTH_LONG).show();
                                    // dialog.dismiss();
                                    NetworkHelper.handleError(error);
                                }
                            });
                    VolleySingleton.getInstance(getContext()).addToRequestQueue(myGsonRequest);


                }



        });
    }
}