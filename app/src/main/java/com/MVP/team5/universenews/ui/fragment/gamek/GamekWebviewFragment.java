package com.MVP.team5.universenews.ui.fragment.gamek;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.MVP.team5.universenews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamekWebviewFragment extends Fragment {
    private WebView webView;

    public GamekWebviewFragment() {
        // Required empty public constructor
    }

    public static GamekWebviewFragment newInstance(int i, String link) {
        Bundle args = new Bundle();
        args.putInt("i",i);
        args.putString("link",link);
        GamekWebviewFragment fragment = new GamekWebviewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gamek_webview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String link = getArguments().getString("link");
        webView = view.findViewById(R.id.gamekWebview);
        webView.getSettings().getJavaScriptEnabled();
        webView.getSettings().getLoadsImagesAutomatically();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);
    }
}
