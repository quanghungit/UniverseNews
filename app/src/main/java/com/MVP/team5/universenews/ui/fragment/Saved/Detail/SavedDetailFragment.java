package com.MVP.team5.universenews.ui.fragment.Saved.Detail;


import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.Utils.Utilities;
import com.MVP.team5.universenews.ui.activity.MainActivity;
import com.MVP.team5.universenews.ui.fragment.gamek.GamekWebviewFragment;
import com.MVP.team5.universenews.ui.model.NewsDetailModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedDetailFragment extends Fragment {

    WebView webView;
    TextView textView;
    NewsDetailModel newsDetailModel;

    public SavedDetailFragment() {
        // Required empty public constructor
    }

    public static SavedDetailFragment newInstance(NewsDetailModel news) {
        SavedDetailFragment fragment = new SavedDetailFragment();
        fragment.newsDetailModel = news;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.savedWv);
        textView = view.findViewById(R.id.savedTv);

        webView.getSettings().setDefaultFontSize(Utilities.getFont(getContext()));
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        if (isOnline()) {
            webView.loadData(newsDetailModel.getHtml(), "text/html; charset=utf-8", "UTF-8");
        } else {
            textView.setText(newsDetailModel.getHtml());
        }

        if (Utilities.getNight(getContext())) {
            webView.setBackgroundColor(Color.parseColor("#E8BBAF74"));
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
