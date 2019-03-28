package com.MVP.team5.universenews.ui.fragment.haitugio.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.MVP.team5.universenews.ui.fragment.haitugio.HaiTuGioCNTTFragment;
import com.MVP.team5.universenews.ui.fragment.haitugio.HaiTuGioBDFragment;
import com.MVP.team5.universenews.ui.fragment.haitugio.HaiTuGioTCFragment;

/**
 * Created by B350M on 12/4/2017.
 */

public class HaiTuGioPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Trang Chủ", "Bóng Đá", "CNTT" };
    private Context context;

    public HaiTuGioPagerAdapter(FragmentManager fm, Context context) {
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
            return new HaiTuGioTCFragment();
        }
        if (position == 1) {
            return new HaiTuGioCNTTFragment();
        } else {
            return new HaiTuGioBDFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
