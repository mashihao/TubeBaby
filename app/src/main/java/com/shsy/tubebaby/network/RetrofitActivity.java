package com.shsy.tubebaby.network;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.shsy.tubebaby.network.api.IRetrofitView;
import com.shsy.tubebaby.utils.DialogUtils;
import com.shsy.tubebaby.utils.MyLogger;


/**
 *
 */

public class RetrofitActivity extends AppCompatActivity implements IRetrofitView {

  protected MyLogger logger = MyLogger.getLogger(this.getClass().getSimpleName());
  private RetrofitPresenter presenter;
  private Dialog progress;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //去掉顶部标题
    getSupportActionBar().hide();

    StatusBarUtil.setColor(this, Color.argb(0,245,245,245));
  }

  @Override
  public void showRetrofitProgress(int urlType) {
    if (progress == null) {
      progress = DialogUtils.progressDialog(this);
    } else if (!progress.isShowing()) {
      progress.show();
    }
  }

  @Override
  public void hideRetrofitProgress(int urlType) {
    if (progress != null && progress.isShowing()) {
      progress.dismiss();
    }
  }

  @Override
  public void setPresenter(RetrofitPresenter presenter) {
    this.presenter = presenter;
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (presenter != null) {
      presenter.unSubscribe(false);
    }
    if (progress != null && progress.isShowing()) {
      progress.dismiss();
    }
  }
}
