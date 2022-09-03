package com.uzarsif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.Adapters.AdminViewMessagesAdapter;
import com.uzarsif.Adapters.ChatWithMandoubRecyclerViewAdapter;
import com.uzarsif.models.AdminViewMessage;
import com.uzarsif.models.Chatwithmandoob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AdminMessagesActivity extends AppCompatActivity {
    RecyclerView chats_recyclerview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_messages);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ECEFF0"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        chats_recyclerview1 = findViewById(R.id.chats_recyclerview1);
        chats_recyclerview1.setLayoutManager(layoutManager);
        setChats();


    }
    public void setChats(){


        final ProgressDialog dialog = ProgressDialog.show(AdminMessagesActivity.this, "",
                "الرجاء الإنتظار ...", true);

        // volley
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AdminMessagesActivity.this);
        String userId = prefs.getString("userId", "");
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_ADMIN_CHATS);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
//        params.put("user", userId);
        GsonRequest<AdminViewMessage[]> myGsonRequest = new GsonRequest<AdminViewMessage[]>(Request.Method.POST, url, AdminViewMessage[].class, null, params,
                new Response.Listener<AdminViewMessage[]>() {
                    @Override
                    public void onResponse(AdminViewMessage[] response) {
                                    Log.d("TAG", "onResponse: " + response[0].getFullname());
                        dialog.dismiss();
                        AdminViewMessagesAdapter adminViewMessagesAdapter = new AdminViewMessagesAdapter(Arrays.asList(response));
                        chats_recyclerview1.setAdapter(adminViewMessagesAdapter);
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(AdminMessagesActivity.this).addToRequestQueue(myGsonRequest);

    }

}