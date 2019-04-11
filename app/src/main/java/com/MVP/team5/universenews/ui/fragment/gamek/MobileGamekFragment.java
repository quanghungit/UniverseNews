package com.MVP.team5.universenews.ui.fragment.gamek;


import android.annotation.SuppressLint;
import android.graphics.Color;
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
import com.MVP.team5.universenews.ui.Utils.Utilities;
import com.MVP.team5.universenews.ui.fragment.gamek.adapter.GamekMobileAdapter;
import com.MVP.team5.universenews.ui.fragment.gamek.adapter.GamekPcAdapter;
import com.MVP.team5.universenews.ui.fragment.gamek.model.Gamek_Mobile_Content;
import com.MVP.team5.universenews.ui.fragment.gamek.model.Gamek_PC_Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MobileGamekFragment extends Fragment {
    private static final String TAG = "MobileGamekFragment";
    private ListView listView;
    private ArrayList<Gamek_Mobile_Content> datas;
    private GamekMobileAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    public MobileGamekFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mobile_gamek, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.gamek_mobile_listview);
        swipeRefreshLayout = view.findViewById(R.id.gamek_mobile_refreshlayout);
        if (Utilities.getNight(getContext())) {
            swipeRefreshLayout.setBackgroundColor(Color.parseColor("#E8BBAF74"));
        }
        setupData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setUpWidget(ArrayList<Gamek_PC_Content> datas) {
        GamekPcAdapter adapter = new GamekPcAdapter(datas,getActivity());
        Log.d(TAG, "setUpWidget: " + datas.size());
        listView.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    private void setupData() {
        new AsyncTask<Void, Void, ArrayList<Gamek_PC_Content>>() {
            ArrayList<Gamek_PC_Content> datas = new ArrayList<>();

            @Override
            protected ArrayList<Gamek_PC_Content> doInBackground(Void... voids) {
                try {
                    String title = "";
                    String link = "";
                    String des = "";
                    String img = "";
                    Document document = Jsoup.connect("http://gamek.vn/mobile-social.chn").get();
                    Elements elements = document.select("div.content").select("ul").select("li.top");
                    Log.d(TAG, "doInBackground: ");
                    for (Element element : elements) {
                        Element title1 = element.getElementsByTag("h3").first();
                        Element link1 = element.getElementsByTag("a").first();
                        Element des1 = element.getElementsByTag("p").first();
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
                        String linkd = "http://gamek.vn" + link;
                        datas.add(new Gamek_PC_Content(title,linkd,des,img));
                    }
                } catch (Exception e) {

                }
                return datas;
            }

            @Override
            protected void onPostExecute(ArrayList<Gamek_PC_Content> data) {
                super.onPostExecute(data);
                setUpWidget(data);
            }
        }.execute();

    }
}