package com.MVP.team5.universenews.ui.fragment.dantri.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.MVP.team5.universenews.ui.fragment.dantri.DanTriGDFragment;
import com.MVP.team5.universenews.ui.fragment.dantri.DanTriKHCNFragment;
import com.MVP.team5.universenews.ui.fragment.dantri.DanTriVanHoaFragment;

/**
 * Created by B350M on 12/4/2017.
 */

public class DanTriPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "KH - CN", "Giáo Dục", "Văn Hóa" };
    private Context context;

    public DanTriPagerAdapter(FragmentManager fm, Context context) {
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
            return new DanTriKHCNFragment();
        }
        if (position == 1) {
            return new DanTriGDFragment();
        } else {
            return new DanTriVanHoaFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
