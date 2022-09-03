package com.uzarsif;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyPasswordActivity extends AppCompatActivity {
    private Button mVerifyCodeBtn;
    private EditText otpEdit;
    private String OTP;
    private FirebaseAuth firebaseAuth;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_password);
        mVerifyCodeBtn = findViewById(R.id.verify_button);
        otpEdit = findViewById(R.id.code_edit_text);
        firebaseAuth = FirebaseAuth.getInstance();

        OTP = getIntent().getStringExtra("auth");
        username = getIntent().getStringExtra("username");

        mVerifyCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verification_code = otpEdit.getText().toString();
                if (!verification_code.isEmpty()) {
                    Log.d("TAG", "onClick: "+verification_code);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OTP, verification_code);

                    checkCredential(credential);
                } else {
                    Toast.makeText(VerifyPasswordActivity.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void checkCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(VerifyPasswordActivity.this, "Verified", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VerifyPasswordActivity.this, ChangePasswordActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(VerifyPasswordActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}