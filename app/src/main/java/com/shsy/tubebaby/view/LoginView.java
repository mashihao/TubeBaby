package com.shsy.tubebaby.view;

import com.shsy.tubebaby.network.api.IRetrofitView;

import org.json.JSONObject;

public interface LoginView extends IRetrofitView {

    void loginsuccess(JSONObject json);

    void loginfail();

}
