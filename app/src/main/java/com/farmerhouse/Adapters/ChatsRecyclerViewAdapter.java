package com.farmerhouse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farmerhouse.R;
import com.farmerhouse.models.Chat;
import com.farmerhouse.models.Message;

import java.util.List;







    public class ChatsRecyclerViewAdapter extends RecyclerView.Adapter<ChatsRecyclerViewAdapter.MyViewHolder> {

        List<Chat> Chats;
        Context context;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView chatDate;
            public LinearLayout layout;

            public MyViewHolder(View view) {
                super(view);
                chatDate = view.findViewById(R.id.chatDate);

//                layout = view.findViewById(R.id.layout);
            }
        }


        public ChatsRecyclerViewAdapter(List<Chat> Chats) {


            this.Chats = Chats;
        }


        @Override
        public ChatsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_item, parent, false);
            context = parent.getContext();
            return new ChatsRecyclerViewAdapter.MyViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(final ChatsRecyclerViewAdapter.MyViewHolder holder, final int position) {
            final Chat chat = Chats.get(position);

            holder.chatDate.setText(chat.getDate());




        }

        @Override
        public int getItemCount() {
            return Chats.size();
        }


    }


