package com.farmerhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.farmerhouse.models.User;
import com.farmerhouse.models.Village;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData extends AppCompatActivity {
    SearchableSpinner villages;
    String villageId;
    String phoneNumberString , passwordString;
    EditText fullnameView , addressView;
    List valuesArray = new ArrayList() ;
    Button uploadButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        if(getIntent().hasExtra("phone") && getIntent().hasExtra("password")){

            phoneNumberString= getIntent().getStringExtra("phone");
             passwordString = getIntent().getStringExtra("password");

//            Log.d("TAG", "onCreate: phone and pass" +phoneNumberString +" "+passwordString);
        }

        villages = findViewById(R.id.SearchableSpinner);
        uploadButton = findViewById(R.id.uploadView);
        fullnameView = findViewById(R.id.fullnameView);
        addressView = findViewById(R.id.addressView);

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
                Log.d("TAG", "sendData: reach send data and signup");

                final ProgressDialog dialog = ProgressDialog.show(UserData.this, "",
                        "Please wait...", true);


                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

//
                                Toast.makeText(UserData.this, "upload Successful",
                                        Toast.LENGTH_LONG).show();
                                Log.d("upload", response);



                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                dialog.dismiss();
                                Toast.makeText(UserData.this, "error", Toast.LENGTH_LONG).show();
                                Log.d("TAG", "onErrorResponse: "+error.getMessage().toString());


                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<>();
                        //    Map<String, String> params = new Hashtable<>();
                        String villageIdString = villageId;
                        String addressString = addressView.getText().toString();
                        String fullnameString = fullnameView.getText().toString();




                        params.put("fullname", fullnameString);
                        params.put("phone", phoneNumberString);
                        params.put("password", passwordString);
                        params.put("villageId", villageIdString);
                        params.put("address", addressString);

//                        params.put("token", MyPreferencesUtils.getUserToken(Signup.this));


                        Log.d("tag", params.toString());
                        return params;

                    }
                };
                {
                    int socketTimeout = 30000;
                    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                    stringRequest.setRetryPolicy(policy);
                    RequestQueue requestQueue = Volley.newRequestQueue(UserData.this);

                    requestQueue.add(stringRequest);
                }



            }
        });



    }
}