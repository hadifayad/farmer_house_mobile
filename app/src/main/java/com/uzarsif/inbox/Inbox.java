package com.uzarsif.inbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.Adapters.ChatsRecyclerViewAdapter;
import com.uzarsif.CreateMessageOptions;
import com.uzarsif.GsonRequest;
import com.uzarsif.NetworkHelper;
import com.uzarsif.R;
import com.uzarsif.VolleySingleton;
import com.uzarsif.models.Chat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


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
                "الرجاء الإنتظار ...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_USER_CHATS);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
        params.put("userId", "2");
        GsonRequest<Chat[]> myGsonRequest = new GsonRequest<com.uzarsif.models.Chat[]>(Request.Method.POST, url, com.uzarsif.models.Chat[].class, null, params,
                new Response.Listener<com.uzarsif.models.Chat[]>() {
                    @Override
                    public void onResponse(com.uzarsif.models.Chat[] response) {
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

                Intent i = new Intent(Inbox.this, CreateMessageOptions.class);

                                        startActivity(i);

//                final ProgressDialog dialog = ProgressDialog.show(Inbox.this, "",
//                        "الرجاء الإنتظار ...", true);
//                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Inbox.this);
//                        String userId = prefs.getString("userId", "");
//                        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_CREATE_CHAT);
//                Log.d("TAG", "onClick: "+url);
//                        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                                new Response.Listener<String>() {
//                                    @Override
//                                    public void onResponse(String response) {
////
//
//                                        dialog.dismiss();
//                                        Toast.makeText(Inbox.this, "new message",
//                                                Toast.LENGTH_LONG).show();
//                                        Log.d("upload", response);
//                                        Intent i = new Intent(Inbox.this,CreateMessage.class);
//                                        i.putExtra("chatId", response.toString());
//                                        startActivity(i);
////                        sendToMain();
//                                    }
//                                },
//                                new Response.ErrorListener() {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//                                        dialog.dismiss();
//                                        Toast.makeText(Inbox.this, "error", Toast.LENGTH_LONG).show();
//
//                                    }
//                                }) {
//                            @Override
//                            protected Map<String, String> getParams() throws AuthFailureError {
//                                HashMap<String, String> params = new HashMap<>();
//                                //    Map<String, String> params = new Hashtable<>();
//
//
//                                params.put("userId", "2");
//                                Log.d("TAG", "getParams: "+params.toString());
//
//
//
//
//                                Log.d("tag", params.toString());
//                                return params;
//
//                            }
//                        };
//                        {
//                            int socketTimeout = 30000;
//                            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//                            stringRequest.setRetryPolicy(policy);
//                            RequestQueue requestQueue = Volley.newRequestQueue(Inbox.this);
//
//                            requestQueue.add(stringRequest);
//                        }
                    }
                });

            }



}