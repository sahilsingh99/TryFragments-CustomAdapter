package com.example.tryfragadap;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SimpleFragmentAdapter extends FragmentPagerAdapter {


    private Context mContext;

    public SimpleFragmentAdapter(Context context, FragmentManager fm){
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            String title = mContext.getResources().getString(R.string.Family);
            return  title;
        }
        else {
            String title = mContext.getResources().getString(R.string.Audio);
            return  title;
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0) return new FamilyFragment();
        else return new AudioFragment();
    }
}
