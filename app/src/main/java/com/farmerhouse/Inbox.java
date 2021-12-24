package com.farmerhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.farmerhouse.Adapters.MessagesRecyclerViewAdapter;
import com.farmerhouse.models.Message;

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
                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_USER_MESSAGES);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
        params.put("userId", "2");
        GsonRequest<Message[]> myGsonRequest = new GsonRequest<com.farmerhouse.models.Message[]>(Request.Method.POST, url, com.farmerhouse.models.Message[].class, null, params,
                new Response.Listener<com.farmerhouse.models.Message[]>() {
                    @Override
                    public void onResponse(com.farmerhouse.models.Message[] response) {
                        Log.d("TAG", "onResponse: " + response[0].getDate());
                        dialog.dismiss();
                        MessagesRecyclerViewAdapter messagesRecyclerViewAdapter = new MessagesRecyclerViewAdapter(Arrays.asList(response));
                        messagesRecyclerView.setAdapter(messagesRecyclerViewAdapter);
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
                Intent i = new Intent(Inbox.this,CreateMessage.class);
                startActivity(i);
            }
        });

    }
}