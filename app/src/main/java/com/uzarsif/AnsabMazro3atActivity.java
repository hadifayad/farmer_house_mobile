package com.uzarsif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.uzarsif.R;
import com.uzarsif.Adapters.DataRecyclerViewAdapter;
import com.uzarsif.Adapters.PlantsRecyclerView;
import com.uzarsif.ansabMazro3at.adapters.Zera3aTypesRecyclerViewAdapter;
import com.uzarsif.ansabMazro3at.models.Zera3aTypes;
import com.uzarsif.models.Data;
import com.uzarsif.models.DataMessages;
import com.uzarsif.models.Plant;

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
    ImageView save;
    public String plantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ansab_mazro3at);

        platChoosenLayout = findViewById(R.id.platChoosenLayout);
        platChoosenLayout.setVisibility(View.GONE);
        plantText = findViewById(R.id.plantText);
        plantsLayout = findViewById(R.id.plantsLayout);
        scrollView = findViewById(R.id.scrollview);
        zera3aTypesRV = findViewById(R.id.zera3aTypesRV);
        save = findViewById(R.id.save);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        zera3aTypesRV.setLayoutManager(layoutManager);
        ansabRecyclerView = findViewById(R.id.ansabRecyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(AnsabMazro3atActivity.this, LinearLayoutManager.HORIZONTAL, false);
        ansabRecyclerView.setLayoutManager(layoutManager1);
        plantsDataRV = findViewById(R.id.plantsDataRV);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        plantsDataRV.setLayoutManager(layoutManager2);
        setData();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Zera3aTypes> zera3aTypes = zera3aTypesRecyclerViewAdapter.getZera3aTypes();
                showMyDialog(zera3aTypes);
            }
        });
    }

    public void setData() {

        final ProgressDialog dialog = ProgressDialog.show(AnsabMazro3atActivity.this, "",
                "الرجاء الإنتظار ...", true);

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
//            Toast.makeText(getApplicationContext(), "all selected", Toast.LENGTH_SHORT).show();
//            Log.d("TAG", "check:size "+size);
//            Log.d("TAG", "check: "+zera3aTypes.get(0).getName()+zera3aTypes.get(0).getResultId());
//            Log.d("TAG", "check: "+zera3aTypes.get(1).getName()+zera3aTypes.get(1).getResultId());
//            Log.d("TAG", "check: "+zera3aTypes.get(2).getName()+zera3aTypes.get(2).getResultId());
            getPlants();

        }
    }

    public void getPlants() {
        List<Zera3aTypes> zera3aTypes = zera3aTypesRecyclerViewAdapter.getZera3aTypes();

        String heightId = zera3aTypes.get(2).getResultId();
        String plantingTypeId = zera3aTypes.get(6).getResultId();
        String plantsTypeId = zera3aTypes.get(7).getResultId();
        String waterTypeId = zera3aTypes.get(4).getResultId();
        String soilTypeId = zera3aTypes.get(1).getResultId();
        String mantaaId = zera3aTypes.get(3).getResultId();
        String mazrouatTypeId = zera3aTypes.get(5).getResultId();
        String mawsem_id = zera3aTypes.get(0).getResultId();







        final ProgressDialog dialog = ProgressDialog.show(AnsabMazro3atActivity.this, "",
                "الرجاء الإنتظار ...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_PLANTS);
        Log.d("url", url.toString());

        Map<String, String> params = new HashMap();



        params.put("heightId",heightId);
        params.put("plantingTypeId",plantingTypeId);
        params.put("plantsTypeId",plantsTypeId);
        params.put("waterTypeId",waterTypeId);
        params.put("soilTypeId",soilTypeId);
        params.put("mantaaId",mantaaId);
        params.put("mazrouatTypeId",mazrouatTypeId);
        params.put("mawsem_id",mawsem_id);
        Log.d("TAG", "getPlants: "+params.toString());
//        params.put("parentId", data.getId());
        GsonRequest<Plant[]> myGsonRequest = new GsonRequest<>(Request.Method.POST, url, Plant[].class, null, params,
                response -> {
//                    Log.d("TAG", "onResponse: " + response[0].getName());
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
        }, 500);
    }

    public void showMyDialog( List<Zera3aTypes> zera3aTypes){


        CustomDialog cdd=new CustomDialog(AnsabMazro3atActivity.this,zera3aTypes,plantId);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();

        cdd.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        View view = getLayoutInflater().inflate(R.layout.cutom_dialog,null);
//        builder.setView(view);
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        alertDialog.show();
//        alertDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


//        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                switch (which){
//                    case DialogInterface.BUTTON_POSITIVE:
//                        //Yes button clicked
//                        break;
//
//                    case DialogInterface.BUTTON_NEGATIVE:
//                        //No button clicked
//                        break;
//                }
//            }
//        };
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(AnsabMazro3atActivity.this);
//        builder.setMessage("").setPositiveButton("Yes", dialogClickListener)
//                .setNegativeButton("No", dialogClickListener).show();

    }

    @Override
    public void plantClicked(Plant plant) {
        plantsLayout.setVisibility(View.VISIBLE);
        platChoosenLayout.setVisibility(View.VISIBLE);
        plantText.setText(plant.getName());
        this.plantId = plant.getId();
        final ProgressDialog dialog = ProgressDialog.show(AnsabMazro3atActivity.this, "",
                "الرجاء الإنتظار ...", true);

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
                    scrollDown();
                },
                error -> {
                    dialog.dismiss();
                    NetworkHelper.handleError(error);
                });

        VolleySingleton.getInstance(AnsabMazro3atActivity.this).addToRequestQueue(myGsonRequest);








    }

    @Override
    public void onBackPressed() {
        List<Zera3aTypes> zera3aTypes = zera3aTypesRecyclerViewAdapter.getZera3aTypes();
        showMyDialog(zera3aTypes);
    }


}