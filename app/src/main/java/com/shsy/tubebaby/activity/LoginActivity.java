package com.shsy.tubebaby.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.shsy.tubebaby.R;
import com.shsy.tubebaby.app.MyApplication;
import com.shsy.tubebaby.bean.UserBean;
import com.shsy.tubebaby.network.RetrofitActivity;
import com.shsy.tubebaby.presenter.LoginPresenter;
import com.shsy.tubebaby.utils.CountDownTimerUtils;
import com.shsy.tubebaby.utils.SharedPreferencesUtils;
import com.shsy.tubebaby.utils.ToastUtil;
import com.shsy.tubebaby.view.LoginView;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends RetrofitActivity implements LoginView {
    @BindView(R.id.imv_register_index)
    ImageView imvRegisterIndex;
    @BindView(R.id.rl_login_register)
    RelativeLayout rlLoginRegister;
    @BindView(R.id.imv_login_index)
    ImageView imvLoginIndex;
    @BindView(R.id.rl_login_login)
    RelativeLayout rlLoginLogin;
    @BindView(R.id.et_register_num)
    EditText etRegisterNum;
    @BindView(R.id.et_register_code)
    EditText etRegisterCode;
    @BindView(R.id.tv_register_code)
    TextView tvRegisterCode;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.btn_login_register)
    Button btnLoginRegister;
    @BindView(R.id.ll_register)
    LinearLayout llRegister;
    @BindView(R.id.et_login_num)
    EditText etLoginNum;
    @BindView(R.id.et_login_psw)
    EditText etLoginPsw;
    @BindView(R.id.btn_login_login)
    Button btnLoginLogin;
    @BindView(R.id.tv_login_reset)
    TextView tvLoginReset;
    @BindView(R.id.ll_login_login)
    LinearLayout llLoginLogin;
    private LoginPresenter presenter;

    private UserBean bean;

    private Intent intent;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private String phone;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        setData();
    }

    void setData(){
        sharedPreferencesUtils = SharedPreferencesUtils.getInstance(this);
        phone = sharedPreferencesUtils.getSP(MyApplication.USER_PHONE);
        password = sharedPreferencesUtils.getSP(MyApplication.USER_PSW);
        if (!phone.equals("")&&!password.equals("")){
            if (MyApplication.isLogin){
                login();
            }else{
                etLoginNum.setText(phone);
                etLoginPsw.setText(password);
            }

        }
    }

    @OnClick({R.id.tv_login_reset,R.id.tv_register_code,R.id.rl_login_login,R.id.rl_login_register,
                R.id.btn_login_login,R.id.btn_login_register})
    void init(View view) {
        switch (view.getId()) {
            case R.id.rl_login_login:
                imvLoginIndex.setVisibility(View.VISIBLE);
                llLoginLogin.setVisibility(View.VISIBLE);
                imvRegisterIndex.setVisibility(View.GONE);
                llRegister.setVisibility(View.GONE);
                break;
            case R.id.rl_login_register:
                imvLoginIndex.setVisibility(View.GONE);
                llLoginLogin.setVisibility(View.GONE);
                imvRegisterIndex.setVisibility(View.VISIBLE);
                llRegister.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_login_reset:
                intent = new Intent(LoginActivity.this,ResetPswActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_register_code:
                if (TextUtils.isEmpty(etRegisterNum.getText())&&TextUtils.getTrimmedLength(etRegisterNum.getText())!=11){
                    ToastUtil.makeText("号码输入不正确");
                }else{
                    //在此处进行获取验证码操作，以及按钮变灰进入倒计时
                    presenter.sendCode(etRegisterNum.getText().toString());
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(tvRegisterCode, 60000, 1000);
                    mCountDownTimerUtils.start();
                }
                break;
            case R.id.btn_login_login:
                if (TextUtils.isEmpty(etLoginNum.getText())){
                    ToastUtil.makeText("手机号码不能为空");
                }else if (TextUtils.getTrimmedLength(etLoginNum.getText())!=11){
                    ToastUtil.makeText("请输入正确的手机号");
                }else if(TextUtils.isEmpty(etLoginPsw.getText())){
                    ToastUtil.makeText("请输入密码");
                }else{
                    sharedPreferencesUtils.putSP(MyApplication.USER_PHONE,etLoginNum.getText().toString());
                    sharedPreferencesUtils.putSP(MyApplication.USER_PSW,etLoginPsw.getText().toString());
                    login();
                }
                break;
            case R.id.btn_login_register:
                if (TextUtils.isEmpty(etRegisterNum.getText())){
                    ToastUtil.makeText("手机号码不能为空");
                }else if (TextUtils.getTrimmedLength(etRegisterNum.getText())!=11){
                    ToastUtil.makeText("请输入正确的手机号");
                }else if(TextUtils.isEmpty(etRegisterCode.getText())){
                    ToastUtil.makeText("请输入验证码");
                }else if(TextUtils.isEmpty(etRegisterPassword.getText())){
                    ToastUtil.makeText("请输入密码");
                }else{
                    //在此处执行注册操作
                }
                break;
        }
    }

    void login(){

        //在此处执行登录操作
        intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }



    @Override
    public void loginsuccess(JSONObject json) {

    }

    @Override
    public void loginfail() {

    }
}
