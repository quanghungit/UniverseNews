package com.MVP.team5.universenews.ui.fragment.Saved;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.Utils.DataBase.MyDatabaseHelper;
import com.MVP.team5.universenews.ui.fragment.Saved.Adapter.SavedAdapter;
import com.MVP.team5.universenews.ui.model.NewsDetailModel;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedFragment extends Fragment {

    RecyclerView recyclerView;
    SavedAdapter adapter;
    List<NewsDetailModel> mList;

    MyDatabaseHelper databaseHelper;
    FloatingActionButton fabDelete, fabSync, fabUpdate;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

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

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("News");

        initView(view);
        setUpRecyclerView();
        attachEvent();
    }

    void setUpRecyclerView() {
        mList = databaseHelper.getNews();
        adapter = new SavedAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    void initView(View view) {
        databaseHelper = new MyDatabaseHelper(getContext());
        recyclerView = view.findViewById(R.id.rvSaved);

        fabDelete = view.findViewById(R.id.savedFAB_DeleteAll);
        fabSync = view.findViewById(R.id.savedFAB_Sync);
        fabUpdate = view.findViewById(R.id.savedFAB_Update);
    }

    void attachEvent() {
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.clearAll();
                mList.clear();
                adapter.notifyDataSetChanged();
                databaseReference.child("news").removeValue();
                Toast.makeText(getContext(), "Delete successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        fabSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("news").setValue(mList);
                Toast.makeText(getContext(), "Synchronization successful!", Toast.LENGTH_SHORT).show();
            }
        });

        fabUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<NewsDetailModel> newsDetailModels = new ArrayList<>();
                        newsDetailModels.clear();
                        databaseHelper.clearAll();
                        mList.clear();
                        for (DataSnapshot snapshot: dataSnapshot.child("news").getChildren()) {
                            NewsDetailModel newsDetailModel = snapshot.getValue(NewsDetailModel.class);
                            newsDetailModels.add(newsDetailModel);
                            databaseHelper.addNews(newsDetailModel);
                            mList.add(newsDetailModel);
                        }
                        adapter.notifyDataSetChanged();
                        if (databaseHelper.getCount() == 0) {
                            Toast.makeText(getContext(), "Database is empty!", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(getContext(), "Synchronization successful!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

}
