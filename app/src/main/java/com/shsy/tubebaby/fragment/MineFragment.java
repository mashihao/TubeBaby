package com.shsy.tubebaby.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shsy.tubebaby.R;
import com.shsy.tubebaby.activity.SettingActivity;
import com.shsy.tubebaby.app.MyApplication;
import com.shsy.tubebaby.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends Fragment {


    @BindView(R.id.imv_mine_pic)
    ImageView imvMinePic;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.imv_mine_write)
    ImageView imvMineWrite;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.tv_mine_num)
    TextView tvMineNum;
    @BindView(R.id.rl_mine_message)
    RelativeLayout rlMineMessage;
    @BindView(R.id.rl_mine_user)
    RelativeLayout rlMineUser;
    @BindView(R.id.rl_mine_setting)
    RelativeLayout rlMineSetting;
    @BindView(R.id.rl_mine_version)
    RelativeLayout rlMineVersion;
    @BindView(R.id.rl_mine_about)
    RelativeLayout rlMineAbout;

    View view;
    @BindView(R.id.tv_mine_version)
    TextView tvMineVersion;

    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.from(getActivity()).inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        tvMineVersion.setText("V"+MyApplication.localVersion);
        return view;
    }

    @OnClick({R.id.rl_mine_message, R.id.rl_mine_user, R.id.rl_mine_version, R.id.rl_mine_setting, R.id.rl_mine_about})
    void OnClick(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_message:
                ToastUtil.makeText("消息");
                break;
            case R.id.rl_mine_about:
                ToastUtil.makeText("消息");
                break;
            case R.id.rl_mine_user:
                ToastUtil.makeText("消息");
                break;
            case R.id.rl_mine_version:
                ToastUtil.makeText("消息");
                break;
            case R.id.rl_mine_setting:
                intent = new Intent(view.getContext(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
