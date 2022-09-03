package com.uzarsif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputEditText;
import com.uzarsif.models.MyResponse;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText password, confirmPassword;
    Button buttonChangePassword;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        username = getIntent().getStringExtra("username");

        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        buttonChangePassword = findViewById(R.id.buttonChangePassword);

        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordStr = password.getText().toString();
                String passwordConfirmStr = confirmPassword.getText().toString();

                if (passwordStr.equals(passwordConfirmStr)) {

                    final ProgressDialog dialog = ProgressDialog.show(ChangePasswordActivity.this, "",
                            "الرجاء الإنتظار ...", true);

                    String url = NetworkHelper.getUrl(NetworkHelper.ACTION_CHANGE_PASSWORD);

                    Map<String, String> params = new HashMap();
                    params.put("username", username);
                    params.put("password", passwordStr);
                    Log.d("TAG", "onClick: "+username);
                    GsonRequest<MyResponse> myGsonRequest = new GsonRequest<MyResponse>(Request.Method.POST, url, MyResponse.class, null, params,
                            response -> {
                                if(response.getStatus().contains("1")){
                                    Toast.makeText(ChangePasswordActivity.this, "تم تغيير كلمة المرور بنجاح", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(ChangePasswordActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    dialog.dismiss();
                                    Toast.makeText(ChangePasswordActivity.this, "حدث خطأ", Toast.LENGTH_LONG).show();
                                }
                            });
                    VolleySingleton.getInstance(ChangePasswordActivity.this).addToRequestQueue(myGsonRequest);

                } else {
                    Toast.makeText(ChangePasswordActivity.this, "كلمتا المرور لا تطابقان", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}