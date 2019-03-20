package com.MVP.team5.universenews.ui.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.activity.MainActivity;
import com.MVP.team5.universenews.ui.fragment.dantri.DanTriFragment;
import com.MVP.team5.universenews.ui.fragment.genk.GenkFragment;
import com.MVP.team5.universenews.ui.fragment.gamek.GamekFragment;
import com.MVP.team5.universenews.ui.fragment.soha.SoHaFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    ImageButton gamek, genk, dantri, soha;
    TextView textView;

    MainActivity mainActivity;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.fragment_main, container, false);

        // create ContextThemeWrapper from the original Activity Context with the custom theme
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        // inflate the layout using the cloned inflater, not default inflater
        return localInflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gamek = view.findViewById(R.id.imGamek);
        genk = view.findViewById(R.id.imGenk);
        dantri = view.findViewById(R.id.imDantri);
        soha = view.findViewById(R.id.imSoha);
        textView = view.findViewById(R.id.aaa);

        gamek.setOnClickListener(this);
        genk.setOnClickListener(this);
        dantri.setOnClickListener(this);
        soha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imGamek:
                if (isOnline() == false) {
                    Toast.makeText((MainActivity) getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                } else {
                    ((MainActivity) getActivity()).changeFragment(new GamekFragment());
                    ((MainActivity) getActivity()).setTitle("GameK");
                }
                break;
            case R.id.imGenk:
                if (isOnline() == false) {
                    Toast.makeText((MainActivity) getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                } else {
                    ((MainActivity) getActivity()).changeFragment(new GenkFragment());
                    ((MainActivity) getActivity()).setTitle("GenK");
                }
                break;
            case R.id.imDantri:
                if (isOnline() == false) {
                    Toast.makeText((MainActivity) getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                } else {
                    ((MainActivity) getActivity()).changeFragment(new DanTriFragment());
                    ((MainActivity) getActivity()).setTitle("Dân Trí");
                }
                break;
            case R.id.imSoha:
                if (isOnline() == false) {
                    Toast.makeText((MainActivity) getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                } else {
                    ((MainActivity) getActivity()).changeFragment(new SoHaFragment());
                    ((MainActivity) getActivity()).setTitle("SOHA");
                }
                break;
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) ((MainActivity) getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

