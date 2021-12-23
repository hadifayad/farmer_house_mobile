package com.farmerhouse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farmerhouse.CreateMessage;
import com.farmerhouse.R;
import com.farmerhouse.models.Data;
import com.farmerhouse.models.Message;

import java.util.List;

public class ChoicesRecyclerView extends RecyclerView.Adapter<ChoicesRecyclerView.MyViewHolder> {


    List<Data> Choices;
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


    public ChoicesRecyclerView(List<Data> Choices) {


        this.Choices = Choices;
    }


    @Override
    public ChoicesRecyclerView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choices_item, parent, false);
        context = parent.getContext();
        return new ChoicesRecyclerView.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ChoicesRecyclerView.MyViewHolder holder, final int position) {
        final Data choice = Choices.get(position);

        holder.title.setText(choice.getTitle());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                DataRecyclerViewAdapter dataRecyclerViewAdapter = new DataRecyclerViewAdapter(choice);
//                CreateMessage.choicesRecyclerView.setAdapter(dataRecyclerViewAdapter);
            }
        });





    }

    @Override
    public int getItemCount() {
        return Choices.size();
    }


}
