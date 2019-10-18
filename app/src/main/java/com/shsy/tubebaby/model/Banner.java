package com.shsy.tubebaby.model;

/**
 * describe : 
 * author   : 马世豪
 * time     : 2019/9/19 18:10
 */
public class Banner {


    private String describe;
    private String name;
    private int type ;
    private boolean show;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}

