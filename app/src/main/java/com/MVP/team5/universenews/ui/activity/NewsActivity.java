package com.MVP.team5.universenews.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.Utils.Utilities;
import com.MVP.team5.universenews.ui.fragment.gamek.model.Gamek_TrangChu_Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    WebView webView;
    String mLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mLink = getIntent().getStringExtra("Link");

        webView = findViewById(R.id.notiNewsWv);
        webView.getSettings().setDefaultFontSize(Utilities.getFont(this));
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings();

        if (Utilities.getNight(this)) {
            findViewById(R.id.notiLayout).setBackgroundColor(Color.parseColor("#E8BBAF74"));
            webView.setBackgroundColor(Color.parseColor("#E8BBAF74"));
        }

        setupData(mLink);
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
                    body = document.select("article.content_detail").first();
                    mData = body.html();

                } catch (Exception e) {

                }
                return mData;
            }

            @Override
            protected void onPostExecute(String mData) {
                super.onPostExecute(mData);
                webView.loadData(mData, "text/html; charset=utf-8", "UTF-8");
            }
        }.execute();
    }
}
