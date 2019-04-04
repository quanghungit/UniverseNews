package com.MVP.team5.universenews.ui.fragment.gamek.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.MVP.team5.universenews.R;
import com.MVP.team5.universenews.databinding.GamekTrangchuCustomviewBinding;
import com.MVP.team5.universenews.ui.Utils.Utilities;
import com.MVP.team5.universenews.ui.activity.MainActivity;
import com.MVP.team5.universenews.ui.fragment.gamek.GamekWebviewFragment;
import com.MVP.team5.universenews.ui.fragment.gamek.model.Gamek_Mobile_Content;
import com.MVP.team5.universenews.ui.model.SettingsModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by B350M on 12/1/2017.
 */

public class GamekMobileAdapter extends BaseAdapter {
    private ArrayList<Gamek_Mobile_Content> list;
    private Context context;

    GamekTrangchuCustomviewBinding binding;
    SettingsModel settingsModel;

    public GamekMobileAdapter(ArrayList<Gamek_Mobile_Content> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Gamek_Mobile_Content getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {

            settingsModel = new SettingsModel(
                    Utilities.getTheme(context),
                    Utilities.getFont(context),
                    Utilities.getNight(context)
            );
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.gamek_trangchu_customview, viewGroup, false);
            binding.setSetting(settingsModel);
          //  view = LayoutInflater.from(context).inflate(R.layout.gamek_trangchu_customview,null);
            view = binding.getRoot();
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
           holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(getItem(i).getTitle());
        holder.title.setTextSize(settingsModel.getFontSize());
        holder.des.setText(getItem(i).getDes());
        holder.des.setTextSize(settingsModel.getFontSize());
        Glide.with(context).load(list.get(i).getImg()).into(holder.img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).changeFragment(GamekWebviewFragment.newInstance(i, list.get(i).getLink()));
                ((MainActivity)context).setTitle(list.get(i).getTitle());
            }
        });
        view.setTag(holder);
        return view;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,des;
        CardView cardView;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.gamek_trangchu_customview_title);
            des = itemView.findViewById(R.id.gamek_trangchu_customview_des);
            img = itemView.findViewById(R.id.gamek_trangchu_customview_img);
            cardView = itemView.findViewById(R.id.gamek_trangchu_cardview);
        }
    }
}

