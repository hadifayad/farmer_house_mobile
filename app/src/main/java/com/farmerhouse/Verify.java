package com.farmerhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Verify extends AppCompatActivity {

    private Button mVerifyCodeBtn;
    private EditText otpEdit;
    private String OTP, phoneNumberString , passwordString;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#cc9f53"));

        mVerifyCodeBtn = findViewById(R.id.verify_button);
        otpEdit = findViewById(R.id.code_edit_text);

        firebaseAuth = FirebaseAuth.getInstance();

        phoneNumberString = getIntent().getStringExtra("phone");

        passwordString = getIntent().getStringExtra("password");
        OTP = getIntent().getStringExtra("auth");



//        List<SignUpGames> signUpGames =(List<SignUpGames>) getIntent().getParcelableExtra("signUpGames");
//        Toast.makeText(this,signUpGames.get(0).getName() , Toast.LENGTH_SHORT).show();

        mVerifyCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verification_code = otpEdit.getText().toString();
                if (!verification_code.isEmpty()) {
                    Log.d("TAG", "onClick: "+verification_code);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OTP, verification_code);

                    signIn(credential);
                } else {
                    Toast.makeText(Verify.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signIn(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                    sendData();
                    Toast.makeText(Verify.this, "Verification Success", Toast.LENGTH_SHORT).show();

                    Intent k = new Intent(Verify.this, UserData.class);

                    k.putExtra("phone", phoneNumberString);
                    k.putExtra("password", passwordString);

                    startActivity(k);





                } else {
                    Toast.makeText(Verify.this, "Verification Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

    }

    private void sendToMain() {
        startActivity(new Intent(Verify.this, Signup.class));
        finish();
    }

    private void sendData() {



        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_SIGNUP_USER);

        final ProgressDialog dialog = ProgressDialog.show(Verify.this, "",
                "Please wait...", true);


        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//
                        Toast.makeText(Verify.this, "Signup Successful",
                                Toast.LENGTH_LONG).show();
                        Log.d("upload", response);
//                        sendToMain();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        Toast.makeText(Verify.this, "error", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                //    Map<String, String> params = new Hashtable<>();


                params.put("phone", phoneNumberString);

                params.put("password", passwordString);

                params.put("token", MyPreferencesUtils.getUserToken(Verify.this));


                Log.d("tag", params.toString());
                return params;

            }
        };
        {
            int socketTimeout = 30000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
            RequestQueue requestQueue = Volley.newRequestQueue(Verify.this);

            requestQueue.add(stringRequest);
        }


    }
}