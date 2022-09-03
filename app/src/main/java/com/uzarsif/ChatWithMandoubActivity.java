package com.uzarsif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.Adapters.ChatWithMandoubRecyclerViewAdapter;
import com.uzarsif.models.Chatwithmandoob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChatWithMandoubActivity extends AppCompatActivity {
    RecyclerView chats_recyclerview;
    ImageView newMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_mandoub);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ECEFF0"));
        chats_recyclerview = findViewById(R.id.chats_recyclerview);
        newMessageButton = findViewById(R.id.newMessageButton);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        chats_recyclerview.setLayoutManager(layoutManager);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChatWithMandoubActivity.this);
        String role = prefs.getString("role", "");
        if(role.contains("1")) {
            Log.d("TAG", "onCreate: "+role);
            setMandoubChats();
        }
        else if(role.contains("2")){
            Intent i = new Intent(ChatWithMandoubActivity.this, AdminMessagesActivity.class);


            startActivity(i);
            finish();
        }
        else{
            Log.d("TAG", "onCreate:else "+role);
            setChats();
        }


        newMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChatWithMandoubActivity.this);
                String role = prefs.getString("role", "");
                if(role.contains("1")){


                    Intent i = new Intent(ChatWithMandoubActivity.this, MandoubFarmersActivity.class);


                    startActivity(i);


                }

else{
                    createChat();
                }


            }
        });



    }

    public void setChats(){


        final ProgressDialog dialog = ProgressDialog.show(ChatWithMandoubActivity.this, "",
                "الرجاء الإنتظار ...", true);

        // volley
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChatWithMandoubActivity.this);
        String userId = prefs.getString("userId", "");
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_USER_CHATS_WITH_MANDOUB);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
        params.put("user", userId);
        GsonRequest<Chatwithmandoob[]> myGsonRequest = new GsonRequest<Chatwithmandoob[]>(Request.Method.POST, url, Chatwithmandoob[].class, null, params,
                new Response.Listener<Chatwithmandoob[]>() {
                    @Override
                    public void onResponse(Chatwithmandoob[] response) {
//                                    Log.d("TAG", "onResponse: " + response[0].getDate());
                        dialog.dismiss();
                        ChatWithMandoubRecyclerViewAdapter chatWithMandoubRecyclerViewAdapter = new ChatWithMandoubRecyclerViewAdapter(Arrays.asList(response));
                        chats_recyclerview.setAdapter(chatWithMandoubRecyclerViewAdapter);
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(ChatWithMandoubActivity.this).addToRequestQueue(myGsonRequest);

    }
    public void setMandoubChats(){


        final ProgressDialog dialog = ProgressDialog.show(ChatWithMandoubActivity.this, "",
                "الرجاء الإنتظار ...", true);

        // volley
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChatWithMandoubActivity.this);
        String userId = prefs.getString("userId", "");
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_MANDOUB_CHATS_WITH_USERS);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
        params.put("user", userId);
        GsonRequest<Chatwithmandoob[]> myGsonRequest = new GsonRequest<Chatwithmandoob[]>(Request.Method.POST, url, Chatwithmandoob[].class, null, params,
                new Response.Listener<Chatwithmandoob[]>() {
                    @Override
                    public void onResponse(Chatwithmandoob[] response) {
//                                    Log.d("TAG", "onResponse: " + response[0].getDate());
                        dialog.dismiss();
                        ChatWithMandoubRecyclerViewAdapter chatWithMandoubRecyclerViewAdapter = new ChatWithMandoubRecyclerViewAdapter(Arrays.asList(response));
                        chats_recyclerview.setAdapter(chatWithMandoubRecyclerViewAdapter);
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(ChatWithMandoubActivity.this).addToRequestQueue(myGsonRequest);

    }

    public void createChat(){
        //////

        final ProgressDialog dialog = ProgressDialog.show(ChatWithMandoubActivity.this, "",
                "الرجاء الإنتظار ...", true);

        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_CREATE_CHAT_WITH_MANDOOB);
        Log.d("url", url.toString());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChatWithMandoubActivity.this);
        String userId = prefs.getString("userId", "");

        Map<String, String> params = new HashMap();
        params.put("userId", userId);
//        params.put("mandoob", "4");




        Log.d("TAG", "uploadData: "+params.toString());



        GsonRequest<Chatwithmandoob[]> myGsonRequest1 = new GsonRequest<Chatwithmandoob[]>(Request.Method.POST, url, Chatwithmandoob[].class, null, params,
                new Response.Listener<Chatwithmandoob[]>() {
                    @Override
                    public void onResponse(Chatwithmandoob[] response) {

//                        Log.d("TAG", "onResponse: "+response.getFullname());
                        dialog.dismiss();
                        Log.d("TAG",   response[0].getId());
                        Intent i = new Intent(ChatWithMandoubActivity.this, ChatCommentsActivity.class);
                        i.putExtra("chatId", response[0].getId());
                        Log.d("TAG", "onResponsee: " + response[0].getId() + response[0].getMandoob() + response[0].getUser());
                        startActivity(i);



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

        VolleySingleton.getInstance(ChatWithMandoubActivity.this).addToRequestQueue(myGsonRequest1);


        ////
////        String chatId =null;
//        final ProgressDialog dialog = ProgressDialog.show(ChatWithMandoubActivity.this, "",
//                "الرجاء الإنتظار ...", true);
//
//        // volley
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ChatWithMandoubActivity.this);
//        String userId = prefs.getString("userId", "");
//        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_CREATE_CHAT_WITH_MANDOOB);
//        Log.d("url", url.toString());
//        Map<String, String> params = new HashMap();
//        params.put("user", userId);
//        params.put("mandoob", "4");
//        Log.d("TAG", "createChat: "+params.toString());
//        GsonRequest<Chatwithmandoob[]> myGsonRequest = new GsonRequest<Chatwithmandoob[]>(Request.Method.POST, url, Chatwithmandoob[].class, null, params,
//                new Response.Listener<Chatwithmandoob[]>() {
//                    @Override
//                    public void onResponse(Chatwithmandoob[] response) {
////                                    Log.d("TAG", "onResponse: " + response[0].getDate());
//
//                    dialog.dismiss();
//                    response[0].getId();
//                    Intent i = new Intent(ChatWithMandoubActivity.this, ChatCommentsActivity.class);
//                    i.putExtra("chatId", response[0].getId());
//                    Log.d("TAG", "onResponsee: " + response[0].getId() + response[0].getMandoob() + response[0].getUser());
//                    startActivity(i);
//
//
//                }},
//                new Response.ErrorListener() {
//
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        dialog.dismiss();
//                        NetworkHelper.handleError(error);
//                    }
//                });
//
//        VolleySingleton.getInstance(ChatWithMandoubActivity.this).addToRequestQueue(myGsonRequest);
//



    }


}