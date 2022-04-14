package com.uzarsif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.models.ProfileData;
import com.uzarsif.models.User;
import com.uzarsif.models.Village;
import com.uzarsif.ui.MultiSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

MultiSpinner.MultiSpinnerListener listener;
    List valuesArray = new ArrayList() ;
    public String villageId , landVillageId;

Spinner villages,landVillage,landLegal,landWater;
    CheckBox landPond,landPublic,landWell;
    Button upload;
ProgressBar progress;
    String land_state,land_water,land_has_well,land_related_public_water,land_has_pond,land_area,land_id,land_height,land_village;
    EditText landHeight,landId,landArea,fullnameView,secondPhone,email,addressView;
    CheckBox masdar1,masdar2,masdar3,masdar4,masdar5,masdar6,mo3adat1,mo3adat2,mo3adat3,mo3adat4,mo3adat5,mo3adat6,mo3adat7;

    String masdar1String,masdar2String,masdar3String,masdar4String,masdar5String,masdar6String,mo3adat1String,mo3adat2String,mo3adat3String,mo3adat4String,mo3adat5String,mo3adat6String,mo3adat7String;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        villages = findViewById(R.id.SearchableSpinner);
        landVillage = findViewById(R.id.landVillage);
        landLegal = findViewById(R.id.landLegal);
        landLegal = findViewById(R.id.landLegal);
        landWater = findViewById(R.id.landWater);
        landPond = findViewById(R.id.landPond);
        landPublic = findViewById(R.id.landPublic);
        landWell = findViewById(R.id.landWell);
        upload = findViewById(R.id.upload);
        landHeight = findViewById(R.id.landHeight);
        landId = findViewById(R.id.landId);
        addressView = findViewById(R.id.addressView);

        fullnameView = findViewById(R.id.fullnameView);
        secondPhone = findViewById(R.id.secondPhone);
        email = findViewById(R.id.email);
        landArea = findViewById(R.id.landArea);

        masdar1 = findViewById(R.id.masdar1);
        masdar2 = findViewById(R.id.masdar2);
        masdar3 = findViewById(R.id.masdar3);
        masdar4 = findViewById(R.id.masdar4);
        masdar5 = findViewById(R.id.masdar5);
        masdar6 = findViewById(R.id.masdar6);
        mo3adat1 = findViewById(R.id.mo3adat1);
        mo3adat2 = findViewById(R.id.mo3adat2);
        mo3adat3 = findViewById(R.id.mo3adat3);
        mo3adat4 = findViewById(R.id.mo3adat4);
        mo3adat5 = findViewById(R.id.mo3adat5);
        mo3adat6 = findViewById(R.id.mo3adat6);
        mo3adat7 = findViewById(R.id.mo3adat7);


//        progress = findViewById(R.id.progress);
//        progress.setProgress(85);
        getVillages();
        getProfileData();
