package com.shsy.tubebaby.model;

/**
 * Created by Administrator on 2019/6/21.
 */

public class MyContacts {
    public String name;
    public String phone;
    public String note;
    public String remark;

    @Override
    public String toString() {
        return "MyContacts{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", note='" + note + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
