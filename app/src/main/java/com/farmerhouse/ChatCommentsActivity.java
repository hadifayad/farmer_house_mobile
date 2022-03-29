package com.farmerhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.farmerhouse.Adapters.ChatWithMandoubRecyclerViewAdapter;
import com.farmerhouse.Adapters.CommentsRecyclerViewAdapter;
import com.farmerhouse.models.Chatwithmandoob;
import com.farmerhouse.models.Comment;
import com.farmerhouse.models.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ChatCommentsActivity extends AppCompatActivity {
    RecyclerView comments_recyclerView;
    EditText textInput;
    RelativeLayout send;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_comments);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#FFFFFFFF"));
        comments_recyclerView = findViewById(R.id.comments_recyclerView);
        textInput = findViewById(R.id.commenttext);
        back_button = findViewById(R.id.back_button);
        send = findViewById(R.id.send);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        comments_recyclerView.setLayoutManager(layoutManager);


        if(getIntent().hasExtra("chatId")) {

           String chatId = getIntent().getStringExtra("chatId");
            setComments(chatId);

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String text = textInput.getText().toString();
                    if (!text.equals("")) {
                        send.setEnabled(false);
                        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_AD_COMMENT);
                        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.contains("true")) {
                                            textInput.setText("");
                                            setComments(chatId);
                                        } else {
                                            Toast.makeText(ChatCommentsActivity.this, "حدث خطأ", Toast.LENGTH_LONG).show();
                                        }
                                        send.setEnabled(true);
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(ChatCommentsActivity.this, "حدث خطأ", Toast.LENGTH_LONG).show();
                                        send.setEnabled(true);

                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String, String> params = new Hashtable<String, String>();
                                params.put("text", text);
                                params.put("chatId", chatId);
                                params.put("userId", User.getID(ChatCommentsActivity.this));
                                Log.d("TAG", "getParams: "+params.toString());
                                return params;
                            }
                        };
                        {
                            int socketTimeout = 30000;
                            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                            stringRequest.setRetryPolicy(policy);
                            RequestQueue requestQueue = Volley.newRequestQueue(ChatCommentsActivity.this);
                            requestQueue.add(stringRequest);
                        }
                    }
                }
            });

            back_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ChatWithMandoubActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
            });


   }



    }

    public void setComments(String chatId){

        final ProgressDialog dialog = ProgressDialog.show(ChatCommentsActivity.this, "",
                "Please wait...", true);

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
                        dialog.dismiss();
                         CommentsRecyclerViewAdapter commentsRecyclerViewAdapter = new CommentsRecyclerViewAdapter(Arrays.asList(response));
                        comments_recyclerView.setAdapter(commentsRecyclerViewAdapter);
                    }
                },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(ChatCommentsActivity.this).addToRequestQueue(myGsonRequest);





    }


}