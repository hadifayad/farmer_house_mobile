package com.farmerhouse.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.farmerhouse.R;
import com.farmerhouse.models.Message;
import com.farmerhouse.models.New;
import com.farmerhouse.ui.PictureItemFragment;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.List;



    public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<com.farmerhouse.Adapters.NewsRecyclerViewAdapter.MyViewHolder> {

        List<New> News;
        Context context;
        ViewPagerAdapter adapter;
        PageIndicatorView pageIndicatorView;
        ViewPager pictures_viewpager;
        Fragment fragment;

        public class MyViewHolder extends RecyclerView.ViewHolder {


            ImageView profile;
            TextView date , username,text;



            public MyViewHolder(View view) {
                super(view);
//                messageDate = view.findViewById(R.id.messageDate);
                pictures_viewpager = view.findViewById(R.id.pictures_viewpager);
                 pageIndicatorView = view.findViewById(R.id.pageIndicatorView);
                profile = view.findViewById(R.id.profile);
                date = view.findViewById(R.id.date);
                username = view.findViewById(R.id.username);
                text = view.findViewById(R.id.text);
                pageIndicatorView.setVisibility(View.GONE);
                pictures_viewpager.setVisibility(View.GONE);
//                layout = view.findViewById(R.id.layout);
            }
        }


        public NewsRecyclerViewAdapter(List<New> News , Fragment fragment) {


            this.News = News;
            this.fragment = fragment;
        }


        @Override
        public com.farmerhouse.Adapters.NewsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.new_item, parent, false);
            context = parent.getContext();
            return new com.farmerhouse.Adapters.NewsRecyclerViewAdapter.MyViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(final com.farmerhouse.Adapters.NewsRecyclerViewAdapter.MyViewHolder holder, final int position) {
            final New aNew = News.get(position);

           holder.text.setText(aNew.getText());
           holder.username.setText(aNew.getFullname());
           holder.date.setText(aNew.getDate());

            if (aNew.getFiles() != null && aNew.getFiles() != "") {
                setupViewPager(aNew.getFiles(), holder);

            }

//           holder.messageDate.setText(aNew.getDate());




        }

        @Override
        public int getItemCount() {
            return News.size();
        }

        public void setupViewPager(String picturesList,MyViewHolder holder) {

            pageIndicatorView.setVisibility(View.VISIBLE);
            pictures_viewpager.setVisibility(View.VISIBLE);

            String[] picturesArray = picturesList.split(",");
//        getChildFragmentManager()
            adapter = new ViewPagerAdapter(fragment.getParentFragmentManager());
//        adapter = new ViewPagerAdapter((((FragmentActivity) getActivity()).getSupportFragmentManager()));
            for (int i = 0; i < picturesArray.length; i++) {
                String picture = picturesArray[i];
                PictureItemFragment pictureItemFragment = new PictureItemFragment();
                pictureItemFragment.setData(picture);
                adapter.addFragment(pictureItemFragment, "");
            }
            pageIndicatorView.setAnimationType(AnimationType.THIN_WORM);
            pageIndicatorView.setCount(picturesArray.length); // specify
            pictures_viewpager.setAdapter(adapter);
            pictures_viewpager.setCurrentItem(0);
            pictures_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {/*empty*/}

                @Override
                public void onPageSelected(int position) {
                    pageIndicatorView.setSelection(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {/*empty*/}
            });
            pageIndicatorView.setViewPager(pictures_viewpager);
        }


    }



