package com.uzarsif.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.uzarsif.DaleelActivity;
import com.uzarsif.NetworkHelper;
import com.uzarsif.R;
import com.uzarsif.models.Data;

import java.util.List;

public class DaleelRecyclerViewAdapter extends RecyclerView.Adapter<DaleelRecyclerViewAdapter.MyViewHolder> {

    List<Data> allData;
    Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView text;
        LinearLayout firstlayout;



        public MyViewHolder(View view) {
            super(view);

            image = view.findViewById(R.id.image);
            text = view.findViewById(R.id.text);
            firstlayout = view.findViewById(R.id.firstlayout);

//                layout = view.findViewById(R.id.layout);
        }
    }


    public DaleelRecyclerViewAdapter(List<Data> allData ) {


        this.allData = allData;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daleel_item, parent, false);
        context = parent.getContext();
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Data d = allData.get(position);


        holder.text.setText(d.getTitle());
        holder.text.setText(d.getTitle());


            String url = NetworkHelper.IMAGES_PATH_DATA_PICTURES + d.getImage();
            Log.d("picccc",url);
            Glide.with(context).load(url)
                    .centerCrop()

                    .diskCacheStrategy(DiskCacheStrategy.DATA).into(holder.image);





        holder.firstlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent k = new Intent(context, DaleelActivity.class);

        k.putExtra("childId", d.getId());
        k.putExtra("childTitle", d.getTitle());
        k.putExtra("childText", d.getText());
        k.putExtra("childImage", d.getImage());
                Log.d("TAG", "onClick: +parent = "+d.getId());


        context.startActivity(k);
            }
        });








    }

    @Override
    public int getItemCount() {
        return allData.size();
    }




}


