package com.uzarsif.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.uzarsif.ChatCommentsActivity;
import com.uzarsif.GsonRequest;
import com.uzarsif.NetworkHelper;
import com.uzarsif.R;
import com.uzarsif.VolleySingleton;
import com.uzarsif.models.Chatwithmandoob;
import com.uzarsif.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MandoubFarmersRecyclerViewAdapter  extends RecyclerView.Adapter<MandoubFarmersRecyclerViewAdapter.MyViewHolder> {

    List<User> users;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  text;
        ImageView profile;
        LinearLayout firstlayout;


        public MyViewHolder(View view) {
            super(view);

            firstlayout = view.findViewById(R.id.firstlayout);
            text = view.findViewById(R.id.text);

        }
    }


    public MandoubFarmersRecyclerViewAdapter(List<User> users) {
        this.users = users;
    }


    @Override
    public MandoubFarmersRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mandoub_farmers_item, parent, false);
        context = parent.getContext();
        return new MandoubFarmersRecyclerViewAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MandoubFarmersRecyclerViewAdapter.MyViewHolder holder, final int position) {
        final User user = users.get(position);

        holder.text.setText(user.getFullname());
        Log.d("TAG", "onBindViewHolder: "+user.getFullname());
//        holder.textViewOutGoing.setText(StringEscapeUtils.unescapeJava(comment.getC_text()));
//        holder.title.setText(user.getTitle());
        holder.firstlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ProgressDialog dialog = ProgressDialog.show(context, "",
                        "Please wait...", true);

                String url = NetworkHelper.getUrl(NetworkHelper.ACTION_CREATE_CHAT_WITH_MANDOOB);
                Log.d("url", url.toString());
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                String userId = prefs.getString("userId", "");

                Map<String, String> params = new HashMap();
                params.put("userId", user.getId());
                params.put("mandoob", userId);




                Log.d("TAG", "uploadData: "+params.toString());



                GsonRequest<Chatwithmandoob[]> myGsonRequest1 = new GsonRequest<Chatwithmandoob[]>(Request.Method.POST, url, Chatwithmandoob[].class, null, params,
                        new Response.Listener<Chatwithmandoob[]>() {
                            @Override
                            public void onResponse(Chatwithmandoob[] response) {

//                        Log.d("TAG", "onResponse: "+response.getFullname());
                                dialog.dismiss();
                                Log.d("TAG",   response[0].getId());
                                Intent i = new Intent(context, ChatCommentsActivity.class);
                                i.putExtra("chatId", response[0].getId());
                                Log.d("TAG", "onResponsee: " + response[0].getId() + response[0].getMandoob() + response[0].getUser());
                                context.startActivity(i);



                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                        object.dismiss();
                                NetworkHelper.handleError(error);
                                Log.d("error", "onErrorResponse: " + error.getMessage());
                            }
                        });

                VolleySingleton.getInstance(context).addToRequestQueue(myGsonRequest1);







            }
        });


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
