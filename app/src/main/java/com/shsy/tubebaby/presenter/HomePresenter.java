package com.shsy.tubebaby.presenter;

import com.shsy.tubebaby.bean.UserBean;
import com.shsy.tubebaby.network.ResponseRetrofit;
import com.shsy.tubebaby.network.RetrofitPresenter;
import com.shsy.tubebaby.network.api.CallBackListener;
import com.shsy.tubebaby.network.api.IRetrofitView;
import com.shsy.tubebaby.view.HomeView;

import org.json.JSONObject;

public class HomePresenter extends RetrofitPresenter {

    private HomeView homeView;
    public HomePresenter(IRetrofitView IRetrofitView) {
        super(IRetrofitView);
        this.homeView = (HomeView) IRetrofitView;
    }

    public void getList(String msg){
        addRequest(getSelfDriverApi().sendCode(msg), new CallBackListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject json) {

            }

            @Override
            public void onFail(int url, ResponseRetrofit result) {

            }
        });
    }

}
