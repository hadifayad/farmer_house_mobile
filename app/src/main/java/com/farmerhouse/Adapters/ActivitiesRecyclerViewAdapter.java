package com.farmerhouse.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.farmerhouse.R;
import com.farmerhouse.models.New;
import com.farmerhouse.models.UserActivity;
import com.farmerhouse.ui.PictureItemFragment;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.List;

public class ActivitiesRecyclerViewAdapter extends RecyclerView.Adapter<com.farmerhouse.Adapters.ActivitiesRecyclerViewAdapter.MyViewHolder> {

        List<UserActivity> UserActivities;
        Context context;


        public class MyViewHolder extends RecyclerView.ViewHolder {


            ImageView icon;
            TextView date , mawsem,name;



            public MyViewHolder(View view) {
                super(view);



                icon = view.findViewById(R.id.type_icon);
                date = view.findViewById(R.id.date);
                mawsem = view.findViewById(R.id.mawsem);
                name = view.findViewById(R.id.name);


            }
        }


        public ActivitiesRecyclerViewAdapter(List<UserActivity> UserActivities ) {


            this.UserActivities = UserActivities;

        }


        @Override
        public com.farmerhouse.Adapters.ActivitiesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup
        parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_item, parent, false);
            context = parent.getContext();
            return new com.farmerhouse.Adapters.ActivitiesRecyclerViewAdapter.MyViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(final com.farmerhouse.Adapters.ActivitiesRecyclerViewAdapter.MyViewHolder holder, final int position) {
            final UserActivity activity = UserActivities.get(position);
            String input = activity.getDate();
            String output = input.substring(0, 10);
            holder.date.setText(output);


            if(activity.getMazrouat_type().equals("أشجار")){
                holder.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_fruit_item));
                Log.d("TAG", "onBindViewHolder: "+activity.getMazrouat_type());

            }
            if(activity.getMazrouat_type().equals("بقوليات")){
                holder.icon.setImageResource(R.drawable.ic_bakoleyat_icon);
                Log.d("TAG", "onBindViewHolder: "+activity.getMazrouat_type());

            }

            if(activity.getMazrouat_type().equals("خضار")){
                holder.icon.setImageResource(R.drawable.ic_khodar_icon);
                Log.d("TAG", "onBindViewHolder: "+activity.getMazrouat_type());

            }

            holder.mawsem.setText(activity.getMawsem());
            holder.name.setText(activity.getPlant());






        }

        @Override
        public int getItemCount() {
            return UserActivities.size();
        }



    }
