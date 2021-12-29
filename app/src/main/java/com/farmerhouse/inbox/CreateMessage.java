package com.farmerhouse.inbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.farmerhouse.Adapters.DataRecyclerViewAdapter;
import com.farmerhouse.GsonRequest;
import com.farmerhouse.NetworkHelper;
import com.farmerhouse.R;
import com.farmerhouse.VolleySingleton;
import com.farmerhouse.models.Data;
import com.farmerhouse.models.DataMessages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateMessage extends AppCompatActivity {

    //    RelativeLayout aked, daleel;
    public RecyclerView dataRecyclerView;
//    RecyclerView topParentRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
//        Data akedData = new Data();
//        Data daleelData = new Data();
//        akedData.setId("2");
//        daleelData.setId("1");



        String   chatId = getIntent().getStringExtra("chatId");


//            Log.d("TAG", "onCreate: phone and pass" +phoneNumberString +" "+passwordString);


        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ffffff"));

//        aked = findViewById(R.id.aked);
//        daleel = findViewById(R.id.daleel);
//        topParentRecyclerView = findViewById(R.id.topParentRecyclerView);


        dataRecyclerView = findViewById(R.id.dataRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(CreateMessage.this);
        dataRecyclerView.setLayoutManager(layoutManager);
//        LinearLayoutManager layoutManagerw = new LinearLayoutManager(CreateMessage.this, LinearLayoutManager.HORIZONTAL, false);

//        topParentRecyclerView.setLayoutManager(layoutManagerw);

        //////top parent


        final ProgressDialog dialog = ProgressDialog.show(CreateMessage.this, "",
                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_TOP_PARENT);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
//        params.put("parentId", data.getId());
        GsonRequest<Data[]> myGsonRequest = new GsonRequest<com.farmerhouse.models.Data[]>(Request.Method.POST, url, Data[].class, null, params,
                new Response.Listener<com.farmerhouse.models.Data[]>() {
                    @Override
                    public void onResponse(Data[] response) {
//                                    Log.d("TAG", "onResponse: " + response[0].getDate());
                        dialog.dismiss();

                        DataMessages dataMessage = new DataMessages();
                        dataMessage.setData(Arrays.asList(response));

                        List<DataMessages> dataMessages = new ArrayList<>();
                        dataMessages.add(dataMessage);

                        DataRecyclerViewAdapter dataRecyclerViewAdapter = new DataRecyclerViewAdapter(dataMessages,chatId);
                        dataRecyclerView.setAdapter(dataRecyclerViewAdapter);
//                        TopParentRecyclerView topParentRecyclerViewAdapter = new TopParentRecyclerView(Arrays.asList(response));
//                        topParentRecyclerView.setAdapter(topParentRecyclerViewAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(CreateMessage.this).addToRequestQueue(myGsonRequest);


        /////
//        List<Data> AllData = new ArrayList<>();


//        aked.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Data akedData = new Data();
//                Data daleelData = new Data();
//                akedData.setId("2");
//                daleelData.setId("1");
//                AllData.add(akedData);
//                DataRecyclerViewAdapter dataRecyclerViewAdapter = new DataRecyclerViewAdapter(AllData);
//                dataRecyclerView.setAdapter(dataRecyclerViewAdapter);
//            }
//        });


//        daleel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Data akedData = new Data();
//                Data daleelData = new Data();
//                akedData.setId("2");
//                daleelData.setId("1");
//                AllData.add(daleelData);
//                DataRecyclerViewAdapter dataRecyclerViewAdapterr = new DataRecyclerViewAdapter(AllData);
//                dataRecyclerView.setAdapter(dataRecyclerViewAdapterr);
//
//            }
//        });
    }
}