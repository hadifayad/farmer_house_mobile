package com.uzarsif.OkoodAnwa3.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.uzarsif.OkoodAnwa3.UnofficialFragment;
import com.uzarsif.OkoodAnwa3.OfficialFragment;


public class OkoodViewPagerAdapter extends FragmentPagerAdapter {
    String userId;

    public OkoodViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment postInProfileFragment = new OfficialFragment();

                return postInProfileFragment;
            case 1:
                Fragment LoginInFragment = new UnofficialFragment();
                return LoginInFragment;

        }
        Fragment SignupInFragment = new OfficialFragment();
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
            case 0:
                return "عقود مع الجمعية";
            case 1:

            return "طلب عقد زراعي";

        }
        return "post";
    }
}
