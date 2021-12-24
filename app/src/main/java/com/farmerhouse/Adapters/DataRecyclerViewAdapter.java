package com.farmerhouse.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.farmerhouse.CreateMessage;
import com.farmerhouse.GsonRequest;
import com.farmerhouse.NetworkHelper;
import com.farmerhouse.R;
import com.farmerhouse.VolleySingleton;
import com.farmerhouse.models.Data;
import com.farmerhouse.models.Message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.MyViewHolder>{


    List<Data> AllData;

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public LinearLayout layout;
       public RecyclerView choicesRecyclerView;

        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.text);
            choicesRecyclerView = view.findViewById(R.id.choicesRecyclerView);

//                layout = view.findViewById(R.id.layout);
        }
    }


    public DataRecyclerViewAdapter(List<Data> AllData) {


        this.AllData = AllData;
    }


    @Override
    public DataRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_item, parent, false);
        context = parent.getContext();
        return new DataRecyclerViewAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final DataRecyclerViewAdapter.MyViewHolder holder, final int position) {
        final Data data = AllData.get(position);
        Log.d("TAG", "onBindViewHolder: "+data.getId());




        holder.text.setText(data.getTitle());
        {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            holder.choicesRecyclerView.setLayoutManager(layoutManager);
            final ProgressDialog dialog = ProgressDialog.show(context, "",
                    "Please wait...", true);

            // volley
            String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_CHILDREN);
            Log.d("url", url.toString());
            Map<String, String> params = new HashMap();
            params.put("parentId", data.getId());
            GsonRequest<Data[]> myGsonRequest = new GsonRequest<com.farmerhouse.models.Data[]>(Request.Method.POST, url, com.farmerhouse.models.Data[].class, null, params,
                    new Response.Listener<com.farmerhouse.models.Data[]>() {
                        @Override
                        public void onResponse(com.farmerhouse.models.Data[] response) {
//                                    Log.d("TAG", "onResponse: " + response[0].getDate());
                            dialog.dismiss();
                            ChoicesRecyclerView choicesRecyclerViewAdapter = new ChoicesRecyclerView(Arrays.asList(response));
                            holder.choicesRecyclerView.setAdapter(choicesRecyclerViewAdapter);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dialog.dismiss();
                            NetworkHelper.handleError(error);
                        }
                    });

            VolleySingleton.getInstance(context).addToRequestQueue(myGsonRequest);
        }




    }

    @Override
    public int getItemCount() {
        return AllData.size();
    }


}
