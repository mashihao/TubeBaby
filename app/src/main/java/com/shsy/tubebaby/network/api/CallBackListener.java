package com.shsy.tubebaby.network.api;


import com.shsy.tubebaby.network.ResponseRetrofit;

/**
 * 回调的接口
 */

public interface CallBackListener<T> {
  void onSuccess(T result);

  void onFail(int url, ResponseRetrofit result);
}
