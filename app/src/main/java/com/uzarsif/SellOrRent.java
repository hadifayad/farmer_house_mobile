package com.uzarsif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SellOrRent extends AppCompatActivity {
    Button be3,daman,okood,daleell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_or_rent);
        be3 = findViewById(R.id.be3);
        daman = findViewById(R.id.daman);
//
//        daleell = findViewById(R.id.daleell);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#81bb28"));
        be3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellOrRent.this, FarmerAkedActivity.class);
                startActivity(i);
                finish();
            }
        });

        daman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SellOrRent.this, AkedDaman.class);
                startActivity(i);
                finish();
            }
        });
    }
}