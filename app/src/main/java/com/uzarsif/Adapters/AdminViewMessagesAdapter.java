package com.uzarsif.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uzarsif.ChatCommentsActivity;
import com.uzarsif.R;
import com.uzarsif.models.AdminViewMessage;


import java.util.List;

public class AdminViewMessagesAdapter extends RecyclerView.Adapter<AdminViewMessagesAdapter.MyViewHolder>{
    List<AdminViewMessage> chats;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView chatDate, farmer,mandoub;
        LinearLayout firstlayout;


        public MyViewHolder(View view) {
            super(view);
            chatDate = view.findViewById(R.id.chatDate);
            firstlayout = view.findViewById(R.id.firstlayout);

            mandoub = view.findViewById(R.id.mandoub);
            farmer = view.findViewById(R.id.farmer);
        }
    }



    public AdminViewMessagesAdapter(List<AdminViewMessage> chats) {

        this.chats = chats;
    }


    @Override
    public AdminViewMessagesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_messages_item, parent, false);
        context = parent.getContext();

        return new AdminViewMessagesAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdminViewMessagesAdapter.MyViewHolder holder,final int position) {
        AdminViewMessage chat = this.chats.get(position);
        holder.chatDate.setText(chat.getCreation_date());
//        Log.d("TAG", "onClick: +parent = "+chat.getMandoob());
////        holder.textViewOutGoing.setText(StringEscapeUtils.unescapeJava(comment.getC_text()));
//
//        Log.d("TAG", "onBindViewHolder: fullname "+chat.getFullname());


        if(chat.getFullname() != null && !chat.getFullname().equals("")){


            holder.farmer.setText(chat.getFullname());
        }
        if(chat.getMandoub() != null && !chat.getMandoub().equals("")){

            holder.mandoub.setText(chat.getMandoub());

        }
        holder.firstlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent k = new Intent(context, ChatCommentsActivity.class);

                k.putExtra("chatId", chat.getId());

                Log.d("TAG", "onClick: +parent = "+k.toString());
                context.startActivity(k);



            }
        });


    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

}
