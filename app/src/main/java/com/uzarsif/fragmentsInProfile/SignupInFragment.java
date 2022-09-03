package com.uzarsif.fragmentsInProfile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.uzarsif.NetworkHelper;
import com.uzarsif.R;
import com.uzarsif.Verify;
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
import com.uzarsif.splash;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SignupInFragment extends Fragment {
    EditText phoneNumberView , passwordView;
    Button signupButton;
    CountryCodePicker ccp;
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
                String fullPhoneNumber =  "+"+countryCode + phoneNumberString;
                if(fullPhoneNumber!= null && fullPhoneNumber!="" && !fullPhoneNumber.equalsIgnoreCase("") && passwordString!= null && passwordString!="" && !passwordString.equalsIgnoreCase("")){
////check if number already used
                    dialog = ProgressDialog.show(getContext(), "",
                            "الرجاء الانتظار ..", true);
                    String url = NetworkHelper.getUrl(NetworkHelper.ACTION_CHECK_PHONE);

                    final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("TAG", "sendData: reach send data and signup" +response);
if(response.contains("true")){
    dialog.dismiss();

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
    dialog.dismiss();
    Toast.makeText(getContext(), "رقم الهاتف مستخدم", Toast.LENGTH_LONG).show();
//
                                }


                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//                        dialog.dismiss();
                                    Toast.makeText(getContext(), "رقم الهاتف مستخدم", Toast.LENGTH_LONG).show();
//                        Log.d("TAG", "onErrorResponse: "+error.getMessage().toString());


                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> params = new HashMap<>();
                            //    Map<String, String> params = new Hashtable<>();

                            params.put("phone", fullPhoneNumber);





                            Log.d("tag", params.toString());
                            return params;

                        }
                    };
                    {
                        int socketTimeout = 30000;
                        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                        stringRequest.setRetryPolicy(policy);
                        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

                        requestQueue.add(stringRequest);
                    }


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
                        String countryCode = countryCodePicker.getSelectedCountryCode();
                        String fullPhoneNumber =  "+"+countryCode + phoneNumberString;


                        otpIntent.putExtra("phone", fullPhoneNumber);
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