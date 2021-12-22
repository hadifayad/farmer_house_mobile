package com.farmerhouse.fragmentsInProfile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farmerhouse.R;
import com.farmerhouse.Signup;
import com.farmerhouse.Verify;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class SignupInFragment extends Fragment {
    EditText phoneNumberView , passwordView;
    Button signupButton;
    FirebaseAuth auth;
    TextView processText;
    private CountryCodePicker countryCodePicker;
    private ProgressDialog dialog;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;

    public SignupInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Bundle bundle = getArguments();
//        userId = bundle.getString("userId");

//        Toast.makeText(getActivity(), userId, Toast.LENGTH_SHORT).show();

//        postInProfileRecyclerView = view.findViewById(R.id.postInProfileRecyclerView);
        FirebaseApp.initializeApp(getContext());
        auth = FirebaseAuth.getInstance();


        //////
        phoneNumberView = view.findViewById(R.id.phoneNumberView);
        signupButton = view.findViewById(R.id.signupButton);
        processText = view.findViewById(R.id.progressResultView);
        processText = view.findViewById(R.id.progressResultView);
        passwordView = view.findViewById(R.id.passwordView);
        countryCodePicker = view.findViewById(R.id.ccp);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumberString = phoneNumberView.getText().toString();
                String passwordString = passwordView.getText().toString();

                String countryCode = countryCodePicker.getSelectedCountryCode();
                String fullPhoneNumber = "+" + countryCode + phoneNumberString;
                if(fullPhoneNumber!= null && fullPhoneNumber!="" && !fullPhoneNumber.equalsIgnoreCase("") && passwordString!= null && passwordString!="" && !passwordString.equalsIgnoreCase("")){

                    dialog = ProgressDialog.show(getContext(), "",
                            "الرجاء الانتظار ..", true);



                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                            .setPhoneNumber(fullPhoneNumber)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(getActivity())
                            .setCallbacks(mCallBacks)
                            .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);

                }
                else{
                    Toast.makeText(getContext(), "الرحاء ادخال رقم الهاتف وكلمة مرور",
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
                        Intent otpIntent = new Intent(getContext(), Verify.class);

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

    private void signIn(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "تم التسجيل بنجاح",
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