//        MultiSpinner multiSpinner = (MultiSpinner) findViewById(R.id.masdarTa2a);
//        List<String> items = new ArrayList<>();
//        items.add("hadi");
//        items.add("ali");
//        multiSpinner.setItems(items,"all",listener);
        // Spinner element


        // Spinner click listener
        landLegal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                land_state = landLegal.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        landWater.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                land_water = landWater.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // legatdata spinner
        List<String> landLegalChoices = new ArrayList<String>();
        landLegalChoices.add("ملك خاص");
        landLegalChoices.add("حجة");
        landLegalChoices.add("ملك العائلة");
        landLegalChoices.add("ملك مشترك");
        landLegalChoices.add("ملك الدولة");
        landLegalChoices.add("مرهونة");
        landLegalChoices.add("مؤجرة من المالك الفعلي");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.checked_text_customized, landLegalChoices);
        dataAdapter.setDropDownViewResource(R.layout.dropdown_customized);
        landLegal.setAdapter(dataAdapter);


        // waterType spinner
        List<String> waterTypeChoices = new ArrayList<String>();
        waterTypeChoices.add("الري البعلي");
        waterTypeChoices.add("الري ببالتنقيط");
        waterTypeChoices.add("الري بالرش");
        waterTypeChoices.add("ري بالجر");
        ArrayAdapter<String> waterTypeChoicesAdapter = new ArrayAdapter<String>(this, R.layout.checked_text_customized, waterTypeChoices);
        waterTypeChoicesAdapter.setDropDownViewResource(R.layout.dropdown_customized);
        landWater.setAdapter(waterTypeChoicesAdapter);


        landVillage. setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                land_village = valuesArray.get(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                        if(landWell.isSelected()){
                            land_has_well="1";
                        }
                        else {
                            land_has_well="0";
                        }

                        if(landPond.isSelected()){

                            land_has_pond ="1";
                        }
                        else{
                            land_has_pond ="0";

                        }
                        if(landPublic.isSelected()){
                            land_related_public_water ="1";
                        }
                        else{
                            land_related_public_water="0";
                        }


                land_area = landArea.getText().toString();
                land_id = landId.getText().toString();
                land_height =landHeight.getText().toString();

                if(masdar1.isSelected()){
                    masdar1String ="1";
                }
                else{

                    masdar1String ="0";
                }
                if(masdar2.isSelected()){
                    masdar2String ="1";
                }
                else{

                    masdar2String ="0";
                }
                if(masdar3.isSelected()){
                    masdar3String ="1";
                }
                else{

                    masdar3String ="0";
                }
                if(masdar4.isSelected()){
                    masdar4String ="1";
                }
                else{

                    masdar4String ="0";
                }
                if(masdar5.isSelected()){
                    masdar5String ="1";
                }
                else{

                    masdar5String ="0";
                }
                if(masdar6.isSelected()){
                    masdar6String ="1";
                }
                else{

                    masdar6String ="0";
                }

                if(mo3adat1.isSelected()){
                    mo3adat1String ="1";
                }
                else{

                    mo3adat1String ="0";
                }
                if(mo3adat2.isSelected()){
                    mo3adat2String ="1";
                }
                else{

                    mo3adat2String ="0";
                }
                if(mo3adat3.isSelected()){
                    mo3adat3String ="1";
                }
                else{

                    mo3adat3String ="0";
                }
                if(mo3adat4.isSelected()){
                    mo3adat4String ="1";
                }
                else{

                    mo3adat4String ="0";
                }
                if(mo3adat5.isSelected()){
                    mo3adat5String ="1";
                }
                else{

                    mo3adat5String ="0";
                }
                if(mo3adat6.isSelected()){
                    mo3adat6String ="1";
                }
                else{

                    mo3adat6String ="0";
                }
                if(mo3adat7.isSelected()){
                    mo3adat7String ="1";
                }
                else{

                    mo3adat7String ="0";
                }


                uploadData();


            }
        });
















    }



    public void getVillages()

        {
            String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_VILLAGES);
            Log.d("url", url.toString());


