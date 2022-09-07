package com.uzarsif;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.uzarsif.Adapters.AdminCommentsRecyclerView;
import com.uzarsif.Adapters.AdminViewMessagesAdapter;
import com.uzarsif.Adapters.CommentsRecyclerViewAdapter;
import com.uzarsif.models.Comment;
import com.uzarsif.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class AdminViewMessage extends AppCompatActivity {

    public RecyclerView comments_recyclerView;
    EditText textInput;
    RelativeLayout send;
    ImageView back_button;
    LinearLayout commentLayout;
    String mandoub;
    AdminCommentsRecyclerView commentsRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_message);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));

        IntentFilter intentFilter = new IntentFilter("com.myApp.CUSTOM_EVENT");
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, intentFilter);


        comments_recyclerView = findViewById(R.id.comments_recyclerView);
        textInput = findViewById(R.id.commenttext);
        back_button = findViewById(R.id.back_button);
        commentLayout = findViewById(R.id.commentLayout);

        send = findViewById(R.id.send);
        comments_recyclerView = findViewById(R.id.comments_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        comments_recyclerView.setLayoutManager(layoutManager);
        String chatId ="";

//        commentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        textInput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                comments_recyclerView.smoothScrollToPosition(comments_recyclerView.getAdapter().getItemCount() - 1);
//            }
//        });

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AdminViewMessage.this);
                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        SharedPreferences.Editor ed = prefs.edit();

                        ed.putString("token", token);

                        ed.commit();

//                        Toast.makeText(splash.this, token, Toast.LENGTH_SHORT).show();
                    }
                });




        if(getIntent().hasExtra("chatId")) {

        }


        chatId = getIntent().getStringExtra("chatId");
         mandoub = getIntent().getStringExtra("mandoub");
        setComments(chatId);


        String finalChatId1 = chatId;
        String finalChatId2 = chatId;

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = textInput.getText().toString();
                if (!text.equals("")) {
                    send.setEnabled(false);
                    String url = NetworkHelper.getUrl(NetworkHelper.ACTION_AD_COMMENT);
                    Log.d("TAG", "onClick: "+url);
                    final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//                                        if (response.contains("true")) {

//                                        Toast.makeText(AdminViewMessage.this, "حدث ", Toast.LENGTH_LONG).show();

                                    textInput.setText("");

                                    setComments(finalChatId1);
//                                        } else {
////                                            Toast.makeText(AdminViewMessage.this, "حدث خطأ", Toast.LENGTH_LONG).show();
//                                        }
                                    send.setEnabled(true);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(AdminViewMessage.this, "حدث خطأ", Toast.LENGTH_LONG).show();
                                    send.setEnabled(true);

                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new Hashtable<String, String>();
                            params.put("text", text);
                            params.put("chatId", finalChatId2);
                            params.put("userId", User.getID(AdminViewMessage.this));
                            Log.d("TAG", "getParams: "+params.toString());
                            return params;
                        }
                    };
                    {
                        int socketTimeout = 30000;
                        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                        stringRequest.setRetryPolicy(policy);
                        RequestQueue requestQueue = Volley.newRequestQueue(AdminViewMessage.this);
                        requestQueue.add(stringRequest);
                    }
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminMessagesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        String finalChatId = chatId;
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setComments(finalChatId); // your code
                pullToRefresh.setRefreshing(false);
            }
        });









    }

    private BroadcastReceiver onNotice = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            String name = intent.getExtras().getString("name");
            String text = intent.getExtras().getString("text");
            Log.d("TAG", "onReceive: "+intent.getStringExtra("text"));
//            Toast.makeText(context,  " " + text, Toast.LENGTH_SHORT).show();
            Comment comment = new Comment();

            comment.setText(text);
            comment.setId("0");
            comment.setUserId("0");
            Log.d("TAG", "onReceive:comment text "+comment.getText());


            commentsRecyclerViewAdapter.addItem(comment);
//            comments_recyclerView.smoothScrollToPosition(commentsRecyclerViewAdapter.getItemCount() - 1);
            comments_recyclerView.scrollToPosition(commentsRecyclerViewAdapter.getItemCount()-1);
        }
    };
    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(onNotice);
        super.onDestroy();
    }

    public void setComments(String chatId){

//        final ProgressDialog dialog = ProgressDialog.show(AdminViewMessage.this, "",
//                "الرجاء الإنتظار ...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_CHAT_COMMENTS);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
        params.put("chatId",chatId);
        GsonRequest<Comment[]> myGsonRequest = new GsonRequest<Comment[]>(Request.Method.POST, url, Comment[].class, null, params,
                new Response.Listener<Comment[]>() {
                    @Override
                    public void onResponse(Comment[] response) {
//                                    Log.d("TAG", "onResponse: " + response[0].getDate());
//                        dialog.dismiss();
                        commentsRecyclerViewAdapter = new AdminCommentsRecyclerView(new ArrayList<>(Arrays.asList(response)),mandoub);
                        comments_recyclerView.setAdapter(commentsRecyclerViewAdapter);
//                        comments_recyclerView.smoothScrollToPosition(commentsRecyclerViewAdapter.getItemCount());
                        comments_recyclerView.scrollToPosition(commentsRecyclerViewAdapter.getItemCount()-1);
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(AdminViewMessage.this).addToRequestQueue(myGsonRequest);





    }

}