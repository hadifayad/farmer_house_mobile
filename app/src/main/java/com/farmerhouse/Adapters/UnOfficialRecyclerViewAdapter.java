package com.farmerhouse.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farmerhouse.R;
import com.farmerhouse.ViewAkedActivity;
import com.farmerhouse.models.MandoubAked;

import java.util.List;

public class UnOfficialRecyclerViewAdapter extends RecyclerView.Adapter<com.farmerhouse.Adapters.UnOfficialRecyclerViewAdapter.MyViewHolder> {

    List<MandoubAked> mandoubAkeds;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {



        TextView date , farmer_name,type,notes;
        RelativeLayout lay;


        public MyViewHolder(View view) {
            super(view);



            farmer_name = view.findViewById(R.id.farmer_name);
            date = view.findViewById(R.id.date);
            type = view.findViewById(R.id.type);
            notes = view.findViewById(R.id.notes);
            lay = view.findViewById(R.id.lay);


        }
    }


    public UnOfficialRecyclerViewAdapter(List<MandoubAked> mandoubAkeds ) {


        this.mandoubAkeds = mandoubAkeds;

    }


    @Override
    public com.farmerhouse.Adapters.UnOfficialRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup
                                                                                                            parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mandoub_aked_item, parent, false);
        context = parent.getContext();
        return new com.farmerhouse.Adapters.UnOfficialRecyclerViewAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final com.farmerhouse.Adapters.UnOfficialRecyclerViewAdapter.MyViewHolder holder, final int position) {
        final MandoubAked aked = mandoubAkeds.get(position);
        String input = aked.getDate();
        String output = input.substring(0, 10);
        holder.date.setText(aked.getDate());
        holder.type.setText(aked.getType());




        holder.notes.setText(aked.getNotes());
        holder.farmer_name.setText(aked.getFarmerId());
//        Log.d("TAG", "onBindViewHolder: "+aked.getActivity_type() + "  "+aked.getFarmer());

        holder.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context , ViewAkedActivity.class);
                i.putExtra("id", aked.getId());
                i.putExtra("place", aked.getPlace());
                i.putExtra("quantity", aked.getQuantity());
                i.putExtra("price", aked.getPrice());
                i.putExtra("date_tesleem", aked.getDate());
                i.putExtra("place_tesleem", aked.getTesleem_place());
                i.putExtra("notes", aked.getNotes());
                i.putExtra("type", aked.getType());
                i.putExtra("fullname", aked.getFarmerId());
                i.putExtra("phone", aked.getPhone());
                i.putExtra("email", aked.getEmail());
                i.putExtra("address", aked.getAddress());
                i.putExtra("village", aked.getVillage());
                Log.d("TAG", "onClick: "+i.getExtras());
                context.startActivity(i);




            }
        });







    }

    @Override
    public int getItemCount() {
        return mandoubAkeds.size();
    }



}
