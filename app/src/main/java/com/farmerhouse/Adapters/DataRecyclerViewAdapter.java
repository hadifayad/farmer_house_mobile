package com.farmerhouse.Adapters;

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
import com.farmerhouse.GsonRequest;
import com.farmerhouse.NetworkHelper;
import com.farmerhouse.R;
import com.farmerhouse.VolleySingleton;
import com.farmerhouse.models.Data;
import com.farmerhouse.models.DataMessages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.MyViewHolder> implements ChoicesRecyclerView.TextChoosen {


    List<DataMessages> dataMessages;
    String chatId;

    Context context;

    @Override
    public void sendTextChoosen(String id, String text) {
        int lastPosition = getItemCount() - 1;
        if (lastPosition >= 0) {
            String title = this.dataMessages.get(lastPosition).getText();
            if (title != null && !title.equals("")) {

            } else {
                this.dataMessages.get(lastPosition).setId(id);
                this.dataMessages.get(lastPosition).setText(text);
                notifyItemChanged(lastPosition);

                getNextChoices(lastPosition, id);
            }
        }
//        notifyDataSetChanged();
//        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }

    public void getNextChoices(int lastPosition, String id) {

//        final ProgressDialog dialog = ProgressDialog.show(context, "",
//                "Please wait...", true);

        // volley
        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_CHILDREN_AND_SAVE_MESSAGE);
        Log.d("url", url.toString());
        Map<String, String> params = new HashMap();
        params.put("parentId", id);
        params.put("chatId", chatId);
        GsonRequest<Data[]> myGsonRequest = new GsonRequest<com.farmerhouse.models.Data[]>(Request.Method.POST, url, com.farmerhouse.models.Data[].class, null, params,
                new Response.Listener<com.farmerhouse.models.Data[]>() {
                    @Override
                    public void onResponse(com.farmerhouse.models.Data[] response) {
//                        dialog.dismiss();
                        DataMessages dataMessage = new DataMessages();
                        dataMessage.setData(Arrays.asList(response));
                        dataMessages.add(dataMessage);
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        dialog.dismiss();
                        NetworkHelper.handleError(error);
                    }
                });

        VolleySingleton.getInstance(context).addToRequestQueue(myGsonRequest);


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public LinearLayout layoutChoosen;
        public RecyclerView choicesRecyclerView;

        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.text);
            choicesRecyclerView = view.findViewById(R.id.choicesRecyclerView);

            layoutChoosen = view.findViewById(R.id.layoutChoosen);
        }
    }


    public DataRecyclerViewAdapter(List<DataMessages> dataMessages, String chatId) {
        this.dataMessages = dataMessages;
        this.chatId = chatId;
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
        final DataMessages dataMessage = dataMessages.get(position);
        List<Data> data = dataMessage.getData();
        Log.d("TAG", "onBindViewHolder: " + dataMessage.getId());

        String title = dataMessage.getText();


        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.choicesRecyclerView.setLayoutManager(layoutManager);

        boolean isLastPosition = false;
        if (position == getItemCount() - 1) {
            isLastPosition = true;
        }
        ChoicesRecyclerView choicesRecyclerViewAdapter;
        if(!chatId.equals("0")){
            choicesRecyclerViewAdapter = new ChoicesRecyclerView(data, this,true);
        }else{
            choicesRecyclerViewAdapter = new ChoicesRecyclerView(data, this,isLastPosition);
        }
        holder.choicesRecyclerView.setAdapter(choicesRecyclerViewAdapter);

        if (title != null && !title.equals("")) {
            holder.layoutChoosen.setVisibility(View.VISIBLE);
            holder.text.setText(dataMessage.getText());
        } else {
            holder.layoutChoosen.setVisibility(View.GONE);
        }

//        final ProgressDialog dialog = ProgressDialog.show(context, "",
//                "Please wait...", true);

//        // volley
//        String url = NetworkHelper.getUrl(NetworkHelper.ACTION_GET_CHILDREN);
//        Log.d("url", url.toString());
//        Map<String, String> params = new HashMap();
//        params.put("parentId", data.getId());
//        GsonRequest<Data[]> myGsonRequest = new GsonRequest<com.farmerhouse.models.Data[]>(Request.Method.POST, url, com.farmerhouse.models.Data[].class, null, params,
//                new Response.Listener<com.farmerhouse.models.Data[]>() {
//                    @Override
//                    public void onResponse(com.farmerhouse.models.Data[] response) {
////                                    Log.d("TAG", "onResponse: " + response[0].getDate());
//                        dialog.dismiss();
//                        ChoicesRecyclerView choicesRecyclerViewAdapter = new ChoicesRecyclerView(Arrays.asList(response));
//                        holder.choicesRecyclerView.setAdapter(choicesRecyclerViewAdapter);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        dialog.dismiss();
//                        NetworkHelper.handleError(error);
//                    }
//                });
//
//        VolleySingleton.getInstance(context).addToRequestQueue(myGsonRequest);

    }

    @Override
    public int getItemCount() {
        return dataMessages.size();
    }


}
