package com.farmerhouse;

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

public class splash extends AppCompatActivity {

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#FFFFFF"));
        start= findViewById(R.id.start);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(splash.this);
        String firstTime = prefs.getString("firstTime", "");
        String userId = prefs.getString("userId", "");



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