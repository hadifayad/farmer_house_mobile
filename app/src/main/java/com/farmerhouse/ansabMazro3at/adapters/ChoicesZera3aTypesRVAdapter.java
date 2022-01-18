package com.farmerhouse.ansabMazro3at.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farmerhouse.Adapters.ChoicesRecyclerView;
import com.farmerhouse.R;
import com.farmerhouse.ansabMazro3at.models.Zera3aTypes;
import com.farmerhouse.ansabMazro3at.models.Zera3aTypesChoices;
import com.farmerhouse.models.Data;

import java.util.List;

public class ChoicesZera3aTypesRVAdapter extends RecyclerView.Adapter<ChoicesZera3aTypesRVAdapter.MyViewHolder> {

    Context context;
    List<Zera3aTypesChoices> zera3aTypesChoices;
    String tableName;
    int zera3aTypePosition;

    TextChoosen mCallback;

    public interface TextChoosen {
        public void sendTextChoosen(Zera3aTypesChoices s,String tableName,int zera3aTypePosition);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView choiceName;
//        public LinearLayout layout;

        public MyViewHolder(View view) {
            super(view);
            choiceName = view.findViewById(R.id.choiceName);
//            title = view.findViewById(R.id.title);
        }
    }

    public ChoicesZera3aTypesRVAdapter(List<Zera3aTypesChoices> zera3aTypesChoices
            , Zera3aTypesRecyclerViewAdapter zera3aTypesAdapter, String tableName,int zera3aTypePosition) {
        this.zera3aTypesChoices = zera3aTypesChoices;
        this.zera3aTypePosition = zera3aTypePosition;
        this.tableName = tableName;
        mCallback = (TextChoosen) zera3aTypesAdapter;
    }

    @Override
    public ChoicesZera3aTypesRVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.zera3a_types_choices_item, parent, false);
        context = parent.getContext();
        return new ChoicesZera3aTypesRVAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ChoicesZera3aTypesRVAdapter.MyViewHolder holder, final int position) {
        final Zera3aTypesChoices zera3aTypesChoice = zera3aTypesChoices.get(position);
        holder.choiceName.setText(zera3aTypesChoice.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.sendTextChoosen(zera3aTypesChoice,tableName,zera3aTypePosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return zera3aTypesChoices.size();
    }


}