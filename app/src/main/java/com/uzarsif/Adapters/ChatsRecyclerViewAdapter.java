package com.uzarsif.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.uzarsif.R;
import com.uzarsif.inbox.ViewMessageActivity;
import com.uzarsif.models.Chat;

import java.util.List;


public class ChatsRecyclerViewAdapter extends RecyclerView.Adapter<ChatsRecyclerViewAdapter.MyViewHolder> {

    List<Chat> Chats;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView chatDate,title;
        public LinearLayout layout;

        public MyViewHolder(View view) {
            super(view);
            chatDate = view.findViewById(R.id.chatDate);
            title = view.findViewById(R.id.title);

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

        holder.chatDate.setText(chat.getCreated_at());
//        Log.d("TAG", "onBindViewHolder: "+chat.getTitle());
//        Log.d("TAG", "onBindViewHolder: "+chat.getCreated_at());


            holder.title.setText(chat.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewMessageActivity.class);
                intent.putExtra("chatId", chat.getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return Chats.size();
    }


}


