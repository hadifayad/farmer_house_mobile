package com.farmerhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.farmerhouse.Adapters.ChoicesRecyclerView;
import com.farmerhouse.Adapters.DataRecyclerViewAdapter;
import com.farmerhouse.Adapters.MessagesRecyclerViewAdapter;
import com.farmerhouse.models.Data;
import com.farmerhouse.models.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateMessage extends AppCompatActivity {

    RelativeLayout aked,daleel;
   public  RecyclerView dataRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        Data akedData = new Data();
              Data  daleelData=new Data();
        akedData.setId("2");
        daleelData.setId("1");


        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ffffff"));

        aked = findViewById(R.id.aked);
        daleel =findViewById(R.id.daleel);



        dataRecyclerView =findViewById(R.id.dataRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(CreateMessage.this);
        dataRecyclerView.setLayoutManager(layoutManager);
        List<Data> AllData = new ArrayList<>();


        aked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data akedData = new Data();
                Data  daleelData=new Data();
                akedData.setId("2");
                daleelData.setId("1");
                AllData.add(akedData);
                DataRecyclerViewAdapter dataRecyclerViewAdapter = new DataRecyclerViewAdapter(AllData);
                dataRecyclerView.setAdapter(dataRecyclerViewAdapter);
            }
        });


        daleel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data akedData = new Data();
                Data  daleelData=new Data();
                akedData.setId("2");
                daleelData.setId("1");
                AllData.add(daleelData);
                DataRecyclerViewAdapter dataRecyclerViewAdapterr = new DataRecyclerViewAdapter(AllData);
                dataRecyclerView.setAdapter(dataRecyclerViewAdapterr);

            }
        });
    }
}