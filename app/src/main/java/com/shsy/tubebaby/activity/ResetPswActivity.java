package com.shsy.tubebaby.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.shsy.tubebaby.R;
import com.shsy.tubebaby.network.RetrofitActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPswActivity extends RetrofitActivity {

    @BindView(R.id.imv_reset_back)
    ImageView imvResetBack;
    @BindView(R.id.et_reset_num)
    EditText etResetNum;
    @BindView(R.id.et_reset_code)
    EditText etResetCode;
    @BindView(R.id.tv_reset_code)
    TextView tvResetCode;
    @BindView(R.id.et_reset_password)
    EditText etResetPassword;
    @BindView(R.id.btn_reset)
    Button btnReset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpsw);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.btn_reset,R.id.tv_reset_code,R.id.imv_reset_back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.imv_reset_back:
                finish();
                break;
            case R.id.btn_reset:
                break;
            case R.id.tv_reset_code:
                break;
        }
    }
}
