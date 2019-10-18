package com.shsy.tubebaby.presenter;

import android.util.Log;

import com.shsy.tubebaby.bean.UserBean;
import com.shsy.tubebaby.network.ResponseRetrofit;
import com.shsy.tubebaby.network.RetrofitPresenter;
import com.shsy.tubebaby.network.api.CallBackListener;
import com.shsy.tubebaby.network.api.IRetrofitView;
import com.shsy.tubebaby.view.LoginView;

import org.json.JSONObject;

import io.reactivex.annotations.NonNull;

public class LoginPresenter extends RetrofitPresenter {

    LoginView iView;
    public LoginPresenter(@NonNull IRetrofitView IRetrofitView) {
        super(IRetrofitView);
        this.iView = (LoginView) IRetrofitView;
    }

    public void login(UserBean bean){
        addRequest(getSelfDriverApi().login(bean), new CallBackListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject json) {
                iView.loginsuccess(json);
            }

            @Override
            public void onFail(int url, ResponseRetrofit result) {
                iView.loginfail();
            }
        });
    }

    public void sendCode(String  phoneNumber){
        addRequest(getSelfDriverApi().sendCode(phoneNumber), new CallBackListener<JSONObject>() {
            @Override
            public void onSuccess(JSONObject json) {
                Log.e("MyTag","json:"+json.toString());
                iView.loginsuccess(json);
            }

            @Override
            public void onFail(int url, ResponseRetrofit result) {
                iView.loginfail();
            }
        });
    }


}
