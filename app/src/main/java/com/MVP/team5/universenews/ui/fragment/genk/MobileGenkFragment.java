package com.MVP.team5.universenews.ui.fragment.genk;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.fragment.genk.adapter.GenkMobileAdapter;
import com.MVP.team5.universenews.ui.fragment.genk.model.Genk_Mobile_Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MobileGenkFragment extends Fragment {
    private static final String TAG = "MobileGenkFragment";
    private ListView listView;
    private ArrayList<Genk_Mobile_Content> datas;
    private GenkMobileAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    public MobileGenkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moible_genk, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.genk_mobile_listview);
        swipeRefreshLayout = view.findViewById(R.id.genk_mobile_refreshlayout);
        setupData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setUpWidget(ArrayList<Genk_Mobile_Content> datas) {
        GenkMobileAdapter adapter = new GenkMobileAdapter(datas,getActivity());
        // Log.d(TAG, "setUpWidget: " + datas.size());
        listView.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    private void setupData() {
        new AsyncTask<Void, Void, ArrayList<Genk_Mobile_Content>>() {
            ArrayList<Genk_Mobile_Content> datas = new ArrayList<>();

            @Override
            protected ArrayList<Genk_Mobile_Content> doInBackground(Void... voids) {
                try {
                    String title = "";
                    String link = "";
                    String des = "";
                    String img = "";
                    Document document = Jsoup.connect("http://genk.vn/mobile.chn").get();
                    Elements elements = document.select("ul.knsw-list").select("ul").select("li");
                    Log.d(TAG, "doInBackground: ");
                    for (Element element : elements) {
                        Element title1 = element.getElementsByTag("h4").first();
                        Element link1 = element.getElementsByTag("a").first();
                        Element des1 = element.getElementsByClass("knswli-sapo").first();
                        Element img1 = element.getElementsByTag("img").first();
                        if (title1 != null) {
                            title = title1.text();
                        }
                        if (des1 != null) {
                            des = des1.text();
                        }
                        if (img1 != null) {
                            img = img1.attr("src");
                        }
                        if (link1 != null) {
                            link = link1.attr("href");
                        }
                        String linkd = "http://genk.vn" + link;
                        datas.add(new Genk_Mobile_Content(title,linkd,des,img));
                    }
                } catch (Exception e) {

                }
                return datas;
            }

            @Override
            protected void onPostExecute(ArrayList<Genk_Mobile_Content> data) {
                super.onPostExecute(data);
                setUpWidget(data);
            }
        }.execute();

    }
}