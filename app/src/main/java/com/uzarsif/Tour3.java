package com.uzarsif;

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

public class Tour3 extends AppCompatActivity {
    Button tour3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour3);
        tour3 = findViewById(R.id.tour3);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#81bb28"));

        tour3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Tour3.this);
                SharedPreferences.Editor ed = prefs.edit();
                ed.putString("firstTime","1");
                ed.commit();
                String firstTime = prefs.getString("firstTime", "");
                Log.d("TAG", "onClick tour3: "+firstTime);
                Intent i = new Intent(Tour3.this, LoginOrSignup.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
                finish();
            }
        });
    }
}