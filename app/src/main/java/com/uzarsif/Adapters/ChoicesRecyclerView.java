package com.uzarsif.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.uzarsif.R;
import com.uzarsif.models.Data;

import java.util.List;

public class ChoicesRecyclerView extends RecyclerView.Adapter<ChoicesRecyclerView.MyViewHolder> {


    List<Data> Choices;
    Context context;
    boolean isLastPosition = false;
    TextChoosen mCallback;

    public interface TextChoosen {
        public void sendTextChoosen(Data s);
    }

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


    public ChoicesRecyclerView(List<Data> Choices, DataRecyclerViewAdapter dataRecyclerViewAdapter, boolean isLastPosition) {

        mCallback = (TextChoosen) dataRecyclerViewAdapter;
        this.Choices = Choices;
        this.isLastPosition = isLastPosition;
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


        if (isLastPosition) {
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mCallback.sendTextChoosen(choice);

//                DataRecyclerViewAdapter dataRecyclerViewAdapter = new DataRecyclerViewAdapter(choice);
//                CreateMessage.choicesRecyclerView.setAdapter(dataRecyclerViewAdapter);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return Choices.size();
    }


}
