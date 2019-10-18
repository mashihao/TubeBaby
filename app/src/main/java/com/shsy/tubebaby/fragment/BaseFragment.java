package com.shsy.tubebaby.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shsy.tubebaby.network.RetrofitPresenter;
import com.shsy.tubebaby.network.api.IRetrofitView;
import com.shsy.tubebaby.utils.DialogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseFragment extends Fragment implements IRetrofitView {

    private RetrofitPresenter presenter;
    private Dialog progress;
    private Unbinder unbinder;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unSubscribe(false);
        }
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }

    @Override
    public void showRetrofitProgress(int urlType) {
        if (progress == null) {
            progress = DialogUtils.progressDialog(getActivity());
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


}
