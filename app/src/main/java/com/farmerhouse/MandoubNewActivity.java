package com.farmerhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.farmerhouse.models.ActivityType;
import com.farmerhouse.models.ProfileData;
import com.farmerhouse.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MandoubNewActivity extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener date;
    private Calendar myCalendar;
    TextView activity_time;
    List farmersValuesArray = new ArrayList() ;
    List typesValuesArray = new ArrayList() ;
    Spinner activity_type,activity_farmer;
    String farmer, type;
    EditText activity_notes;
    Button upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandoub_new);
        activity_time = findViewById(R.id.activity_time);
        activity_notes = findViewById(R.id.activity_notes);
        activity_farmer = findViewById(R.id.activity_farmer);
        activity_type = findViewById(R.id.activity_type);
        upload = findViewById(R.id.upload);
        myCalendar = Calendar.getInstance();
        setDatePicker();
        setMandoubUsers();
        setActivities();

        activity_farmer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                farmer = farmersValuesArray.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        activity_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = typesValuesArray.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uploadData();



            }
        });
    }


    public void selectDate(View view) {
        new DatePickerDialog(MandoubNewActivity.this, date, myCalendar
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
                new TimePickerDialog(MandoubNewActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                myCalendar.set(Calendar.MINUTE, minute);


                                activity_time.setText((new SimpleDateFormat("yyyy-MM-dd kk:mm")).format(myCalendar.getTime()));
                            }
                        }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), false).show();
            }
        };
    }


    public void setMandoubUsers(){


        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_MANDOUB_FARMERS);
        Log.d("url", url.toString());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MandoubNewActivity.this);
        String userId = prefs.getString("userId", "");

        Map<String, String> params = new HashMap();
        params.put("mandoobId", userId);

        GsonRequest<User[]> myGsonRequest = new GsonRequest<User[]>(Request.Method.POST, url, User[].class, null, params,
                new Response.Listener<User[]>() {
                    @Override
                    public void onResponse(User[] response) {

                        String FarmersArray[] = new String[response.length];


                        for (int i = 0; i < response.length; i++) {
                            FarmersArray[i] = response[i].getFullname();
//                            valuesArray[i] = response[i].getId();
                            farmersValuesArray.add(response[i].getId());
                        }




                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MandoubNewActivity.this, R.layout.checked_text_customized, FarmersArray);
                        dataAdapter.setDropDownViewResource(R.layout.dropdown_customized);
                        activity_farmer.setAdapter(dataAdapter);


                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        object.dismiss();
                        NetworkHelper.handleError(error);
                        Log.d("error", "onErrorResponse: " + error.getMessage());
                    }
                });

        VolleySingleton.getInstance(MandoubNewActivity.this).addToRequestQueue(myGsonRequest);





    }

    public void setActivities(){


        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_ACTIVITIES_TYPES);
        Log.d("url", url.toString());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MandoubNewActivity.this);
        String userId = prefs.getString("userId", "");

        Map<String, String> params = new HashMap();
        params.put("mandoobId", userId);

        GsonRequest<ActivityType[]> myGsonRequest = new GsonRequest<ActivityType[]>(Request.Method.POST, url, ActivityType[].class, null, params,
                new Response.Listener<ActivityType[]>() {
                    @Override
                    public void onResponse(ActivityType[] response) {

                        String TypesArray[] = new String[response.length];


                        for (int i = 0; i < response.length; i++) {
                            TypesArray[i] = response[i].getName();
//                            valuesArray[i] = response[i].getId();
                            typesValuesArray.add(response[i].getId());
                            Log.d("TAG", "onResponse: "+response[i].getName());
                        }




                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MandoubNewActivity.this, R.layout.checked_text_customized, TypesArray);
                        dataAdapter.setDropDownViewResource(R.layout.dropdown_customized);
                        activity_type.setAdapter(dataAdapter);


                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        object.dismiss();
                        NetworkHelper.handleError(error);
                        Log.d("error", "onErrorResponse: " + error.getMessage());
                    }
                });

        VolleySingleton.getInstance(MandoubNewActivity.this).addToRequestQueue(myGsonRequest);






    }
    public void uploadData(){
        String url = NetworkHelper.getUrl(NetworkHelper.CREATE_MANDOUB_ACTIVTY);
        Log.d("url", url.toString());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MandoubNewActivity.this);
        String userId = prefs.getString("userId", "");

        Map<String, String> params = new HashMap();


        params.put("mandoobId",userId);
        params.put("activity_type",type);
        params.put("date",activity_time.getText().toString());
        params.put("farmer",farmer);
        params.put("notes",activity_notes.getText().toString());

        Log.d("TAG", "uploadData: "+params.toString());



        GsonRequest<String> myGsonRequest1 = new GsonRequest<String>(Request.Method.POST, url, String.class, null, params,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {




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

        VolleySingleton.getInstance(MandoubNewActivity.this).addToRequestQueue(myGsonRequest1);




    }
}