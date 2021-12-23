package com.farmerhouse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farmerhouse.CreateMessage;
import com.farmerhouse.R;
import com.farmerhouse.models.Data;

import java.util.List;

public class TopParentRecyclerView extends RecyclerView.Adapter<TopParentRecyclerView.MyViewHolder> {


    List<Data> AllData;
    List<Data> TopParent;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView bullet;
        public RelativeLayout layout;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);

            bullet = view.findViewById(R.id.bullet);
            layout = view.findViewById(R.id.layout);
        }
    }


    public TopParentRecyclerView(List<Data> TopParent) {


        this.TopParent = TopParent;
    }


    @Override
    public TopParentRecyclerView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choices_item, parent, false);
        context = parent.getContext();
        return new TopParentRecyclerView.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final TopParentRecyclerView.MyViewHolder holder, final int position) {
        final Data parent = TopParent.get(position);

        holder.title.setText(parent.getTitle());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                DataRecyclerViewAdapter dataRecyclerViewAdapter = new DataRecyclerViewAdapter(choice);
//                CreateMessage.choicesRecyclerView.setAdapter(dataRecyclerViewAdapter);


//                Data akedData = new Data();
//                Data  daleelData=new Data();
//                akedData.setId("2");
//                daleelData.setId("1");
//                AllData.add(akedData);
//                DataRecyclerViewAdapter dataRecyclerViewAdapter = new DataRecyclerViewAdapter(AllData);
//                dataRecyclerView.setAdapter(dataRecyclerViewAdapter);
            }
        });





    }

    @Override
    public int getItemCount() {
        return TopParent.size();
    }

}
