package com.shsy.tubebaby.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.shsy.tubebaby.R;
import com.shsy.tubebaby.app.MyApplication;
import com.shsy.tubebaby.model.MessageItem;
import com.shsy.tubebaby.model.MessageModel;
import com.shsy.tubebaby.network.RetrofitActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends RetrofitActivity {

    @BindView(R.id.imv_setting_back)
    ImageView imvSettingBack;
    @BindView(R.id.btn_setting_return)
    Button btnSettingReturn;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imv_setting_back,R.id.btn_setting_return})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.imv_setting_back:
                finish();
                break;
            case R.id.btn_setting_return:
                MyApplication.isLogin = false;
                MessageItem messageItem = new MessageItem(1,"11");
                EventBus.getDefault().postSticky(new MessageModel(messageItem));
                intent = new Intent(SettingActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
