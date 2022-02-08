package com.farmerhouse;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.farmerhouse.ansabMazro3at.models.Zera3aTypes;
import com.farmerhouse.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
   public List<Zera3aTypes> zera3aTypes;
   public String plantId;

    public CustomDialog(Activity a, List<Zera3aTypes> zera3aTypes,String plantId) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.zera3aTypes = zera3aTypes;
        this.plantId = plantId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cutom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                addActivity();
                c.finish();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    public void addActivity(){



        if (this.zera3aTypes.size()==8) { // all items are selected

//            Log.d("TAG", "check: "+zera3aTypes.get(0).getName()+zera3aTypes.get(0).getResultId());
//            Log.d("TAG", "check: "+zera3aTypes.get(1).getName()+zera3aTypes.get(1).getResultId());
//            Log.d("TAG", "check: "+zera3aTypes.get(2).getName()+zera3aTypes.get(2).getResultId());

            String heightId = zera3aTypes.get(2).getResultId();
            String plantingTypeId = zera3aTypes.get(6).getResultId();
            String plantsTypeId = zera3aTypes.get(7).getResultId();
            String waterTypeId = zera3aTypes.get(4).getResultId();
            String soilTypeId = zera3aTypes.get(1).getResultId();
            String mantaaId = zera3aTypes.get(3).getResultId();
            String mazrouatTypeId = zera3aTypes.get(5).getResultId();
            String mawsem_id = zera3aTypes.get(0).getResultId();


        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_ADD_ACTIVITY);
        Log.d("url", url.toString());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String userId = prefs.getString("userId", "");

        Map<String, String> params = new HashMap();
        params.put("user_id",userId);
        params.put("plant_id",plantId);
        params.put("heightId",heightId);
        params.put("plantingTypeId",plantingTypeId);
        params.put("plantsTypeId",plantsTypeId);
        params.put("waterTypeId",waterTypeId);
        params.put("soilTypeId",soilTypeId);
        params.put("mantaaId",mantaaId);
        params.put("mazrouatTypeId",mazrouatTypeId);
        params.put("mawsem_id",mawsem_id);



        Log.d("TAG", "uploadData: "+params.toString());



        GsonRequest<User> myGsonRequest1 = new GsonRequest<User>(Request.Method.POST, url, User.class, null, params,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {

                        Log.d("TAG", "onResponse: "+response.getFullname());


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

        VolleySingleton.getInstance(getContext()).addToRequestQueue(myGsonRequest1);

    }}
}