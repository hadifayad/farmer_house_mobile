package com.farmerhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.farmerhouse.Adapters.ChatsRecyclerViewAdapter;
import com.farmerhouse.models.Chat;
import com.farmerhouse.models.Message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Inbox extends AppCompatActivity {
RecyclerView messagesRecyclerView;
ImageView newMessageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);
        newMessageButton = findViewById(R.id.newMessageButton);


        ////setting messages recycler
        {
        LinearLayoutManager layoutManager = new LinearLayoutManager(Inbox.this);
        messagesRecyclerView.setLayoutManager(layoutManager);
        final ProgressDialog dialog = ProgressDialog.show(Inbox.this, "",
                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_USER_CHATS);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
        params.put("userId", "2");
        GsonRequest<Chat[]> myGsonRequest = new GsonRequest<com.farmerhouse.models.Chat[]>(Request.Method.POST, url, com.farmerhouse.models.Chat[].class, null, params,
                new Response.Listener<com.farmerhouse.models.Chat[]>() {
                    @Override
                    public void onResponse(com.farmerhouse.models.Chat[] response) {
//                        Log.d("TAG", "onResponse: " + response[0].getDate());
                        dialog.dismiss();
                        ChatsRecyclerViewAdapter chatsRecyclerViewAdapter = new ChatsRecyclerViewAdapter(Arrays.asList(response));
                        messagesRecyclerView.setAdapter(chatsRecyclerViewAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(Inbox.this).addToRequestQueue(myGsonRequest);
    }
        /////

        newMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = ProgressDialog.show(Inbox.this, "",
                        "Please wait...", true);
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Inbox.this);
                        String userId = prefs.getString("userId", "");
                        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_CREATE_CHAT);
                Log.d("TAG", "onClick: "+url);
                        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
//

                                        dialog.dismiss();
                                        Toast.makeText(Inbox.this, "new message",
                                                Toast.LENGTH_LONG).show();
                                        Log.d("upload", response);
                                        Intent i = new Intent(Inbox.this,CreateMessage.class);
                                        i.putExtra("chatId", response.toString());
                                        startActivity(i);
//                        sendToMain();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        dialog.dismiss();
                                        Toast.makeText(Inbox.this, "error", Toast.LENGTH_LONG).show();

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
                            RequestQueue requestQueue = Volley.newRequestQueue(Inbox.this);

                            requestQueue.add(stringRequest);
                        }
                    }
                });

            }



}