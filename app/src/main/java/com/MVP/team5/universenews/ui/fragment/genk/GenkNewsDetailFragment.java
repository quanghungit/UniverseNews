package com.MVP.team5.universenews.ui.fragment.genk;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.Utils.Utilities;
import com.MVP.team5.universenews.ui.fragment.gamek.model.Gamek_TrangChu_Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenkNewsDetailFragment extends Fragment {

    TextView textView;
    WebView webView;

    public GenkNewsDetailFragment() {
        // Required empty public constructor
    }

    public static GenkNewsDetailFragment newInstance(int i, String link) {
        Bundle args = new Bundle();
        args.putInt("i", i);
        args.putString("link", link);
        GenkNewsDetailFragment fragment = new GenkNewsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genk_news_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.genk_news_detail_textview);
        webView = view.findViewById(R.id.genk_detail_webView);
        webView.getSettings().setDefaultFontSize(Utilities.getFont(getContext())/2);

        setupData(getArguments().getString("link"));
    }

    private void setupData(final String mLink) {
        System.out.println("OK---------------");
        new AsyncTask<Void, Void, String>() {
            String mData = "";

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    Document document = Jsoup.connect(mLink).get();
                    Element body = document.select("#ContentDetail").first();
                    body.html();
//
                    System.out.println("Ahihi=====" + document.title() + "=======" + body.text());
//                    Element body = document.select("div.rightdetail_content").first();
//                    System.out.println(" Ahihi do ngok: " + body.text() + "\n" + document.title());
                    mData = body.html();
                } catch (Exception e) {

                }
                return mData;
            }

            @Override
            protected void onPostExecute(String mData) {
                super.onPostExecute(mData);
                //textView.setText(Html.fromHtml(mData));
                webView.loadData(mData, "text/html; charset=utf-8","UTF-8");
            }
        }.execute();

    }

}
