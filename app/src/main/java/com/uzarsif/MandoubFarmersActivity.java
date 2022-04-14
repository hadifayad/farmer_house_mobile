package com.uzarsif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.Adapters.MandoubFarmersRecyclerViewAdapter;
import com.uzarsif.models.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MandoubFarmersActivity extends AppCompatActivity {

    RecyclerView users_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandoub_farmers);
        users_recyclerView = findViewById(R.id.users_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        users_recyclerView.setLayoutManager(layoutManager);
        setUsers();
    }

    public void setUsers(){

        final ProgressDialog dialog = ProgressDialog.show(MandoubFarmersActivity.this, "",
                "Please wait...", true);

        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_MANDOUB_FARMERS);
        Log.d("url", url.toString());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MandoubFarmersActivity.this);
        String userId = prefs.getString("userId", "");

        Map<String, String> params = new HashMap();
        params.put("mandoobId", userId);





        Log.d("TAG", "mandoob: "+params.toString());



        GsonRequest<User[]> myGsonRequest1 = new GsonRequest<User[]>(Request.Method.POST, url, User[].class, null, params,
                new Response.Listener<User[]>() {
                    @Override
                    public void onResponse(User[] response) {


                        dialog.dismiss();
//                        Log.d("TAG",   response[0].getId());

                        MandoubFarmersRecyclerViewAdapter mandoubFarmersRecyclerViewAdapter = new MandoubFarmersRecyclerViewAdapter(Arrays.asList(response));
                        users_recyclerView.setAdapter(mandoubFarmersRecyclerViewAdapter);




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        object.dismiss();
                        NetworkHelper.handleError(error);
                        Log.d("error", "onErrorResponse: " + error.getMessage());
                    }
                });

        VolleySingleton.getInstance(MandoubFarmersActivity.this).addToRequestQueue(myGsonRequest1);


    }
}