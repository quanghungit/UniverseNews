package com.MVP.team5.universenews.ui.fragment.Saved.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.ui.Utils.DataBase.MyDatabaseHelper;
import com.MVP.team5.universenews.ui.Utils.Utilities;
import com.MVP.team5.universenews.ui.activity.MainActivity;
import com.MVP.team5.universenews.ui.fragment.Saved.Detail.SavedDetailFragment;
import com.MVP.team5.universenews.ui.fragment.gamek.GamekWebviewFragment;
import com.MVP.team5.universenews.ui.model.NewsDetailModel;

import java.util.List;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder> {

    List<NewsDetailModel> mList;
    MyDatabaseHelper myDatabaseHelper;
    Context context;

    public SavedAdapter(List<NewsDetailModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.custom_saved_rv,viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        myDatabaseHelper = new MyDatabaseHelper(context);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {
        final NewsDetailModel news = mList.get(i);

        if (Utilities.getNight(context)) {
            holder.itemView.setBackgroundColor(Color.parseColor("#E8BBAF74"));
        }
        holder.title.setText(news.getTilte());
        holder.desc.setText(news.getDesc());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myDatabaseHelper.deleteNews(news);
                Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
                mList.remove(i);
                notifyDataSetChanged();
                return false;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).changeFragment(SavedDetailFragment.newInstance(mList.get(i)));
                ((MainActivity)context).setTitle(mList.get(i).getTilte());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.customTitle);
            desc = itemView.findViewById(R.id.customDescription);
        }

    }
}
