package com.shsy.tubebaby.bean;

public class UserBean {

    private String code;
    private String loginId;
    private String password;

    public UserBean(String code,String loginId,String password){
        this.code = code;
        this.loginId = loginId;
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
