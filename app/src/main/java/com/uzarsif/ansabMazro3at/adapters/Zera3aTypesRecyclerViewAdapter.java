package com.uzarsif.ansabMazro3at.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uzarsif.AnsabMazro3atActivity;
import com.uzarsif.R;
import com.uzarsif.ansabMazro3at.models.Zera3aTypes;
import com.uzarsif.ansabMazro3at.models.Zera3aTypesChoices;

import java.util.List;

public class Zera3aTypesRecyclerViewAdapter extends RecyclerView.Adapter<Zera3aTypesRecyclerViewAdapter.MyViewHolder> implements ChoicesZera3aTypesRVAdapter.TextChoosen {

    Context context;
    List<Zera3aTypes> zera3aTypes;
//    List<Zera3aTypes> zera3aTypesTemp;

    RecyclerView mRecyclerView;


    CheckResults mCallback;

    public interface CheckResults {
        public void check();
    }

    public List<Zera3aTypes> getZera3aTypes() {
        return this.zera3aTypes;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }


    @Override
    public void sendTextChoosen(Zera3aTypesChoices s, String tableName, int zera3aTypePosition) {
        if (this.zera3aTypes.get(zera3aTypePosition).getResultId().equals("")) {
            this.zera3aTypes.get(zera3aTypePosition).setResultName(s.getName());
            this.zera3aTypes.get(zera3aTypePosition).setResultId(s.getId());
            notifyDataSetChanged();
//        notifyItemChanged(zera3aTypePosition+1);
//            final Handler handler = new Handler(Looper.getMainLooper());
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
                    mRecyclerView.smoothScrollToPosition(getItemCount() - 1);
                    //Do something after 100ms
//                }
//            }, 300);
            this.mCallback.check();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView typeName, choiceResult;
        public RecyclerView choicesRecyclerView;
        public LinearLayout mainLayout, resultLayout;

        public MyViewHolder(View view) {
            super(view);
            typeName = view.findViewById(R.id.typeName);
            choiceResult = view.findViewById(R.id.choiceResult);
            choicesRecyclerView = view.findViewById(R.id.choicesRecyclerView);
            mainLayout = view.findViewById(R.id.mainLayout);
            resultLayout = view.findViewById(R.id.resultLayout);
//            title = view.findViewById(R.id.title);
        }
    }

    public Zera3aTypesRecyclerViewAdapter(List<Zera3aTypes> zera3aTypes, AnsabMazro3atActivity ansabMazro3atActivity) {
        this.zera3aTypes = zera3aTypes;
//        if(this.zera3aTypes.size()> 0){
//            this.zera3aTypesTemp.add(this.zera3aTypes.get(0));
//        }
        this.mCallback = (CheckResults) ansabMazro3atActivity;
    }

    @Override
    public Zera3aTypesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.zera3a_types_item, parent, false);
        context = parent.getContext();
        return new Zera3aTypesRecyclerViewAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final Zera3aTypesRecyclerViewAdapter.MyViewHolder holder, final int position) {
        final Zera3aTypes zera3aType = zera3aTypes.get(position);

        if (position == 0) {
            holder.mainLayout.setVisibility(View.VISIBLE);
            holder.itemView.setVisibility(View.VISIBLE);
        } else {
            if (this.zera3aTypes.get(position - 1).getResultId().equals("")) {
                holder.mainLayout.setVisibility(View.GONE);
                holder.itemView.setVisibility(View.GONE);
            } else {
                holder.mainLayout.setVisibility(View.VISIBLE);
                holder.itemView.setVisibility(View.VISIBLE);
            }
        }
        if (!this.zera3aTypes.get(position).getResultName().equals("")) {
            holder.resultLayout.setVisibility(View.VISIBLE);
        } else {
            holder.resultLayout.setVisibility(View.GONE);
        }
        holder.choiceResult.setText(zera3aType.getResultName());
        holder.typeName.setText(zera3aType.getName());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.choicesRecyclerView.setLayoutManager(layoutManager);
        ChoicesZera3aTypesRVAdapter choicesZera3aTypesRVAdapter = new ChoicesZera3aTypesRVAdapter(zera3aType.getData(), this, zera3aType.getTableName(), position);
        holder.choicesRecyclerView.setAdapter(choicesZera3aTypesRVAdapter);

    }

    @Override
    public int getItemCount() {
        int size = this.zera3aTypes.size();
        int x = 0;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                x++;
            } else {
                if (this.zera3aTypes.get(i - 1).getResultId().equals("")) {
                } else {
                    x++;
                }
            }
        }
        return x;
//        return zera3aTypesTemp.size();
    }


}
