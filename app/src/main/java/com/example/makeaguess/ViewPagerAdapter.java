package com.example.makeaguess;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final String title[]={"Animals","Flowers"};
    private Bundle bundle;
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:Frag5 frag5=new Frag5();
                frag5.setArguments(bundle);

                return  frag5;
            case 1:Frag6 frag6=new Frag6();
                frag6.setArguments(bundle);
                return  frag6;

        }

        return null;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}