//        testanim object=new testanim(AddPost.this);
//        object.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        object.show();

            Map<String, String> params1 = new HashMap();


            GsonRequest<Village[]> myGsonRequest1 = new GsonRequest<Village[]>(Request.Method.POST, url, Village[].class, null, params1,
                    new Response.Listener<Village[]>() {
                        @Override
                        public void onResponse(Village[] response) {

                            Log.d("TAG", "onResponse:length " + response.length);

                            String VillageArray[] = new String[response.length];


                            for (int i = 0; i < response.length; i++) {
                                VillageArray[i] = response[i].getName();
//                            valuesArray[i] = response[i].getId();
                                valuesArray.add(response[i].getId());
                            }


// Application of the Array to the Spinner
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(EditProfileActivity.this, android.R.layout.simple_spinner_item, VillageArray);
                            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view


                            villages.setAdapter(spinnerArrayAdapter);
                            landVillage.setAdapter(spinnerArrayAdapter);
//                            landVillage.setSelection(valuesArray.get(Integer.parseInt(landVillageId)));
//                            Log.d("TAG", "onResponse: "+landVillageId +"length"+ valuesArray.get(Integer.parseInt(landVillageId)));
//                            for(int i =0 ; i<valuesArray.size();i++){
//                                if(valuesArray.get(i)==landVillageId){
//                                    Log.d("TAG", "onResponse: l id hwwwwee"+i+"name"+response[i]);
//                                }
//
//                            }
//                        object.dismiss();


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

            VolleySingleton.getInstance(EditProfileActivity.this).addToRequestQueue(myGsonRequest1);

        }

public void uploadData(){


    String url = NetworkHelper.getUrl(NetworkHelper.ACTION_UPDATE_PROFILE);
    Log.d("url", url.toString());
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(EditProfileActivity.this);
    String userId = prefs.getString("userId", "");

    Map<String, String> params = new HashMap();
    params.put("land_height",land_height);
    params.put("land_id",land_id);
    params.put("land_area",land_area);
    params.put("land_related_public_water",land_related_public_water);
    params.put("land_has_pond",land_has_pond);
    params.put("land_has_well",land_has_well);
    params.put("land_village",land_village);
    params.put("land_state",land_state);
    params.put("land_water",land_water);
    params.put("human",masdar1String);
    params.put("animals",masdar2String);
    params.put("automatic_energy",masdar3String);
    params.put("wind_energy",masdar4String);
    params.put("solar_energy",masdar5String);
    params.put("electricity",masdar6String);
    params.put("jarrar",mo3adat1String);
    params.put("rash",mo3adat2String);
    params.put("maktoura",mo3adat3String);
    params.put("sahreej",mo3adat4String);
    params.put("mdakha",mo3adat5String);
    params.put("shabaket_ray",mo3adat6String);
    params.put("alat",mo3adat7String);
    params.put("userId",userId);
    Log.d("TAG", "uploadData: "+params.toString());



    GsonRequest<User> myGsonRequest1 = new GsonRequest<User>(Request.Method.POST, url, User.class, null, params,
            new Response.Listener<User>() {
                @Override
                public void onResponse(User response) {




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

    VolleySingleton.getInstance(EditProfileActivity.this).addToRequestQueue(myGsonRequest1);






}

public void getProfileData(){

    String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_PROFILE_DATA);
    Log.d("url", url.toString());


//        testanim object=new testanim(AddPost.this);
//        object.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        object.show();
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(EditProfileActivity.this);
    String userId = prefs.getString("userId", "");

    Map<String, String> params1 = new HashMap();
    params1.put("userId",userId);

    GsonRequest<ProfileData> myGsonRequest = new GsonRequest<ProfileData>(Request.Method.POST, url, ProfileData.class, null, params1,
            new Response.Listener<ProfileData>() {
                @Override
                public void onResponse(ProfileData response)





                {
                    Log.d("TAG", "onResponse: "+response.toString());
//                    villages = findViewById(R.id.SearchableSpinner);
//                    landVillage = findViewById(R.id.landVillage);
//                    landLegal = findViewById(R.id.landLegal);
//
//                    landWater = findViewById(R.id.landWater);
//                    landPond = findViewById(R.id.landPond);
//                    landPublic = findViewById(R.id.landPublic);
//                    landWell = findViewById(R.id.landWell);

                    landHeight.setText(response.getLand_height());
                    landId.setText(response.getLand_id());
                    addressView.setText(response.getAddress());

                    fullnameView.setText(response.getFullname());
                    secondPhone.setText(response.getSecond_phone());
                    email.setText(response.getEmail());
                    landArea.setText(response.getLand_area());
                    Log.d("TAG", "onResponse: "+valuesArray.size() +" land "+response.getLand_village());
//                  villages.setSelection(Integer.parseInt(response.getVillage()));
//                  villages.setSelection(Integer.parseInt(response.getLand_village()));
                    villageId =  response.getVillage();
                    landVillageId =  response.getLand_village();
                    if(response.getLand_has_well().toString().equals("1")){
                        landWell.setChecked(true);
                    }
                    if(response.getLand_has_pond().toString().equals("1")){
                        landPond.setChecked(true);
                    }
                    if(response.getLand_related_public_water().toString().equals("1")){
                        landPublic.setChecked(true);
                    }
                    if(response.getHuman().toString().equals("1")){
                        masdar1.setChecked(true);
                    }
                    if(response.getAnimals().toString().equals("1")){
                        masdar2.setChecked(true);
                    }
                    if(response.getAutomatic_energy().toString().equals("1")){
                        masdar3.setChecked(true);
                    }
                    if(response.getWind_energy().toString().equals("1")){
                        masdar4.setChecked(true);
                    }
                    if(response.getSolar_energy().toString().equals("1")){
                        masdar5.setChecked(true);
                    }
                    if(response.getElectricity().toString().equals("1")){
                        masdar6.setChecked(true);
                    }
                    if(response.getJarrar().toString().equals("1")){
                        mo3adat1.setChecked(true);
                    }
                    if(response.getRash().toString().equals("1")){
                        mo3adat2.setChecked(true);
                    }
                    if(response.getAlat().toString().equals("1")){
                        mo3adat3.setChecked(true);
                    }
                    if(response.getMaktoura().toString().equals("1")){
                        mo3adat4.setChecked(true);
                    }
                    if(response.getSahreej().toString().equals("1")){
                        mo3adat5.setChecked(true);
                    }
                    if(response.getMdakha().toString().equals("1")){
                        mo3adat6.setChecked(true);
                    }
                    if(response.getShabaket_ray().toString().equals("1")){
                        mo3adat7.setChecked(true);
                    }




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

    VolleySingleton.getInstance(EditProfileActivity.this).addToRequestQueue(myGsonRequest);
}





}