package com.shsy.tubebaby.network.api;

/**
 * 成功返回回调函数  只包含成功
 */

public interface SuccessCallBackListener<T> {
  void onSuccess(T result);
}
