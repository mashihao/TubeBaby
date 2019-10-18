package com.shsy.tubebaby.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.shsy.tubebaby.R;
import com.shsy.tubebaby.activity.SuccessActivity;
import com.shsy.tubebaby.adapter.RecyclerAdapter;
import com.shsy.tubebaby.bean.KnowBean;
import com.shsy.tubebaby.model.Banner;
import com.shsy.tubebaby.presenter.HomePresenter;
import com.shsy.tubebaby.view.HomeView;
import com.shsy.tubebaby.view.bannerview.BannerView;
import com.shsy.tubebaby.view.bannerview.BannerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeView {

    @BindView(R.id.banner_home)
    BannerView bannerHome;
    @BindView(R.id.rl_home_wiki)
    RelativeLayout rlHomeWiki;
    @BindView(R.id.rl_home_success)
    RelativeLayout rlHomeSuccess;
    @BindView(R.id.tv_home_refresh)
    TextView tvHomeRefresh;
    @BindView(R.id.recycle_home)
    RecyclerView recycleHome;
    private View view;

    private ArrayList<KnowBean> mlist;
    KnowBean knowBean;
    RecyclerAdapter myAdapter;

    private Intent intent;
    private HomePresenter homePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(getActivity()).inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recycleHome.setLayoutManager(linearLayoutManager);
        homePresenter = new HomePresenter(this);
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
        recycleHome.setAdapter(myAdapter);

        ArrayList<Banner> banners = new ArrayList<>();
        banners.add(new Banner());
        initBanner(banners);

    }

    private void initBanner(List<Banner> data) {
        bannerHome.setData(data, new BannerViewHolder(getActivity()) {
            ImageView ivBanner;

            @Override
            public View createView() {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_banner, null, false);
                return view;
            }
            @Override
            public void bind(int position, Banner s) {
            }
        });
        //bannerView.setNesting(true, 20);


        bannerHome.setOnPageClickListener(new BannerView.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
                String toast = "this is page " + data.get(position);
                //Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.rl_home_wiki,R.id.rl_home_success,R.id.tv_home_refresh})
    void OnClick(View v){
        switch (v.getId()){
            case R.id.rl_home_wiki:

                break;
            case R.id.rl_home_success:
                intent = new Intent(getActivity(), SuccessActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_home_refresh:
                homePresenter.getList("18251853275");
                break;
        }
    }

    @Override
    public void getList() {

    }
}
