package com.farmerhouse.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
    public void sendTextChoosen(Data s) {
        int lastPosition = getItemCount() - 1;


        if (lastPosition >= 0) {
            String title = this.dataMessages.get(lastPosition).getText();
            if (title != null && !title.equals("")) {

            } else {
                Log.d("TAG", "sendTextChoosen: else");
                this.dataMessages.get(lastPosition).setId(s.getId());
                this.dataMessages.get(lastPosition).setText(s.getTitle());
                this.dataMessages.get(lastPosition).setImage(s.getImage());
                this.dataMessages.get(lastPosition).setDescription(s.getText());
                this.dataMessages.get(lastPosition).setChildren(s.getChildren());
                if (s.getChildren().equals("0")) {
                    this.dataMessages.get(lastPosition).setLastChild(true);
                } else {
                    this.dataMessages.get(lastPosition).setLastChild(false);
                    getNextChoices(lastPosition, s.getId());
                }
                notifyItemChanged(lastPosition);
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
                        if (response.length == 0) { // no data available
                            dataMessages.get(lastPosition).setLastChild(true); // we added this because of the first item
//                            Toast.makeText(context, dataMessages.get(lastPosition).getImage() + "", Toast.LENGTH_SHORT).show();
                        } else {
                            dataMessages.get(lastPosition).setLastChild(false);
                            //                        dialog.dismiss();
                            DataMessages dataMessage = new DataMessages();
                            dataMessage.setData(Arrays.asList(response));
//                        Log.d("TAG", "onResponse: respose "+response[0].getChildren());
                            dataMessages.add(dataMessage);

                        }
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
        RelativeLayout header;
        ImageView dataImage;
        TextView dataText;

        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.text);
            choicesRecyclerView = view.findViewById(R.id.choicesRecyclerView);

            layoutChoosen = view.findViewById(R.id.layoutChoosen);
            header = view.findViewById(R.id.thirdlayout);
            dataText = view.findViewById(R.id.dataText);
            dataImage = view.findViewById(R.id.dataImage);
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

    public void ShowDetailOfLastData() {

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
        if (!chatId.equals("0")) {
            choicesRecyclerViewAdapter = new ChoicesRecyclerView(data, this, true);
        } else {
            choicesRecyclerViewAdapter = new ChoicesRecyclerView(data, this, isLastPosition);
        }
        holder.choicesRecyclerView.setAdapter(choicesRecyclerViewAdapter);

        if (title != null && !title.equals("")) {
            holder.layoutChoosen.setVisibility(View.VISIBLE);
            holder.text.setText(dataMessage.getText());
        } else {
            holder.layoutChoosen.setVisibility(View.GONE);
        }

        if (dataMessage.isLastChild()) {
            holder.dataText.setVisibility(View.GONE);

            if (dataMessage.getImage() != null && !dataMessage.getImage().equals("")) {
                Glide.with(context)
                        .load(NetworkHelper.IMAGES_PATH_DATA_PICTURES + dataMessage.getImage())
                        .centerCrop()
                        .into(holder.dataImage);
                holder.dataImage.setVisibility(View.VISIBLE);
            } else {
                holder.dataImage.setVisibility(View.GONE);

            }
            if (dataMessage.getText() != null && !dataMessage.getText().equals("")) {
                holder.dataText.setText(dataMessage.getDescription());
                holder.dataText.setVisibility(View.VISIBLE);
            } else {
                holder.dataText.setVisibility(View.GONE);
            }
        }else{
            holder.dataText.setVisibility(View.VISIBLE);
        }

        /////honaaaaaaaaaa
//if(){
//    holder.dataText.setText();
//
//holder.header.setBackground(ContextCompat.getDrawable(context, R.drawable.data_chat));
//    Log.d("picccc",url);
//    Glide.with(getContext()).load(url)
//            .centerCrop() .diskCacheStrategy(DiskCacheStrategy.DATA).into(holder.dataImage);
//
//
//}


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
