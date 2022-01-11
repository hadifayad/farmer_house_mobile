package com.farmerhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.farmerhouse.Adapters.DataRecyclerViewAdapter;
import com.farmerhouse.Adapters.PlantsRecyclerView;
import com.farmerhouse.inbox.CreateMessage;
import com.farmerhouse.models.Data;
import com.farmerhouse.models.DataMessages;
import com.farmerhouse.models.Plant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ansab extends AppCompatActivity {


    LinearLayout mantaaView,mawsemView,tareeaView,zeraaTypeView,plantsTypeView,waterTypeView,soilTypeView,heightView,mantaaResponseView,mawsemResponseView,tareeaResponseView,zeraaTypeResponseView,plantsTypeResponseView,waterTypeResponseView,soilTypeResponseView,heightResponseView;
    RelativeLayout dakhel,sa7el,jabal,sayfe,shatawe,rabee3e,kharejeyya,byoot,baaleyya,marweyya,khodar,bokoleyyat,ashjar,blbakhakh,blferayra,bljar,ramleyya,teeneyya,salteyya,ramleyyaTeeneyya;
    RelativeLayout height0,height100,height200,height300,height400,height500,height600,height700,height800,height900,height1000,height1100,height1200,height1300,height1400,height1500;
    TextView mantaaResponse, mawsemResponse,tareeaResponse,zeraaTypeResponse,plantsTypeResponse,waterTypeResponse,soilTypeResponse,heightResponse;
    ScrollView scrollview;
    RecyclerView ansabRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ansab);
        dakhel = findViewById(R.id.dakhel);
        sa7el = findViewById(R.id.sa7el);
        jabal = findViewById(R.id.jabal);
        mawsemView = findViewById(R.id.mawsemView);
        mantaaResponse = findViewById(R.id.mantaaResponse);
        mantaaResponseView = findViewById(R.id.mantaaResponseView);
        mawsemResponse = findViewById(R.id.mawsemResponse);
        mawsemResponseView = findViewById(R.id.mawsemResponseView);
        sayfe = findViewById(R.id.sayfe);
        shatawe = findViewById(R.id.shatawe);
        rabee3e = findViewById(R.id.rabee3e);
        tareeaView = findViewById(R.id.tareeaView);
        tareeaResponse = findViewById(R.id.tareeaResponse);
        tareeaResponseView = findViewById(R.id.tareeaResponseView);
        kharejeyya = findViewById(R.id.kharejeyya);
        byoot = findViewById(R.id.byoot);
        zeraaTypeView = findViewById(R.id.zeraaTypeView);
        zeraaTypeResponseView = findViewById(R.id.zeraaTypeResponseView);
        zeraaTypeResponse = findViewById(R.id.zeraaTypeResponse);
        baaleyya = findViewById(R.id.baaleyya);
        marweyya = findViewById(R.id.marweyya);
        plantsTypeView = findViewById(R.id.plantsTypeView);
        plantsTypeResponseView = findViewById(R.id.plantsTypeResponseView);
        plantsTypeResponse = findViewById(R.id.plantsTypeResponse);
        khodar = findViewById(R.id.khodar);
        bokoleyyat = findViewById(R.id.bokoleyyat);
        ashjar = findViewById(R.id.ashjar);
        waterTypeView = findViewById(R.id.waterTypeView);
        waterTypeResponseView = findViewById(R.id.waterTypeResponseView);
        waterTypeResponse = findViewById(R.id.waterTypeResponse);
        bljar = findViewById(R.id.bljar);
        blferayra = findViewById(R.id.blferayra);
        blbakhakh = findViewById(R.id.blbakhakh);
        soilTypeView = findViewById(R.id.soilTypeView);

        soilTypeResponseView = findViewById(R.id.soilTypeResponseView);
        soilTypeResponse = findViewById(R.id.soilTypeResponse);
        ramleyya = findViewById(R.id.ramleyya);
        teeneyya = findViewById(R.id.teeneyya);
        salteyya = findViewById(R.id.salteyya);
        ramleyyaTeeneyya = findViewById(R.id.ramleyyaTeeneyya);
        heightView = findViewById(R.id.heightView);
        heightResponse = findViewById(R.id.heightResponse);
        heightResponseView = findViewById(R.id.heightResponseView);
        height0 = findViewById(R.id.height0);
        height100 = findViewById(R.id.height100);
        height200 = findViewById(R.id.height200);
        height300 = findViewById(R.id.height300);
        height400 = findViewById(R.id.height400);
        height500 = findViewById(R.id.height500);
        height600 = findViewById(R.id.height600);
        height700 = findViewById(R.id.height700);
        height800 = findViewById(R.id.height800);
        height900 = findViewById(R.id.height900);
        height1000 = findViewById(R.id.height1000);
        height1100 = findViewById(R.id.height1100);
        height1200 = findViewById(R.id.height1200);
        height1300 = findViewById(R.id.height1300);
        height1400 = findViewById(R.id.height1400);
        height1500 = findViewById(R.id.height1500);
        scrollview = findViewById(R.id.scrollview);
        ansabRecyclerView = findViewById(R.id.ansabRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Ansab.this, LinearLayoutManager.HORIZONTAL, false);
        ansabRecyclerView.setLayoutManager(layoutManager);
        scrollview.post(new Runnable() {
            @Override
            public void run() {
//                scrollview.fullScroll(View.FOCUS_DOWN);
                scrollview.scrollTo(0, scrollview.getBottom());
            }
        });


        dakhel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mantaaResponseView.setVisibility(View.VISIBLE);
                mawsemView.setVisibility(View.VISIBLE);
                mantaaResponse.setVisibility(View.VISIBLE);
                mantaaResponse.setText("داخل");

            }
        });
        sa7el.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mantaaResponseView.setVisibility(View.VISIBLE);
                mawsemView.setVisibility(View.VISIBLE);
                mantaaResponse.setVisibility(View.VISIBLE);
                mantaaResponse.setText("ساحل");

            }
        });
        jabal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mantaaResponseView.setVisibility(View.VISIBLE);
                mawsemView.setVisibility(View.VISIBLE);
                mantaaResponse.setVisibility(View.VISIBLE);
                mantaaResponse.setText("جبل");

            }
        });

        shatawe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mawsemResponseView.setVisibility(View.VISIBLE);
                tareeaView.setVisibility(View.VISIBLE);
                mawsemResponse.setVisibility(View.VISIBLE);
                mawsemResponse.setText("شتوي");

            }
        });

        sayfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mawsemResponseView.setVisibility(View.VISIBLE);
                tareeaView.setVisibility(View.VISIBLE);
                mawsemResponse.setVisibility(View.VISIBLE);
                mawsemResponse.setText("صيفي");

            }
        });

        rabee3e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mawsemResponseView.setVisibility(View.VISIBLE);
                tareeaView.setVisibility(View.VISIBLE);
                mawsemResponse.setVisibility(View.VISIBLE);
                mawsemResponse.setText("ربيعي");

            }
        });
        kharejeyya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tareeaResponseView.setVisibility(View.VISIBLE);
                zeraaTypeView.setVisibility(View.VISIBLE);
                tareeaResponse.setVisibility(View.VISIBLE);
                tareeaResponse.setText("خارجية");

            }
        });
        byoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tareeaResponseView.setVisibility(View.VISIBLE);
                zeraaTypeView.setVisibility(View.VISIBLE);
                tareeaResponse.setVisibility(View.VISIBLE);
                tareeaResponse.setText("بيوت بلاستيكية");

            }
        });

        marweyya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zeraaTypeResponseView.setVisibility(View.VISIBLE);
                plantsTypeView.setVisibility(View.VISIBLE);
                zeraaTypeResponse.setVisibility(View.VISIBLE);
                zeraaTypeResponse.setText("مروية");

            }
        });



        baaleyya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zeraaTypeResponseView.setVisibility(View.VISIBLE);
                plantsTypeView.setVisibility(View.VISIBLE);
                zeraaTypeResponse.setVisibility(View.VISIBLE);
                zeraaTypeResponse.setText("بعلية");

            }
        });

        bokoleyyat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plantsTypeResponseView.setVisibility(View.VISIBLE);
                waterTypeView.setVisibility(View.VISIBLE);
                plantsTypeResponse.setVisibility(View.VISIBLE);
                plantsTypeResponse.setText("بقوليات");


            }
        });

        ashjar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plantsTypeResponseView.setVisibility(View.VISIBLE);
                waterTypeView.setVisibility(View.VISIBLE);
                plantsTypeResponse.setVisibility(View.VISIBLE);
                plantsTypeResponse.setText("أشجار");

            }
        });

        khodar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plantsTypeResponseView.setVisibility(View.VISIBLE);
                waterTypeView.setVisibility(View.VISIBLE);
                plantsTypeResponse.setVisibility(View.VISIBLE);
                plantsTypeResponse.setText("خضار");

            }
        });

        bljar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterTypeResponseView.setVisibility(View.VISIBLE);
                soilTypeView.setVisibility(View.VISIBLE);
                waterTypeResponse.setVisibility(View.VISIBLE);
                waterTypeResponse.setText("ري بالجر");

            }
        });

        blferayra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterTypeResponseView.setVisibility(View.VISIBLE);
                soilTypeView.setVisibility(View.VISIBLE);
                waterTypeResponse.setVisibility(View.VISIBLE);
                waterTypeResponse.setText("ري بالفريرة");

            }
        });

        blbakhakh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterTypeResponseView.setVisibility(View.VISIBLE);
                soilTypeView.setVisibility(View.VISIBLE);
                waterTypeResponse.setVisibility(View.VISIBLE);
                waterTypeResponse.setText("ري بالبخاخ");

            }
        });
 ramleyya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soilTypeResponseView.setVisibility(View.VISIBLE);
                heightView.setVisibility(View.VISIBLE);
                soilTypeResponse.setVisibility(View.VISIBLE);
                soilTypeResponse.setText("رملية");

            }
        });
 ramleyyaTeeneyya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soilTypeResponseView.setVisibility(View.VISIBLE);
                heightView.setVisibility(View.VISIBLE);
                soilTypeResponse.setVisibility(View.VISIBLE);
                soilTypeResponse.setText("رملية طينية");

            }
        });
 salteyya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soilTypeResponseView.setVisibility(View.VISIBLE);
                heightView.setVisibility(View.VISIBLE);
                soilTypeResponse.setVisibility(View.VISIBLE);
                soilTypeResponse.setText("سلتية");


            }
        });
 teeneyya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soilTypeResponseView.setVisibility(View.VISIBLE);
                heightView.setVisibility(View.VISIBLE);
                soilTypeResponse.setVisibility(View.VISIBLE);
                soilTypeResponse.setText("طينية");

            }
        });

        height0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heightResponseView.setVisibility(View.VISIBLE);

                heightResponse.setVisibility(View.VISIBLE);
                heightResponse.setText("0 - 100");
                getPlants();

            }
        });


    }



    public void getPlants(){

        final ProgressDialog dialog = ProgressDialog.show(Ansab.this, "",
                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_PLANTS);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
//        params.put("parentId", data.getId());
        GsonRequest<Plant[]> myGsonRequest = new GsonRequest<com.farmerhouse.models.Plant[]>(Request.Method.POST, url, Plant[].class, null, params,
                new Response.Listener<com.farmerhouse.models.Plant[]>() {
                    @Override
                    public void onResponse(Plant[] response) {
                                    Log.d("TAG", "onResponse: " + response[0].getName());
                        PlantsRecyclerView plantsRecyclerView = new PlantsRecyclerView(Arrays.asList(response));
                        ansabRecyclerView.setAdapter(plantsRecyclerView);
                        dialog.dismiss();




                    }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(Ansab.this).addToRequestQueue(myGsonRequest);

    }
}

