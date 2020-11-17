package com.example.news;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SimpleFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public SimpleFragmentAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new TopStoryFragment();
        }
        else if(position == 1){
            return new SportFragment();
        }
        else if (position == 2){
            return new BusinessFragment();
        }
        else{
            return new EntertainmentFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return mContext.getString(R.string.topStory);
        }
        else if (position == 1){
            return mContext.getString(R.string.sport);
        }
        else if (position == 2){
            return mContext.getString(R.string.business);
        }
        else {
            return mContext.getString(R.string.entertainment);
        }
    }
}
