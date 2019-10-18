package com.shsy.tubebaby.network;

import com.shsy.tubebaby.network.api.ApiService;
import com.shsy.tubebaby.network.api.CallBackListener;
import com.shsy.tubebaby.network.api.IRetrofitView;
import com.shsy.tubebaby.network.api.SuccessCallBackListener;
import com.shsy.tubebaby.utils.MyLogger;
import com.shsy.tubebaby.utils.ToastUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 网络请求提供者 作为 mvp 的 presenter 的基类，用于网络请求（逻辑处理）
 */

public class RetrofitPresenter {
    // 基类的view
    protected IRetrofitView IRetrofitView;

    protected MyLogger mloor = MyLogger.getLogger(this.getClass().getSimpleName());

    private ApiService apiService;

    /**
     * 管理所有的Disposable
     */
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RetrofitPresenter(@NonNull IRetrofitView IRetrofitView) {
        this.IRetrofitView = IRetrofitView;
        IRetrofitView.setPresenter(this);
    }

    /**
     * RXjava取消注册，以避免内存泄漏
     *
     * @param isNewRequest
     */
    public void unSubscribe(boolean isNewRequest) {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    /**
     * 添加网络请求
     * @param observable   请求主体
     * @param callListener 请求结果回调
     * @param requestType  请求类型
     * @param isProgress   是否显示加载框
     */
    protected <T> void addBaseRequest(Observable<T> observable,
                                      final CallBackListener<T> callListener,
                                      final int requestType,
                                      final boolean isProgress,
                                      final boolean isToast) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(
                observable.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (IRetrofitView != null && isProgress) {
                                    IRetrofitView.showRetrofitProgress(requestType);
                                }
//                                mloor.d("doOnSubscribe------" + Thread.currentThread().getName());
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                if (IRetrofitView != null && isProgress) {
                                    IRetrofitView.hideRetrofitProgress(requestType);
                                }
                                mloor.d("doOnComplete-----" + Thread.currentThread().getName());
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<T>() {
                            @Override
                            public void accept(T t) throws Exception {
                                if (t instanceof ResponseRetrofit) {
                                    ResponseRetrofit res = (ResponseRetrofit) t;
                                    if (res.getCode()==0) {
                                        callListener.onSuccess(t);
                                    } else {
                                        if (isToast) {
                                            ToastUtil.makeText(res.getMessage());
                                        }
                                        callListener.onFail(res.getCode(), res);
                                    }
                                } else {
                                    //新方法不走  外壳
                                    callListener.onSuccess(t);
                                }
                            }
                        }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                if (IRetrofitView != null && isProgress) {
                                    IRetrofitView.hideRetrofitProgress(requestType);
                                }
                                if (isToast) {
                                    ToastUtil.makeText("网络异常，请稍后重试！");
                                }
                                mloor.d("Throwable-----" + throwable.getMessage());
                            }
                        })
        );
    }


    /**
     * 只返回正确回调
     *
     * @param observable   请求主体
     * @param callListener 成功回调
     */
    protected <T> void addRequest(Observable<T> observable,
                                  final SuccessCallBackListener<T> callListener) {
        this.addBaseRequest(observable, new CallBackListener<T>() {
            @Override
            public void onSuccess(T o) {
                if (callListener != null) {
                    callListener.onSuccess(o);
                }
            }

            @Override
            public void onFail(int Url, ResponseRetrofit result) {
            }
        }, 0, true);
    }

    protected <T> void addBaseRequest(Observable<T> observable,
                                      final CallBackListener<T> callListener,
                                      final int requestType,
                                      final boolean isProgress) {
        addBaseRequest(observable, callListener, requestType, isProgress, true);
    }


    protected <T> void addRequest(Observable<T> observable,
                                  final SuccessCallBackListener<T> callListener, boolean isProgress) {
        this.addBaseRequest(observable, new CallBackListener<T>() {
            @Override
            public void onSuccess(T o) {
                if (callListener != null) {
                    callListener.onSuccess(o);
                }
            }

            @Override
            public void onFail(int Url, ResponseRetrofit result) {
            }
        }, 0, isProgress);
    }

    protected <T> void addRequest(Observable<T> observable,
                                  final SuccessCallBackListener<T> callListener, boolean isProgress, boolean isToast) {
        this.addBaseRequest(observable, new CallBackListener<T>() {
            @Override
            public void onSuccess(T o) {
                if (callListener != null) {
                    callListener.onSuccess(o);
                }
            }

            @Override
            public void onFail(int Url, ResponseRetrofit result) {
            }
        }, 0, isProgress, isToast);
    }

    protected <T> void addRequest(Observable<T> observable,
                                  final SuccessCallBackListener<T> callListener, int requestType) {
        this.addBaseRequest(observable, new CallBackListener<T>() {
            @Override
            public void onSuccess(T o) {
                if (callListener != null) {
                    callListener.onSuccess(o);
                }
            }

            @Override
            public void onFail(int Url, ResponseRetrofit result) {
            }
        }, requestType, true);
    }

    /**
     * 只返回正确与错误回调
     * 不弹出toast提醒
     *
     * @param observable   请求主体
     * @param callListener 成功失败回调
     */
    protected <T> void addRequest(Observable<T> observable, final CallBackListener<T> callListener) {
        this.addBaseRequest(observable, callListener, 0, true, true);
    }

    /**
     * 只返回正确与错误回调
     * 不弹出toast提醒
     *
     * @param observable   请求主体
     * @param callListener 成功失败回调
     */
    protected <T> void addRequest(Observable<T> observable, final CallBackListener<T> callListener,
                                  boolean isProgress) {
        this.addBaseRequest(observable, callListener, 0, isProgress, true);
    }


    /**
     * 获取相应的服务接口
     *
     * @return
     */
    protected ApiService getSelfDriverApi() {
        if (apiService == null) {
            apiService = RetrofitClient.getInstance().getRetrofit().create(ApiService.class);
        }
        return apiService;
    }

}
