package com.farmerhouse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farmerhouse.R;
import com.farmerhouse.models.Message;

import java.util.List;







    public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesRecyclerViewAdapter.MyViewHolder> {

        List<Message> Messages;
        Context context;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView messageDate;
            public LinearLayout layout;

            public MyViewHolder(View view) {
                super(view);
                messageDate = view.findViewById(R.id.messageDate);

//                layout = view.findViewById(R.id.layout);
            }
        }


        public MessagesRecyclerViewAdapter(List<Message> Messages) {


            this.Messages = Messages;
        }


        @Override
        public MessagesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_item, parent, false);
            context = parent.getContext();
            return new MessagesRecyclerViewAdapter.MyViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(final MessagesRecyclerViewAdapter.MyViewHolder holder, final int position) {
            final Message message = Messages.get(position);

            holder.messageDate.setText(message.getDate());




        }

        @Override
        public int getItemCount() {
            return Messages.size();
        }


    }


