package com.farmerhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewAkedActivity extends AppCompatActivity {

    TextView place,quantity,price,date_tesleem,place_tesleem,notes,type,activity_farmer,village;
    TextView fullnameView,phone,email,addressView;
    List farmersValuesArray = new ArrayList() ;
    List typesValuesArray = new ArrayList() ;
    Spinner activity_type;
    String farmer;
    Button upload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_aked);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#cc9f53"));

        place = findViewById(R.id.place);
        quantity = findViewById(R.id.quantity);
        price = findViewById(R.id.price);
        date_tesleem = findViewById(R.id.date_tesleem);
        place_tesleem = findViewById(R.id.place_tesleem);
        village = findViewById(R.id.village);

        notes = findViewById(R.id.notes);
        type = findViewById(R.id.type);
        fullnameView = findViewById(R.id.fullnameView);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        addressView = findViewById(R.id.addressView);

        if(getIntent().hasExtra("id")){

            place.setText(getIntent().getStringExtra("place").toString());
            quantity.setText(getIntent().getStringExtra("quantity").toString());
            price.setText(getIntent().getStringExtra("price").toString());
            date_tesleem.setText(getIntent().getStringExtra("date_tesleem"));
            place_tesleem.setText(getIntent().getStringExtra("place_tesleem").toString());

            notes.setText(getIntent().getStringExtra("notes").toString());
            type.setText(getIntent().getStringExtra("type").toString());
            fullnameView.setText(getIntent().getStringExtra("fullname").toString());
            phone.setText(getIntent().getStringExtra("phone").toString());
            email.setText(getIntent().getStringExtra("email").toString());
            addressView.setText(getIntent().getStringExtra("address").toString());
            village.setText(getIntent().getStringExtra("village").toString());






//            Log.d("TAG", "onCreate: phone and pass" +phoneNumberString +" "+passwordString);
        }







    }
}