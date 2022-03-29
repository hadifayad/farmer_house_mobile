package com.farmerhouse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.farmerhouse.Adapters.DaleelRecyclerViewAdapter;
import com.farmerhouse.models.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DaleelActivity extends AppCompatActivity {
   private RecyclerView daleelRecyclerView;
    public String childId , childTitle,childText,childImage;
    ImageView close,back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daleel);
      daleelRecyclerView = findViewById(R.id.daleelRecyclerView);
        close = findViewById(R.id.close);
        back_button = findViewById(R.id.back_button);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#FFFFFF"));
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        daleelRecyclerView.setLayoutManager(layoutManager2);

        if(getIntent().hasExtra("childId")){

             childId = getIntent().getStringExtra("childId");
            childTitle = getIntent().getStringExtra("childTitle");
            childText = getIntent().getStringExtra("childText");
            childImage = getIntent().getStringExtra("childImage");

            Log.d("TAG", "onCreatee: "+childId+childText+childTitle+childImage);
            getChild(childId,childText,childTitle,childImage);




        }
        else
        {
  getParents();

        }



        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });




    }

    public void getParents(){
                final ProgressDialog dialog = ProgressDialog.show(DaleelActivity.this, "",
                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_TOP_PARENT);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
//        params.put("parentId", data.getId());
        GsonRequest<Data[]> myGsonRequest = new GsonRequest<Data[]>(Request.Method.POST, url, Data[].class, null, params,
                new Response.Listener<Data[]>() {
                    @Override
                    public void onResponse(Data[] response) {
//                                    Log.d("TAG", "onResponse: " + response[0].getDate());
                        dialog.dismiss();
                        DaleelRecyclerViewAdapter daleelRecyclerViewAdapter = new DaleelRecyclerViewAdapter(Arrays.asList(response));
                        daleelRecyclerView.setAdapter(daleelRecyclerViewAdapter);
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(DaleelActivity.this).addToRequestQueue(myGsonRequest);




    }

    public void getChild(String childId,String childText , String childTitle, String childImage){

        final ProgressDialog dialog = ProgressDialog.show(DaleelActivity.this, "",
                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_CHILDREN);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
        params.put("parentId", childId);
        Log.d("TAG", "getChild:params "+childId);
        GsonRequest<Data[]> myGsonRequest = new GsonRequest<Data[]>(Request.Method.POST, url, Data[].class, null, params,
                new Response.Listener<Data[]>() {
                    @Override
                    public void onResponse(Data[] response) {
//                                    Log.d("TAG", "onResponse: " + response[0].getDate());

                        if(response.length==0){
                            dialog.dismiss();
                            Intent k = new Intent(DaleelActivity.this, DataFinal.class);

                            k.putExtra("childId", childId);
                            k.putExtra("childTitle",childTitle);
                            k.putExtra("childText", childText);
                            k.putExtra("childImage", childImage);
                            Log.d("TAG", "onClick: +parent = "+k.toString());
                            startActivity(k);
                            finish();


                        }
                        else
                        {
                            dialog.dismiss();
                            DaleelRecyclerViewAdapter daleelRecyclerViewAdapter = new DaleelRecyclerViewAdapter(Arrays.asList(response));
                            daleelRecyclerView.setAdapter(daleelRecyclerViewAdapter);

                        }
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(DaleelActivity.this).addToRequestQueue(myGsonRequest);



    }
}