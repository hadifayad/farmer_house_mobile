package com.uzarsif.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.uzarsif.AnsabMazro3atActivity;
import com.uzarsif.R;
import com.uzarsif.models.Plant;

import java.util.List;

public class PlantsRecyclerView extends RecyclerView.Adapter<PlantsRecyclerView.MyViewHolder> {


    List<Plant> plants;
    Context context;

    PlantInterface mCallbackPlantClicked;

    public interface PlantInterface {
        public void plantClicked(Plant plant);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView bullet;
        public RelativeLayout layout;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.plantName);

            bullet = view.findViewById(R.id.bullet);
            layout = view.findViewById(R.id.layout);
        }
    }


    public PlantsRecyclerView(List<Plant> plants, AnsabMazro3atActivity mCallbackPlantClicked) {
//        Log.d("TAG", "PlantsRecyclerView: " + plants.get(0).getName());

        this.plants = plants;
        this.mCallbackPlantClicked = (PlantInterface) mCallbackPlantClicked;
    }


    @Override
    public PlantsRecyclerView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plant_item, parent, false);

        context = parent.getContext();
        return new PlantsRecyclerView.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final PlantsRecyclerView.MyViewHolder holder, final int position) {
        final Plant plant = plants.get(position);


        Log.d("TAG", "onBindViewHolder: " + plant.getName());

        holder.title.setText(plant.getName());

        if ((position % 8) == 7) {
            holder.bullet.setBackground(context.getResources().getDrawable(R.drawable.deep_green_shape));


        } else if ((position % 8) == 0) {
            holder.bullet.setBackground(context.getResources().getDrawable(R.drawable.light_blue_shape));
        } else if ((position % 8) == 1) {
            holder.bullet.setBackground(context.getResources().getDrawable(R.drawable.violet_shape));
        } else if ((position % 8) == 2) {
            holder.bullet.setBackground(context.getResources().getDrawable(R.drawable.light_green_shape));
        } else if ((position % 8) == 3) {
            holder.bullet.setBackground(context.getResources().getDrawable(R.drawable.turq_shape));
        } else if ((position % 8) == 4) {
            holder.bullet.setBackground(context.getResources().getDrawable(R.drawable.green_shape));
        } else if ((position % 8) == 5) {
            holder.bullet.setBackground(context.getResources().getDrawable(R.drawable.purpple_shape));

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallbackPlantClicked.plantClicked(plant);
            }
        });


    }

    @Override
    public int getItemCount() {
        return plants.size();
    }


}
