package com.uzarsif;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class splash extends AppCompatActivity {

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        startActivity(new Intent(this, CreateMessage.class));

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#FFFFFF"));
        start= findViewById(R.id.start);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(splash.this);
        String firstTime = prefs.getString("firstTime", "");
        String userId = prefs.getString("userId", "");


        Bundle bundle = getIntent().getExtras();
        if (bundle != null && getIntent().hasExtra("chatId")) {
            String chatId = bundle.getString("chatId");
//            Toast.makeText(splash.this, "chatId: "+chatId, Toast.LENGTH_SHORT).show();

            Intent k = new Intent(splash.this, ChatCommentsActivity.class);

            k.putExtra("chatId",chatId);

            Log.d("TAG", "onClick: +parent = "+k.toString());
        startActivity(k);


        }


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        SharedPreferences.Editor ed = prefs.edit();


                        ed.putString("token", token);

                        ed.commit();

                        if (!userId.equals("")) {

                            Log.d("TAG", "onComplete: "+token);



                        updateToken(userId, token);
                    }





//                        Toast.makeText(splash.this, token, Toast.LENGTH_SHORT).show();
                    }
                });



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TAG", "onClick: "+firstTime);

                if (!firstTime.equals(null) && !firstTime.equals("")) {

                    if (!userId.equals(null) && !userId.equals("")){

                        Intent intent1 = new Intent(splash.this, MainActivity.class);
                        intent1.putExtra("userId", "1");
                        startActivity(intent1);
                        finish();

                    }
                    else {
                        Intent intent1 = new Intent(splash.this, LoginOrSignup.class);

                        startActivity(intent1);
                        finish();
                    }

                }
                else {
                    Intent intent1 = new Intent(splash.this, Tour1.class);

                    startActivity(intent1);
                    finish();
                }


            }
        });
    }

    public void updateToken(String userId, String token){
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_UPDATE_TOKEN);
        Log.d("TAG", "sendData: reach send data and signup");

//        final ProgressDialog dialog = ProgressDialog.show(splash.this, "",
//                "Please wait...", true);


        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//dialog.dismiss();

                        Log.d("token", "success");




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        dialog.dismiss();
                        Toast.makeText(splash.this, "error", Toast.LENGTH_LONG).show();
                        Log.d("TAG", "onErrorResponse: "+error.getMessage().toString());


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                //    Map<String, String> params = new Hashtable<>();

                params.put("userId", userId);




                params.put("token", token);




                Log.d("tag", params.toString());
                return params;

            }
        };
        {
            int socketTimeout = 30000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
            RequestQueue requestQueue = Volley.newRequestQueue(splash.this);

            requestQueue.add(stringRequest);
        }

    }
}