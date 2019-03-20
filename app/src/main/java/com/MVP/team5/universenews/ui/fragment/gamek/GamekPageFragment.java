package com.MVP.team5.universenews.ui.fragment.gamek;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.fragment.gamek.adapter.GamekPageFragmentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamekPageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    GamekPageFragmentAdapter adapter;

    private int mPage;

    public static GamekPageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        GamekPageFragment fragment = new GamekPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gamek_page, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #" + mPage);
        Log.d("afs", "mpage: " + mPage);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
