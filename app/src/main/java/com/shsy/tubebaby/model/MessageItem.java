package com.shsy.tubebaby.model;

public class MessageItem {

    int type;//消息类型，用于判断消息来源
    String msg;//消息内容

    public MessageItem(int type, String msg){
        this.type = type;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
