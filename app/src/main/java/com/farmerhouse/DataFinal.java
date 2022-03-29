package com.farmerhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DataFinal extends AppCompatActivity {

    ImageView dataImage;
    TextView dataText,text;
    String childId,childText,childTitle,childImage;
    ImageView close,back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_final);
        dataImage = findViewById(R.id.dataImage);
        dataText = findViewById(R.id.dataText);
        text = findViewById(R.id.text);
        close = findViewById(R.id.close);
        back_button = findViewById(R.id.back_button);


        if(getIntent().hasExtra("childId")) {
            childId = getIntent().getStringExtra("childId");
            childTitle = getIntent().getStringExtra("childTitle");
            childText = getIntent().getStringExtra("childText");
            childImage = getIntent().getStringExtra("childImage");

            Log.d("TAG", "onCreateeD: "+childId+childText+childTitle+childImage);
            if(childImage == null){
                dataImage.setVisibility(View.GONE);
            }
            else{
                String url = NetworkHelper.IMAGES_PATH_DATA_PICTURES +childImage ;
                Log.d("picccc",url);
                Glide.with(DataFinal.this).load(url)
                        .centerCrop()

                        .diskCacheStrategy(DiskCacheStrategy.DATA).into(dataImage);

            }


            text.setText(childTitle);
            dataText.setText(childText);

            if(childText == null || childText.equals("")){
                dataText.setText("سيتم الاضافة قريبا..");
            }






        }

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }
}