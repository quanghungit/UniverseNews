package com.MVP.team5.universenews.ui.fragment.dantri;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.Utils.DataBase.MyDatabaseHelper;
import com.MVP.team5.universenews.ui.Utils.Utilities;
import com.MVP.team5.universenews.ui.fragment.genk.GenkNewsDetailFragment;
import com.MVP.team5.universenews.ui.model.NewsDetailModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanTriDetailBlankFragment extends Fragment {

    WebView webView;
    Button button;
    MyDatabaseHelper myDatabaseHelper;
    NewsDetailModel newsDetailModel;

    public DanTriDetailBlankFragment() {
        // Required empty public constructor
    }

    public static DanTriDetailBlankFragment newInstance(NewsDetailModel newsDetailModel) {
        DanTriDetailBlankFragment fragment = new DanTriDetailBlankFragment();
        fragment.newsDetailModel = newsDetailModel;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dan_tri_detail_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDatabaseHelper = new MyDatabaseHelper(getContext());

        button = view.findViewById(R.id.btnSaveBaoMoiNews);
        button.setVisibility(View.INVISIBLE);

        webView = view.findViewById(R.id.dantri_detail_webview);
        webView.getSettings().setDefaultFontSize(Utilities.getFont(getContext())/2);

        setupData(getArguments().getString("link"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.addNews(newsDetailModel);
                Toast.makeText(getContext(), "Lưu thành công!",Toast.LENGTH_SHORT).show();
                button.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setupData(final String mLink) {
        System.out.println("OK---------------" + mLink);
        new AsyncTask<Void, Void, String>() {
            String mData = "";

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    Document document = Jsoup.connect(mLink).get();
                    Element body = document.select("article.content_detail").first();
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
                newsDetailModel = new NewsDetailModel(
                        getArguments().getString("title"),
                        getArguments().getString("desc"),
                        mData
                );
                webView.loadData(mData, "text/html; charset=utf-8","UTF-8");
                button.setVisibility(View.VISIBLE);
            }
        }.execute();

    }

}
