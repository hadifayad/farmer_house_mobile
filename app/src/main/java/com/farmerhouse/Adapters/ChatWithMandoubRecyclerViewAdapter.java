package com.farmerhouse.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.farmerhouse.ChatCommentsActivity;
import com.farmerhouse.DaleelActivity;
import com.farmerhouse.DataFinal;
import com.farmerhouse.R;
import com.farmerhouse.models.Chatwithmandoob;
import com.farmerhouse.models.Comment;
import com.farmerhouse.models.User;

import java.util.List;

public class ChatWithMandoubRecyclerViewAdapter  extends RecyclerView.Adapter<ChatWithMandoubRecyclerViewAdapter.MyViewHolder> {

    List<Chatwithmandoob> chats;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  chatDate, title,name;
        LinearLayout firstlayout;


        public MyViewHolder(View view) {
            super(view);
            chatDate = view.findViewById(R.id.chatDate);
        firstlayout = view.findViewById(R.id.firstlayout);

            title = view.findViewById(R.id.title);
            name = view.findViewById(R.id.name);
        }
    }


    public ChatWithMandoubRecyclerViewAdapter(List<Chatwithmandoob> chats) {
        this.chats = chats;
    }


    @Override
    public ChatWithMandoubRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_with_mandoub_item, parent, false);
        context = parent.getContext();
        return new ChatWithMandoubRecyclerViewAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ChatWithMandoubRecyclerViewAdapter.MyViewHolder holder, final int position) {
        final Chatwithmandoob chat = chats.get(position);

        holder.chatDate.setText(chat.getCreation_date());
//        holder.textViewOutGoing.setText(StringEscapeUtils.unescapeJava(comment.getC_text()));
        holder.title.setText(chat.getTitle());
        Log.d("TAG", "onBindViewHolder: fullname "+chat.getFullname());


        if(chat.getFullname() != null && !chat.getFullname().equals("")){
            holder.name.setText(chat.getFullname());
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


