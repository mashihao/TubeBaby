package com.shsy.tubebaby.network.api;


import com.shsy.tubebaby.network.RetrofitPresenter;

/**
 * 界面网络回调IView基础接口
 */

public interface IRetrofitView {
  void showRetrofitProgress(int urlType);

  void hideRetrofitProgress(int urlType);

  void setPresenter(RetrofitPresenter presenter);
}
