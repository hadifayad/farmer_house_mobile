package com.uzarsif.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.uzarsif.R;
import com.uzarsif.models.MandoubActivity;

import java.util.List;

public class MandoubActivitiesRecyclerViewAdapter extends RecyclerView.Adapter<com.uzarsif.Adapters.MandoubActivitiesRecyclerViewAdapter.MyViewHolder> {

    List<MandoubActivity> UserActivities;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {



        TextView date , farmer_name,type,notes;



        public MyViewHolder(View view) {
            super(view);



            farmer_name = view.findViewById(R.id.farmer_name);
            date = view.findViewById(R.id.date);
            type = view.findViewById(R.id.type);
            notes = view.findViewById(R.id.notes);


        }
    }


    public MandoubActivitiesRecyclerViewAdapter(List<MandoubActivity> UserActivities ) {


        this.UserActivities = UserActivities;

    }


    @Override
    public com.uzarsif.Adapters.MandoubActivitiesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup
                                                                                                          parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mandoub_activity_item, parent, false);
        context = parent.getContext();
        return new com.uzarsif.Adapters.MandoubActivitiesRecyclerViewAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final com.uzarsif.Adapters.MandoubActivitiesRecyclerViewAdapter.MyViewHolder holder, final int position) {
        final MandoubActivity activity = UserActivities.get(position);
        String input = activity.getDate();
        String output = input.substring(0, 10);
        holder.date.setText(activity.getDate());
        holder.type.setText(activity.getActivity_type());




        holder.notes.setText(activity.getNotes());
        holder.farmer_name.setText(activity.getFarmer());
        Log.d("TAG", "onBindViewHolder: "+activity.getActivity_type() + "  "+activity.getFarmer());






    }

    @Override
    public int getItemCount() {
        return UserActivities.size();
    }



}
