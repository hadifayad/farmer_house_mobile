package com.farmerhouse.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farmerhouse.R;
import com.farmerhouse.models.MandoubActivity;
import com.farmerhouse.models.MandoubAked;

import java.util.List;

public class OfficialAkedRecyclerViewAdapter extends RecyclerView.Adapter<com.farmerhouse.Adapters.OfficialAkedRecyclerViewAdapter.MyViewHolder> {

    List<MandoubAked> mandoubAkeds;
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


    public OfficialAkedRecyclerViewAdapter(List<MandoubAked> mandoubAkeds ) {


        this.mandoubAkeds = mandoubAkeds;

    }


    @Override
    public com.farmerhouse.Adapters.OfficialAkedRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup
                                                                                                                 parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mandoub_aked_item, parent, false);
        context = parent.getContext();
        return new com.farmerhouse.Adapters.OfficialAkedRecyclerViewAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final com.farmerhouse.Adapters.OfficialAkedRecyclerViewAdapter.MyViewHolder holder, final int position) {
        final MandoubAked aked = mandoubAkeds.get(position);
        String input = aked.getDate();
        String output = input.substring(0, 10);
        holder.date.setText(aked.getDate());
        holder.type.setText(aked.getType());




        holder.notes.setText(aked.getNotes());
        holder.farmer_name.setText(aked.getFarmerId());
//        Log.d("TAG", "onBindViewHolder: "+aked.getActivity_type() + "  "+aked.getFarmer());






    }

    @Override
    public int getItemCount() {
        return mandoubAkeds.size();
    }



}
