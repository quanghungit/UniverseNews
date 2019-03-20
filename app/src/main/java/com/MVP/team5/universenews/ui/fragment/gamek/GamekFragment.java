package com.MVP.team5.universenews.ui.fragment.gamek;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.fragment.gamek.adapter.GamekPageFragmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamekFragment extends Fragment {


    public GamekFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gamek, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpagerGamek);
        viewPager.setAdapter(new GamekPageFragmentAdapter(getChildFragmentManager(),
                getActivity()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs_gamek);
        tabLayout.setupWithViewPager(viewPager);
    }
}
