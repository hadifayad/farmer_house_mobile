package com.uzarsif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.models.ProfileData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AkedDaman extends AppCompatActivity {




        EditText place,quantity,price,date_tesleem,place_tesleem,notes,type,duration;
        TextView fullnameView,phone,email,addressView,SearchableSpinner;
        List farmersValuesArray = new ArrayList() ;
        List typesValuesArray = new ArrayList() ;
        Spinner activity_type,activity_farmer;
        String farmer;
        Button upload;
        DatePickerDialog.OnDateSetListener date;
        private Calendar myCalendar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_aked_daman);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#cc9f53"));

            place = findViewById(R.id.place);
            quantity = findViewById(R.id.quantity);
            price = findViewById(R.id.price);
            date_tesleem = findViewById(R.id.date_tesleem);
            place_tesleem = findViewById(R.id.place_tesleem);
            place = findViewById(R.id.place);
            notes = findViewById(R.id.notes);
            type = findViewById(R.id.type);
            fullnameView = findViewById(R.id.fullnameView);
            phone = findViewById(R.id.phone);
            email = findViewById(R.id.email);
            addressView = findViewById(R.id.addressView);
            activity_farmer = findViewById(R.id.activity_farmer);
            upload = findViewById(R.id.upload);
            duration = findViewById(R.id.duration);
            SearchableSpinner = findViewById(R.id.SearchableSpinner);
            myCalendar = Calendar.getInstance();
            setDatePicker();
            getProfileData();



            getProfileData();




            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadAked();
                }
            });




        }
        public void getProfileData(){

            String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_PROFILE_DATA);
            Log.d("url", url.toString());


            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AkedDaman.this);
            String userId = prefs.getString("userId", "");

            Map<String, String> params1 = new HashMap();
            params1.put("userId",userId);

            GsonRequest<ProfileData> myGsonRequest = new GsonRequest<ProfileData>(Request.Method.POST, url, ProfileData.class, null, params1,
                    new Response.Listener<ProfileData>() {
                        @Override
                        public void onResponse(ProfileData response)





                        {
//

                            if(response!=null){
                                Log.d("TAG", "onResponse: "+response.getAddress());
                                addressView.setText(response.getAddress());

                                fullnameView.setText(response.getFullname());
                                phone.setText(response.getPhone());
                                email.setText(response.getEmail());
                                SearchableSpinner.setText(response.getVillage());

                            }



//                        villageId =  response.getVillage();






                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                        object.dismiss();
                            NetworkHelper.handleError(error);
                            Log.d("error", "onErrorResponse: " + error.getMessage());
                        }
                    });

            VolleySingleton.getInstance(AkedDaman.this).addToRequestQueue(myGsonRequest);
        }



        public void uploadAked(){

            String url = NetworkHelper.getUrl(NetworkHelper.CREATE_FARMER_AKED);
            Log.d("url", url.toString());
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AkedDaman.this);
            String userId = prefs.getString("userId", "");
            if(!userId.equals("") && !place.getText().toString().equals("")
                    && !quantity.getText().toString().equals("") && !price.getText().toString().equals("")
                    && !notes.getText().toString().equals("")

            ){
                Map<String, String> params = new HashMap();






                params.put("quantity","");
                params.put("price",price.getText().toString());
                params.put("date","");
                params.put("place_tesleem","");
                params.put("place",place.getText().toString());
                params.put("notes",notes.getText().toString());
                params.put("type","");
                params.put("area",quantity.getText().toString());
                params.put("duration",duration.getText().toString());



                params.put("mandoobId",prefs.getString("mandoobId","4"));
                params.put("farmer",userId);


                Log.d("TAG", "uploadData: "+params.toString());



                GsonRequest<String> myGsonRequest1 = new GsonRequest<String>(Request.Method.POST, url, String.class, null, params,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                finish();


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                        object.dismiss();
                                NetworkHelper.handleError(error);
                                Log.d("error", "onErrorResponse: " + error.getMessage());
                            }
                        });

                VolleySingleton.getInstance(AkedDaman.this).addToRequestQueue(myGsonRequest1);


            }
            else {

                Toast.makeText(AkedDaman.this, "الرجاء تعبئة جميع الحقول", Toast.LENGTH_SHORT).show();
            }







        }


    public void selectDate(View view) {
        new DatePickerDialog(AkedDaman.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    private void setDatePicker() {

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();
                new TimePickerDialog(AkedDaman.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                myCalendar.set(Calendar.MINUTE, minute);


                                date_tesleem.setText((new SimpleDateFormat("yyyy-MM-dd kk:mm")).format(myCalendar.getTime()));
                            }
                        }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), false).show();
            }
        };
    }


}