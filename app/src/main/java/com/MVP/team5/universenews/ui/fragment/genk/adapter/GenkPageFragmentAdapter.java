package com.MVP.team5.universenews.ui.fragment.genk.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.MVP.team5.universenews.ui.fragment.genk.TinIctGenkFragment;
import com.MVP.team5.universenews.ui.fragment.genk.TrangChuGenkFragment;
import com.MVP.team5.universenews.ui.fragment.genk.MobileGenkFragment;

/**
 * Created by B350M on 11/30/2017.
 */

public class GenkPageFragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Main ", "Mobile", "ICT News"};
    private Context context;

    public GenkPageFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        // return GenkPageFragment.newInstance(position + 1);
        if (position == 0) {
            return new TrangChuGenkFragment();
        }
        if (position == 1) {
            return new MobileGenkFragment();
        } else {
            return new TinIctGenkFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}