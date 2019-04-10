package com.MVP.team5.universenews.ui.fragment.gamek;


import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.databinding.FragmentGamekWebviewBinding;
import com.MVP.team5.universenews.databinding.FragmentSettingsBinding;
import com.MVP.team5.universenews.ui.Utils.DataBase.MyDatabaseHelper;
import com.MVP.team5.universenews.ui.Utils.Utilities;
import com.MVP.team5.universenews.ui.fragment.gamek.model.Gamek_TrangChu_Content;
import com.MVP.team5.universenews.ui.model.NewsDetailModel;
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
    Button btnSaveNews;

    SettingsModel settingsModel;
    FragmentGamekWebviewBinding binding;
    MyDatabaseHelper databaseHelper;

    NewsDetailModel newsDetailModel;
    int newsID; //1:Gamek 2:Genk 3:BaoMoi

    public GamekWebviewFragment() {
        // Required empty public constructor
    }

    public static GamekWebviewFragment newInstance(Bundle bundle, int id) {
        GamekWebviewFragment fragment = new GamekWebviewFragment();
        fragment.setArguments(bundle);
        fragment.newsID = id;
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

        btnSaveNews = view.findViewById(R.id.btnSaveNews);
        btnSaveNews.setVisibility(View.INVISIBLE);

        databaseHelper = new MyDatabaseHelper(getContext());

        btnSaveNews = view.findViewById(R.id.btnSaveNews);
        webView = view.findViewById(R.id.gamek_detail_webview);

        webView.getSettings().setDefaultFontSize(Utilities.getFont(getContext()));
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        btnSaveNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addNews(newsDetailModel);
                Toast.makeText(getContext(), "Lưu thành công!", Toast.LENGTH_SHORT).show();
                btnSaveNews.setVisibility(View.INVISIBLE);
            }
        });

        String link = getArguments().getString("link");
        setupData(link);
    }

    @SuppressLint("StaticFieldLeak")
    private void setupData(final String mLink) {
        System.out.println("OK---------------");
        new AsyncTask<Void, Void, String>() {
            ArrayList<Gamek_TrangChu_Content> datas = new ArrayList<>();
            String mData = "";

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    Document document = Jsoup.connect(mLink).get();
                    Element body;
                    switch (newsID) {
                        case 1:
                            body = document.select("div.rightdetail_content").first();
                            mData = body.html();
                            break;
                        case 2:
                            body = document.select("#ContentDetail").first();
                            mData = body.html();
                            break;
                        case 3:
                            body = document.select("article.content_detail").first();
                            mData = body.html();
                            break;
                    }

                } catch (Exception e) {

                }
                return mData;
            }

            @Override
            protected void onPostExecute(String mData) {
                super.onPostExecute(mData);
                newsDetailModel = new NewsDetailModel(
                        getArguments().getString("title"),
                        getArguments().getString("desc"),
                        mData
                );
                webView.loadData(mData, "text/html; charset=utf-8", "UTF-8");
                btnSaveNews.setVisibility(View.VISIBLE);
            }
        }.execute();
    }
}
