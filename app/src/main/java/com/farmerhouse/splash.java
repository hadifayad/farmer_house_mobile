package com.farmerhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.farmerhouse.inbox.CreateMessage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class splash extends AppCompatActivity {

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        startActivity(new Intent(this, CreateMessage.class));

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#FFFFFF"));
        start= findViewById(R.id.start);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(splash.this);
        String firstTime = prefs.getString("firstTime", "");
        String userId = prefs.getString("userId", "");


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        SharedPreferences.Editor ed = prefs.edit();

                        ed.putString("token", token);

                        ed.commit();

                        Toast.makeText(splash.this, token, Toast.LENGTH_SHORT).show();
                    }
                });



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TAG", "onClick: "+firstTime);

                if (!firstTime.equals(null) && !firstTime.equals("")) {

                    if (!userId.equals(null) && !userId.equals("")){

                        Intent intent1 = new Intent(splash.this, MainActivity.class);
                        intent1.putExtra("userId", "1");
                        startActivity(intent1);
                        finish();

                    }
                    else {
                        Intent intent1 = new Intent(splash.this, LoginOrSignup.class);

                        startActivity(intent1);
                        finish();
                    }

                }
                else {
                    Intent intent1 = new Intent(splash.this, Tour1.class);

                    startActivity(intent1);
                    finish();
                }


            }
        });
    }
}