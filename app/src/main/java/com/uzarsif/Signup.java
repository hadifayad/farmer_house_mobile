package com.uzarsif;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Signup extends AppCompatActivity {

    EditText phoneNumberView , passwordView;
    Button signupButton;
    FirebaseAuth auth;
    TextView processText;
    private CountryCodePicker countryCodePicker;
    private ProgressDialog dialog;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();


        //////
        phoneNumberView = findViewById(R.id.phoneNumberView);
        signupButton = findViewById(R.id.signupButton);
        processText = findViewById(R.id.progressResultView);
        processText = findViewById(R.id.progressResultView);
         passwordView = findViewById(R.id.passwordView);
        countryCodePicker = findViewById(R.id.ccp);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumberString = phoneNumberView.getText().toString();
                String passwordString = passwordView.getText().toString();

                String countryCode = countryCodePicker.getSelectedCountryCode();
                String fullPhoneNumber = "+" + countryCode + phoneNumberString;
                if(fullPhoneNumber!= null && fullPhoneNumber!="" && !fullPhoneNumber.equalsIgnoreCase("") && passwordString!= null && passwordString!="" && !passwordString.equalsIgnoreCase("")){

                    dialog = ProgressDialog.show(Signup.this, "",
                            "الرجاء الانتظار ..", true);



                        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                                .setPhoneNumber(fullPhoneNumber)
                                .setTimeout(60L, TimeUnit.SECONDS)
                                .setActivity(Signup.this)
                                .setCallbacks(mCallBacks)
                                .build();
                        PhoneAuthProvider.verifyPhoneNumber(options);

                }
                else{
                    Toast.makeText(Signup.this, "الرحاء ادخال رقم الهاتف وكلمة مرور",
                            Toast.LENGTH_LONG).show();

                }
            }
        });



        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signIn(phoneAuthCredential);
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
                        Intent otpIntent = new Intent(Signup.this, Verify.class);

                        String phoneNumberString = phoneNumberView.getText().toString();
                        String passwordString = passwordView.getText().toString();


                        otpIntent.putExtra("phone", phoneNumberString);
                        otpIntent.putExtra("password", passwordString);


                        otpIntent.putExtra("auth", s);

                        startActivity(otpIntent);
                    }
                }, 1200);

            }
        };



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
//        if (user != null) {
//            sendToMain();
//        }
    }

    private void signIn(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Signup.this, "تم التسجيل بنجاح",
                            Toast.LENGTH_LONG).show();

                } else {

                    processText.setText(task.getException().getMessage());
                    processText.setTextColor(Color.RED);
                    processText.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}