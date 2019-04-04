package com.MVP.team5.universenews.ui.fragment.gamek;


import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.databinding.FragmentGamekWebviewBinding;
import com.MVP.team5.universenews.databinding.FragmentSettingsBinding;
import com.MVP.team5.universenews.ui.Utils.Utilities;
import com.MVP.team5.universenews.ui.fragment.gamek.model.Gamek_TrangChu_Content;
import com.MVP.team5.universenews.ui.model.SettingsModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamekWebviewFragment extends Fragment {
    private WebView webView;

    SettingsModel settingsModel;
    FragmentGamekWebviewBinding binding;

    public GamekWebviewFragment() {
        // Required empty public constructor
    }

    public static GamekWebviewFragment newInstance(int i, String link) {
        Bundle args = new Bundle();
        args.putInt("i", i);
        args.putString("link", link);
        GamekWebviewFragment fragment = new GamekWebviewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        settingsModel = new SettingsModel(
                Utilities.getTheme(getContext()),
                Utilities.getFont(getContext()),
                Utilities.getNight(getContext())
        );
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gamek_webview, container, false);
        View view = binding.getRoot();
        binding.setSetting(settingsModel);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.gamek_detail_webview);
        webView.getSettings().setDefaultFontSize(Utilities.getFont(getContext())/2);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);


        String link = getArguments().getString("link");
        System.out.println("Tuan Anh Dep Trai Vo Dich" + link);
        setupData(link);
    }

    private void setupData(final String mLink) {
        System.out.println("OK---------------");
        new AsyncTask<Void, Void, String>() {
            ArrayList<Gamek_TrangChu_Content> datas = new ArrayList<>();
            String mData = "";

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    Document document = Jsoup.connect(mLink).get();

                    Element body = document.select("div.rightdetail_content").first();
                    System.out.println(" Ahihi do ngok: " + body.text() + "\n" + document.title());
                    mData = body.html();
                } catch (Exception e) {

                }
                return mData;
            }

            @Override
            protected void onPostExecute(String mData) {
                super.onPostExecute(mData);
                webView.loadData(mData, "text/html; charset=utf-8","UTF-8");
            }
        }.execute();

    }


}
