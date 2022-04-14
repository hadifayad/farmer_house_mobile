package com.uzarsif.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.uzarsif.NetworkHelper;
import com.uzarsif.R;

import java.util.List;
import java.util.Objects;

public class ViewPagerAdapterPictures extends PagerAdapter {

    Context context;
    LayoutInflater mLayoutInflater;
    private List<String> mImageList;

    public ViewPagerAdapterPictures(Context context, List<String> imageList) {
        this.context = context;
        this.mImageList = imageList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.mImageList == null ? 0 : this.mImageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_picture, container, false);
        ImageView imageView = itemView.findViewById(R.id.imageview);
        String imageview_url = mImageList.get(position);
        String url = NetworkHelper.IMAGES_PATH_POST_PICTURES + imageview_url;
        Glide.with(context).load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(imageView);
        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
