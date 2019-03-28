package com.MVP.team5.universenews.ui.fragment.haitugio;


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
import com.MVP.team5.universenews.ui.fragment.haitugio.adapter.HaiTuGioBDAdapter;
import com.MVP.team5.universenews.ui.fragment.haitugio.model.HaiTuGio_BD_Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HaiTuGioBDFragment extends Fragment {

    private static final String TAG = "HaiTuGioTCFragment";
    private SwipeRefreshLayout swipeRefreshLayout;

    ListView list;
    ArrayList<HaiTuGio_BD_Content> datas;
    HaiTuGioBDAdapter adapter;


    public HaiTuGioBDFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_24h_bongda, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.haitugio_bd_listview);
        swipeRefreshLayout = view.findViewById(R.id.haitugio_bd_refresh);
        setupData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setUpWidget(ArrayList<HaiTuGio_BD_Content> datas) {
        HaiTuGioBDAdapter adapter = new HaiTuGioBDAdapter(datas, getActivity());
        Log.d(TAG, "setUpWidget: " + datas.size());
        list.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    private void setupData() {
        new AsyncTask<Void, Void, ArrayList<HaiTuGio_BD_Content>>() {
            ArrayList<HaiTuGio_BD_Content> datas = new ArrayList<>();

            @Override
            protected ArrayList<HaiTuGio_BD_Content> doInBackground(Void... voids) {
                try {
                    String title = "";
                    String link = "";
                    String des = "";
                    String img = "";
                    Document document = Jsoup.connect("http://m.dantri.com.vn/van-hoa.htm")
                            .userAgent("Mozilla/5.0 (Linux; Android 7.0; PLUS Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.98 Mobile Safari/537.36")
                            .get();
                    Elements elements = document.select("ul.lst_w").select("li");
                    Log.d(TAG, "doInBackground: ");
                    for (Element element : elements) {
                        Element title1 = element.getElementsByTag("h3").first();
                        Element link1 = element.getElementsByTag("a").first();
                        Element des1 = element.getElementsByTag("i").first();
                        Element img1 = element.getElementsByTag("img").first();
                        if (title1 != null) {
                            title = title1.text();
                        }
                        if (des1 != null) {
                            des = des1.attr("title");
                        }
                        if (img1 != null) {
                            img = img1.attr("src");
                        }
                        if (link1 != null) {
                            link = link1.attr("href");
                        }
                        String linkd = "http://m.dantri.com.vn" + link;
                        datas.add(new HaiTuGio_BD_Content(title, linkd, des, img));
                    }
                } catch (Exception e) {

                }
                return datas;
            }

            @Override
            protected void onPostExecute(ArrayList<HaiTuGio_BD_Content> data) {
                super.onPostExecute(data);
                setUpWidget(data);
            }
        }.execute();

    }
}
