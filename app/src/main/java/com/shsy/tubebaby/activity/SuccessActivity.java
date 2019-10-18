package com.shsy.tubebaby.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shsy.tubebaby.R;
import com.shsy.tubebaby.adapter.RecyclerAdapter;
import com.shsy.tubebaby.bean.KnowBean;
import com.shsy.tubebaby.network.RetrofitActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessActivity extends RetrofitActivity {


    @BindView(R.id.imv_success_back)
    ImageView imvSuccessBack;
    @BindView(R.id.recycle_success)
    RecyclerView recycleSuccess;

    private ArrayList<KnowBean> mlist;
    KnowBean knowBean;
    RecyclerAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_list);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycleSuccess.setLayoutManager(linearLayoutManager);
        mlist = new ArrayList<>();
        for (int i = 0;i<20;i++){
            knowBean = new KnowBean();
            knowBean.setType("科普"+i);
            knowBean.setTitle(i+"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试"+i);
            knowBean.setContent(i+"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试"+i);
            knowBean.setHot(""+(i+1900));
            mlist.add(knowBean);
        }
        myAdapter = new RecyclerAdapter(mlist);
        recycleSuccess.setAdapter(myAdapter);
    }

    @OnClick(R.id.imv_success_back)
    void OnClick(View v){
       switch (v.getId()){
           case R.id.imv_success_back:
               finish();
               break;
       }
    }
}
