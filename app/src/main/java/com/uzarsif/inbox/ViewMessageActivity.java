package com.uzarsif.inbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.Adapters.DataRecyclerViewAdapter;
import com.uzarsif.GsonRequest;
import com.uzarsif.NetworkHelper;
import com.uzarsif.R;
import com.uzarsif.VolleySingleton;
import com.uzarsif.models.DataMessages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ViewMessageActivity extends AppCompatActivity {

    RecyclerView dataRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message2);

        Intent intent = getIntent();
        String chatId = intent.getStringExtra("chatId");


        dataRecyclerView = findViewById(R.id.dataRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ViewMessageActivity.this);
        dataRecyclerView.setLayoutManager(layoutManager);

        final ProgressDialog dialog = ProgressDialog.show(ViewMessageActivity.this, "",
                "الرجاء الإنتظار ...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_CHAT_DATA);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
        params.put("chatId", chatId);
        GsonRequest<DataMessages[]> myGsonRequest = new GsonRequest<DataMessages[]>(Request.Method.POST, url, DataMessages[].class, null, params,
                new Response.Listener<DataMessages[]>() {
                    @Override
                    public void onResponse(DataMessages[] response) {
//                                    Log.d("TAG", "onResponse: " + response[0].getDate());
                        dialog.dismiss();


                        DataRecyclerViewAdapter dataRecyclerViewAdapter = new DataRecyclerViewAdapter(Arrays.asList(response), chatId);
                        dataRecyclerView.setAdapter(dataRecyclerViewAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(ViewMessageActivity.this).addToRequestQueue(myGsonRequest);

    }
}