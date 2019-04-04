package com.MVP.team5.universenews.ui.fragment.dantri;


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
import com.MVP.team5.universenews.ui.fragment.dantri.adapter.DanTriVHAdapter;
import com.MVP.team5.universenews.ui.fragment.dantri.model.DanTri_GD_Content;
import com.MVP.team5.universenews.ui.fragment.dantri.model.DanTri_VH_Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanTriVanHoaFragment extends Fragment {

    private static final String TAG = "HaiTuGioTCFragment";
    private SwipeRefreshLayout swipeRefreshLayout;

    ListView list;
    ArrayList<DanTri_VH_Content> datas;
    DanTriVHAdapter adapter;


    public DanTriVanHoaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dan_tri_van_hoa, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.dantri_vh_listview);
        swipeRefreshLayout = view.findViewById(R.id.dantri_vh_refresh);
        setupData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setUpWidget(ArrayList<DanTri_VH_Content> datas) {
        DanTriVHAdapter adapter = new DanTriVHAdapter(datas, getActivity());
        Log.d(TAG, "setUpWidget: " + datas.size());
        list.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    private void setupData() {
        new AsyncTask<Void, Void, ArrayList<DanTri_VH_Content>>() {
            ArrayList<DanTri_VH_Content> datas = new ArrayList<>();

            @Override
            protected ArrayList<DanTri_VH_Content> doInBackground(Void... voids) {
                try {
                    String title = "";
                    String link = "";
                    String des = "";
                    String img = "";
                    Document document = Jsoup.connect("https://vnexpress.net/phap-luat").get();

                    Elements elements = document.select("article.list_news");


                    System.out.println("Bao moi ne----" + elements.size());
                    Log.d(TAG, "doInBackground: ");
                    for (Element element : elements) {

                        Element title1 = element.select("h4.title_news").first();
                        Element link1 = element.select("div.thumb_art > a").first();
                        Element img1 = element.getElementsByTag("img").first();
                        Element des1 = element.select("p.description").first();
                        if (title1 != null) {
                            title = title1.text();
                        }

                        if (link1 != null) {
                            link = link1.attr("href");
                        }

                        if (img1 != null) {
                            img = img1.attr("src");
                        }

                        if (des1 != null) {
                            des = des1.text();
                        }
                        datas.add(new DanTri_VH_Content(title, link, des, img));
                    }
                } catch (Exception e) {

                }
                return datas;
            }

            @Override
            protected void onPostExecute(ArrayList<DanTri_VH_Content> data) {
                super.onPostExecute(data);
                setUpWidget(data);
            }
        }.execute();
    }
}
