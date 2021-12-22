package com.farmerhouse;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.farmerhouse.fragmentsInProfile.adapters.ProfileViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class LoginOrSignup extends AppCompatActivity {

    ViewPager profileViewPager;
    ProfileViewPagerAdapter profileViewPagerAdapter;
    TabLayout tab_layout_in_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_signup);

        profileViewPager = findViewById(R.id.profileViewPager);
        tab_layout_in_profile = findViewById(R.id.tab_layout_in_profile);
        tab_layout_in_profile.setupWithViewPager(profileViewPager);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#cc9f53"));

        profileViewPagerAdapter = new ProfileViewPagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        profileViewPager.setAdapter(profileViewPagerAdapter);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        ViewGroup.LayoutParams params = profileViewPager.getLayoutParams();
        params.height = height;

        profileViewPager.setLayoutParams(params);

    }
}