package com.farmerhouse.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.farmerhouse.R;
import com.farmerhouse.models.Comment;
import com.farmerhouse.models.User;

import java.util.List;

public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentsRecyclerViewAdapter.MyViewHolder> {

        List<Comment> comments;
        Context context;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewOutGoing, client_name, textviewIncoming, client_nameInGOING;
    public ImageView incoming_icon , outgoing_icon;
    LinearLayout in,out;

    public MyViewHolder(View view) {
        super(view);
        textviewIncoming = view.findViewById(R.id.textVieIncoming);
//        client_name = view.findViewById(R.id.client_name);
        textViewOutGoing = view.findViewById(R.id.textVieOutGoing);
//        client_nameInGOING = view.findViewById(R.id.client_nameInGOING);
        in = view.findViewById(R.id.in);
        out = view.findViewById(R.id.out);
    }
}


    public CommentsRecyclerViewAdapter(List<Comment> comments) {
        this.comments = comments;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments_item_list, parent, false);
        context = parent.getContext();
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Comment comment = comments.get(position);
        holder.in.setVisibility(View.GONE);
        holder.out.setVisibility(View.GONE);
//        holder.client_name.setVisibility(View.GONE);
//        holder.client_nameInGOING.setVisibility(View.GONE);
//        holder.outgoing_icon.setVisibility(View.GONE);
//        holder.incoming_icon.setVisibility(View.GONE);


        //decode
//        String serverResponse = "SOME RESPONSE FROM SERVER WITH UNICODE CHARACTERS";
//        String fromServerUnicodeDecoded = StringEscapeUtils.unescapeJava(serverResponse);

//        holder.textviewIngoing.setText(StringEscapeUtils.unescapeJava(comment.getC_text()));
        holder.textviewIncoming.setText(comment.getText());
//        holder.textViewOutGoing.setText(StringEscapeUtils.unescapeJava(comment.getC_text()));
        holder.textViewOutGoing.setText(comment.getText());
        Log.d("TAG", "onBindViewHolder: id luser "+User.getID(context));



        if (comment.getUserId().equals(User.getID(context))) {
            holder.out.setVisibility(View.VISIBLE);
//            holder.client_name.setVisibility(View.VISIBLE);
//            holder.outgoing_icon.setVisibility(View.VISIBLE);
        } else {
            holder.in.setVisibility(View.VISIBLE);
//            holder.client_nameInGOING.setVisibility(View.VISIBLE);
//            holder.incoming_icon.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

}
