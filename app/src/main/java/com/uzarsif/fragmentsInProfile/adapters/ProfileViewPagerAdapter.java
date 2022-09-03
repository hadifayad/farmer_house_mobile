package com.uzarsif.fragmentsInProfile.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.uzarsif.fragmentsInProfile.LoginInFragment;
import com.uzarsif.fragmentsInProfile.SignupInFragment;


public class ProfileViewPagerAdapter extends FragmentPagerAdapter {
    String userId;

    public ProfileViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);


    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment LoginInFragment = new LoginInFragment();
                return LoginInFragment;
            case 1:

            Fragment postInProfileFragment = new SignupInFragment();

            return postInProfileFragment;

        }
        Fragment SignupInFragment = new SignupInFragment();
        return SignupInFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return "انشاء حساب";
            case 0:
                return "تسجيل دخول";

        }
        return "post";
    }
}
