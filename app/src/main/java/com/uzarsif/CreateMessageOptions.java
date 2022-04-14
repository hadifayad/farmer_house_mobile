package com.uzarsif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class CreateMessageOptions extends AppCompatActivity {

    Button daleel,ansab,okood,daleell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message_options);
        daleel = findViewById(R.id.daleel);
        okood = findViewById(R.id.okood);
//        sell = findViewById(R.id.sell);
        ansab = findViewById(R.id.ansab);
//        daleell = findViewById(R.id.daleell);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#81bb28"));
       ansab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateMessageOptions.this, AnsabMazro3atActivity.class);
//                Intent i = new Intent(CreateMessageOptions.this, Ansab.class);

                startActivity(i);


            }});

//       daleell.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent i = new Intent(CreateMessageOptions.this, DaleelActivity.class);
////                Intent i = new Intent(CreateMessageOptions.this, Ansab.class);
//
//               startActivity(i);
//           }
//       });


        okood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CreateMessageOptions.this, OkoodZera3aActivity.class);
//                Intent i = new Intent(CreateMessageOptions.this, Ansab.class);

                startActivity(i);

            }});

//
//                final ProgressDialog dialog = ProgressDialog.show(CreateMessageOptions.this, "",
//                        "Please wait...", true);
//                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(CreateMessageOptions.this);
//                String userId = prefs.getString("userId", "");
//                String url = NetworkHelper.getUrl(NetworkHelper.ACTION_CREATE_CHAT);
//                Log.d("TAG", "onClick: "+url);
//                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
////
//
//                                dialog.dismiss();
////                                Toast.makeText(CreateMessageOptions.this, "new message",
////                                        Toast.LENGTH_LONG).show();
//                                Log.d("upload", response);
//                                Intent i = new Intent(CreateMessageOptions.this, CreateMessage.class);
//                                i.putExtra("chatId", response.toString());
//                                startActivity(i);
////                        sendToMain();
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                dialog.dismiss();
//                                Toast.makeText(CreateMessageOptions.this, "error", Toast.LENGTH_LONG).show();
//
//                            }
//                        }) {
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        HashMap<String, String> params = new HashMap<>();
//                        //    Map<String, String> params = new Hashtable<>();
//
//
//                        params.put("userId", "2");
//                        Log.d("TAG", "getParams: "+params.toString());
//
//
//
//
//                        Log.d("tag", params.toString());
//                        return params;
//
//                    }
//                };
//                {
//                    int socketTimeout = 30000;
//                    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//                    stringRequest.setRetryPolicy(policy);
//                    RequestQueue requestQueue = Volley.newRequestQueue(CreateMessageOptions.this);
//
//                    requestQueue.add(stringRequest);
//                }
//            }
//        });

    }
}