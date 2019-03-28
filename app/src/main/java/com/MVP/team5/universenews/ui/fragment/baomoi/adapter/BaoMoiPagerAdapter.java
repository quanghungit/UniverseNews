package com.MVP.team5.universenews.ui.fragment.baomoi.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.MVP.team5.universenews.ui.fragment.baomoi.BaoMoiGTFragment;
import com.MVP.team5.universenews.ui.fragment.baomoi.BaoMoiTTFragment;
import com.MVP.team5.universenews.ui.fragment.baomoi.BaoMoiTGFragment;

/**
 * Created by B350M on 12/4/2017.
 */

public class BaoMoiPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Thể Thao", "Thế Giới", "Giải Trí" };
    private Context context;

    public BaoMoiPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BaoMoiTTFragment();
        }
        if (position == 1) {
            return new BaoMoiGTFragment();
        } else {
            return new BaoMoiTGFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
