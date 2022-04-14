package com.uzarsif.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.uzarsif.NetworkHelper;
import com.uzarsif.R;


public class PictureItemFragment extends Fragment {

    String picture;
    public ImageView imageView;
    public View view;


    public PictureItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_picture_item, container, false);
        imageView = view.findViewById(R.id.imageView);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new RoundedCorners(13));


        String url = NetworkHelper.IMAGES_PATH_POST_PICTURES + picture;
        Log.d("picccc",url);
        Glide.with(getContext()).load(url)
                .centerCrop()

//                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);

        return view;
    }

    public void setData(String picture) {
        this.picture = picture;
    }

}


