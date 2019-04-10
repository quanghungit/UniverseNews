package com.MVP.team5.universenews.ui.fragment.Saved;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.Utils.DataBase.MyDatabaseHelper;
import com.MVP.team5.universenews.ui.fragment.Saved.Adapter.SavedAdapter;
import com.MVP.team5.universenews.ui.model.NewsDetailModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedFragment extends Fragment {

    RecyclerView recyclerView;
    SavedAdapter adapter;
    List<NewsDetailModel> mList;

    MyDatabaseHelper databaseHelper;

    public SavedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseHelper = new MyDatabaseHelper(getContext());
        recyclerView = view.findViewById(R.id.rvSaved);

        setUpRecyclerView();
    }

    void setUpRecyclerView() {
        mList = databaseHelper.getNews();
        adapter = new SavedAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

}
