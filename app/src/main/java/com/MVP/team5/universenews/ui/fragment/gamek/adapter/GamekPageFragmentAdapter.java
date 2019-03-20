package com.MVP.team5.universenews.ui.fragment.gamek.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.MVP.team5.universenews.ui.fragment.gamek.MobileGamekFragment;
import com.MVP.team5.universenews.ui.fragment.gamek.PcConsoleGamekFragment;
import com.MVP.team5.universenews.ui.fragment.gamek.TrangChuGamekFragment;

/**
 * Created by B350M on 11/23/2017.
 */

public class GamekPageFragmentAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;

    private String tabTitles[] = new String[]{"Main", "PC Console", "Mobile"};
    private Context context;

    public GamekPageFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
//        return GamekPageFragment.newInstance(position + 1);
        if (position == 0) {
            return new TrangChuGamekFragment();
        }
        if (position == 1) {
            return new PcConsoleGamekFragment();
        } else {
            return new MobileGamekFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
