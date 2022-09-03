package com.uzarsif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.uzarsif.models.User;
import com.uzarsif.models.Village;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData extends AppCompatActivity {
    SearchableSpinner villages;
    String villageId;
    String phoneNumberString , passwordString;
    EditText fullnameView , addressView,secondPhone,email;
    List valuesArray = new ArrayList() ;
    Button uploadButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#81bb28"));

        if(getIntent().hasExtra("phone") && getIntent().hasExtra("password")){

            phoneNumberString= getIntent().getStringExtra("phone");
             passwordString = getIntent().getStringExtra("password");



//            Log.d("TAG", "onCreate: phone and pass" +phoneNumberString +" "+passwordString);
        }

        villages = findViewById(R.id.SearchableSpinner);
        uploadButton = findViewById(R.id.uploadView);
        fullnameView = findViewById(R.id.fullnameView);
        addressView = findViewById(R.id.addressView);
        email = findViewById(R.id.email);
        secondPhone = findViewById(R.id.secondPhone);

        /////////////////////////////////////////////
        //villages data
        {
            String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_VILLAGES);
            Log.d("url", url.toString());


//        testanim object=new testanim(AddPost.this);
//        object.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        object.show();

            Map<String, String> params1 = new HashMap();


            GsonRequest<Village[]> myGsonRequest1 = new GsonRequest<Village[]>(Request.Method.POST, url, Village[].class, null, params1,
                    new Response.Listener<Village[]>() {
                        @Override
                        public void onResponse(Village[] response) {

                            Log.d("TAG", "onResponse: " + response[0]);

                            String VillageArray[] = new String[response.length];

                            for (int i = 0; i < response.length; i++) {
                                VillageArray[i] = response[i].getName();
//                            valuesArray[i] = response[i].getId();
                                valuesArray.add(response[i].getId());
                            }


// Application of the Array to the Spinner
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(UserData.this, android.R.layout.simple_spinner_item, VillageArray);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view


                            villages.setAdapter(spinnerArrayAdapter);
//                        object.dismiss();


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

            VolleySingleton.getInstance(UserData.this).addToRequestQueue(myGsonRequest1);

        }


        villages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                villageId = valuesArray.get(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );

        ////////////////////////////////



        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String url = NetworkHelper.getUrl(NetworkHelper.ACTION_SIGNUP_USER);
                Log.d("TAG", "onClick: +"+url);
//

                Map<String, String> params = new HashMap();
                String villageIdString = villageId;
                String addressString = addressView.getText().toString();
                String fullnameString = fullnameView.getText().toString();
                String secondPhoneString = secondPhone.getText().toString();
                String emailString = email.getText().toString();




                params.put("fullname", fullnameString);
                params.put("phone", phoneNumberString);
                params.put("password", passwordString);
                params.put("villageId", villageIdString);
                params.put("address", addressString);
                params.put("email", emailString);
                params.put("secondPhone", secondPhoneString);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(UserData.this);
                String token = prefs.getString("token", "");

                params.put("token", token);

//                        params.put("token", MyPreferencesUtils.getUserToken(Signup.this));


                Log.d("tag", params.toString());



                Log.d("tag", params.toString());
                GsonRequest<User> myGsonRequest = new GsonRequest<User>(Request.Method.POST, url, User.class, null, params,
                        response -> {

                            if (response.getId() != null) {
                                Log.d("TAG", "onClick: " + response.toString());

                                SharedPreferences.Editor ed = prefs.edit();
                                ed.putString("phone", response.getPhone());
//                                    ed.putString(KEY_PASSWORD, response.getPassword().toString());
                                ed.putString("profile", response.getProfile_picture());

                                ed.putString("fullname", response.getFullname().toString());
                                ed.putString("userId", response.getId().toString());
                                ed.putString("token", response.getToken().toString());

                                ed.putString("mandoobId", response.getMandoobId().toString());


                                ed.putString("role", response.getUser_role().toString());

//                            ed.putString(KEY_TOKEN, response.getRole().toString());
//                            if (response.getLink() != null) {
//                                ed.putString(KEY_LINK, response.getLink().toString());
//                            }


                                ed.commit();

                                Toast.makeText(UserData.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(UserData.this, MainActivity.class);
                                startActivity(intent);
                               finish();

                            }
                            else {
                                Toast.makeText(UserData.this, "هذا الرقم مستخدم مسبقا", Toast.LENGTH_LONG).show();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(UserData.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                                // dialog.dismiss();
                                Toast.makeText(UserData.this, "حدث خطأ", Toast.LENGTH_LONG).show();

                                NetworkHelper.handleError(error);
                            }
                        });
                VolleySingleton.getInstance(UserData.this).addToRequestQueue(myGsonRequest);





                /////////////////////////


            }
        });



    }
}