package com.farmerhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.farmerhouse.Adapters.DataRecyclerViewAdapter;
import com.farmerhouse.Adapters.PlantsRecyclerView;
import com.farmerhouse.ansabMazro3at.adapters.Zera3aTypesRecyclerViewAdapter;
import com.farmerhouse.ansabMazro3at.models.Zera3aTypes;
import com.farmerhouse.inbox.CreateMessage;
import com.farmerhouse.models.Data;
import com.farmerhouse.models.DataMessages;
import com.farmerhouse.models.Plant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnsabMazro3atActivity extends AppCompatActivity implements Zera3aTypesRecyclerViewAdapter.CheckResults, PlantsRecyclerView.PlantInterface {

    RecyclerView zera3aTypesRV, ansabRecyclerView, plantsDataRV;
    Zera3aTypesRecyclerViewAdapter zera3aTypesRecyclerViewAdapter;
    ScrollView scrollView;
    LinearLayout plantsLayout, platChoosenLayout;
    TextView plantText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ansab_mazro3at);

        platChoosenLayout = findViewById(R.id.platChoosenLayout);
        plantText = findViewById(R.id.plantText);
        plantsLayout = findViewById(R.id.plantsLayout);
        scrollView = findViewById(R.id.scrollview);
        zera3aTypesRV = findViewById(R.id.zera3aTypesRV);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        zera3aTypesRV.setLayoutManager(layoutManager);
        ansabRecyclerView = findViewById(R.id.ansabRecyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(AnsabMazro3atActivity.this, LinearLayoutManager.HORIZONTAL, false);
        ansabRecyclerView.setLayoutManager(layoutManager1);
        plantsDataRV = findViewById(R.id.plantsDataRV);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        plantsDataRV.setLayoutManager(layoutManager2);
        setData();
    }

    public void setData() {

        final ProgressDialog dialog = ProgressDialog.show(AnsabMazro3atActivity.this, "",
                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_ZERA3AT_TYPES);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
        GsonRequest<Zera3aTypes[]> myGsonRequest = new GsonRequest<Zera3aTypes[]>(Request.Method.POST, url, Zera3aTypes[].class, null, params,
                response -> {
                    zera3aTypesRecyclerViewAdapter = new Zera3aTypesRecyclerViewAdapter(Arrays.asList(response), this);
                    zera3aTypesRV.setAdapter(zera3aTypesRecyclerViewAdapter);
                    dialog.dismiss();
                },
                error -> {
                    dialog.dismiss();
                    NetworkHelper.handleError(error);
                });

        VolleySingleton.getInstance(AnsabMazro3atActivity.this).addToRequestQueue(myGsonRequest);


    }

    @Override
    public void check() {
        List<Zera3aTypes> zera3aTypes = zera3aTypesRecyclerViewAdapter.getZera3aTypes();
        int size = zera3aTypes.size();
        int x = 0;
        for (int i = 0; i < size; i++) {
            if (!zera3aTypes.get(i).getResultId().equals("")) {
                x++;
            }
        }
        if (size == x) { // all items are selected
            Toast.makeText(getApplicationContext(), "all selected", Toast.LENGTH_SHORT).show();
            getPlants();
        }
    }

    public void getPlants() {

        final ProgressDialog dialog = ProgressDialog.show(AnsabMazro3atActivity.this, "",
                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_PLANTS);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
//        params.put("parentId", data.getId());
        GsonRequest<Plant[]> myGsonRequest = new GsonRequest<>(Request.Method.POST, url, Plant[].class, null, params,
                response -> {
                    Log.d("TAG", "onResponse: " + response[0].getName());
                    PlantsRecyclerView plantsRecyclerView = new PlantsRecyclerView(Arrays.asList(response), AnsabMazro3atActivity.this);
                    ansabRecyclerView.setAdapter(plantsRecyclerView);
                    dialog.dismiss();
                    scrollDown();
                },
                error -> {
                    dialog.dismiss();
                    NetworkHelper.handleError(error);
                });

        VolleySingleton.getInstance(AnsabMazro3atActivity.this).addToRequestQueue(myGsonRequest);

    }


    public void scrollDown() {
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 200);
    }

    @Override
    public void plantClicked(Plant plant) {
        plantsLayout.setVisibility(View.VISIBLE);
        platChoosenLayout.setVisibility(View.VISIBLE);
        plantText.setText(plant.getName());
        final ProgressDialog dialog = ProgressDialog.show(AnsabMazro3atActivity.this, "",
                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_TOP_PARENT_AFTER_PICKING_PLANT);
        Map<String, String> params = new HashMap();
        params.put("dataId", plant.getData_id());
        GsonRequest<Data[]> myGsonRequest = new GsonRequest<>(Request.Method.POST, url, Data[].class, null, params,
                response -> {
                    dialog.dismiss();

                    DataMessages dataMessage = new DataMessages();
                    dataMessage.setData(Arrays.asList(response));
//                    dataMessage.setId(plant.getData_id());
//                    dataMessage.setText(plant.getName());
                    List<DataMessages> dataMessages = new ArrayList<>();
                    dataMessages.add(dataMessage);

                    DataRecyclerViewAdapter dataRecyclerViewAdapter = new DataRecyclerViewAdapter(dataMessages, "-1");
                    plantsDataRV.setAdapter(dataRecyclerViewAdapter);
                },
                error -> {
                    dialog.dismiss();
                    NetworkHelper.handleError(error);
                });

        VolleySingleton.getInstance(AnsabMazro3atActivity.this).addToRequestQueue(myGsonRequest);
    }
}