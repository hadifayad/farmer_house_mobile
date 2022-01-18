package com.farmerhouse;

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
import com.farmerhouse.inbox.CreateMessage;

import java.util.HashMap;
import java.util.Map;

public class CreateMessageOptions extends AppCompatActivity {

    Button daleel,ansab,sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message_options);
        daleel = findViewById(R.id.daleel);
        ansab = findViewById(R.id.ansab);
//        sell = findViewById(R.id.sell);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#81bb28"));
       ansab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateMessageOptions.this, AnsabMazro3atActivity.class);
//                Intent i = new Intent(CreateMessageOptions.this, Ansab.class);

                startActivity(i);


            }});


        daleel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = ProgressDialog.show(CreateMessageOptions.this, "",
                        "Please wait...", true);
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(CreateMessageOptions.this);
                String userId = prefs.getString("userId", "");
                String url = NetworkHelper.getUrl(NetworkHelper.ACTION_CREATE_CHAT);
                Log.d("TAG", "onClick: "+url);
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//

                                dialog.dismiss();
                                Toast.makeText(CreateMessageOptions.this, "new message",
                                        Toast.LENGTH_LONG).show();
                                Log.d("upload", response);
                                Intent i = new Intent(CreateMessageOptions.this, CreateMessage.class);
                                i.putExtra("chatId", response.toString());
                                startActivity(i);
//                        sendToMain();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                dialog.dismiss();
                                Toast.makeText(CreateMessageOptions.this, "error", Toast.LENGTH_LONG).show();

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<>();
                        //    Map<String, String> params = new Hashtable<>();


                        params.put("userId", "2");
                        Log.d("TAG", "getParams: "+params.toString());




                        Log.d("tag", params.toString());
                        return params;

                    }
                };
                {
                    int socketTimeout = 30000;
                    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                    stringRequest.setRetryPolicy(policy);
                    RequestQueue requestQueue = Volley.newRequestQueue(CreateMessageOptions.this);

                    requestQueue.add(stringRequest);
                }
            }
        });

    }
}