package com.shsy.tubebaby.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.shsy.tubebaby.R;
import com.shsy.tubebaby.bean.KnowBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    static ArrayList<KnowBean> mlist;
    ViewHolder viewHolder;
    KnowBean knowBean;


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_know_type)
        TextView tvKnowType;
        @BindView(R.id.tv_know_title)
        TextView tvKnowTitle;
        @BindView(R.id.tv_know_content)
        TextView tvKnowContent;
        @BindView(R.id.tv_know_hot)
        TextView tvKnowHot;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public RecyclerAdapter(ArrayList<KnowBean> beans) {
        this.mlist = beans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_today_know, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        knowBean = mlist.get(position);
        viewHolder.tvKnowType.setText(knowBean.getType());
        viewHolder.tvKnowTitle.setText(knowBean.getTitle());
        viewHolder.tvKnowContent.setText(knowBean.getContent());
        viewHolder.tvKnowHot.setText(knowBean.getHot());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
