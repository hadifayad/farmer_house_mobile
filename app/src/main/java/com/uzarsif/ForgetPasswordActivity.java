package com.uzarsif;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class ForgetPasswordActivity extends AppCompatActivity {
    Button resetPassword;
    EditText username;
    CountryCodePicker countryCodePicker;
    private FirebaseAuth auth;
    private ProgressDialog dialog;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    private TextView processText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        resetPassword = findViewById(R.id.resetPassword);
        username = findViewById(R.id.username);
        countryCodePicker = findViewById(R.id.ccp);
        processText = findViewById(R.id.processText);
        auth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = ProgressDialog.show(ForgetPasswordActivity.this, "",
                        "الرجاء الإنتظار ...", true);


                String phone = username.getText().toString();
                String countryCode = countryCodePicker.getSelectedCountryCode();
                String fullPhoneNumber = "+" + countryCode + phone;
                Log.d("tag", countryCode);

                if (!phone.isEmpty() || !countryCode.isEmpty()) {
                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                            .setPhoneNumber(fullPhoneNumber)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(ForgetPasswordActivity.this)
                            .setCallbacks(mCallBacks)
                            .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);


                } else {
                    processText.setText("الرجاء ادخال رقم الهاتف");
                    processText.setTextColor(Color.RED);
                    processText.setVisibility(View.VISIBLE);
                }

            }
        });


        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                signIn(phoneAuthCredential);
                // go to change password

                Intent intent = new Intent(ForgetPasswordActivity.this, ChangePasswordActivity.class);
                String usernamestring = username.getText().toString();
                String countryCode = countryCodePicker.getSelectedCountryCode();
                String fullPhoneNumber = "+" + countryCode + usernamestring;
                intent.putExtra("username", fullPhoneNumber);
                startActivity(intent);
                finish();
            }


            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                dialog.dismiss();

                processText.setText(e.getMessage());
                processText.setTextColor(Color.RED);
                processText.setVisibility(View.VISIBLE);
            }


            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                //sometime the code is not detected automatically
                //so user has to manually enter the code
                processText.setText("Code has been Sent");
                processText.setVisibility(View.VISIBLE);
                dialog.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        go to verify activity
                        Intent intent = new Intent(ForgetPasswordActivity.this, VerifyPasswordActivity.class);
                        String usernamestring = username.getText().toString();
                        String countryCode = countryCodePicker.getSelectedCountryCode();
                        String fullPhoneNumber = "+" + countryCode + usernamestring;
                        intent.putExtra("username", fullPhoneNumber);
                        intent.putExtra("auth", s);
                        startActivity(intent);
                        finish();
                    }
                }, 1200);

            }
        };
    }
}