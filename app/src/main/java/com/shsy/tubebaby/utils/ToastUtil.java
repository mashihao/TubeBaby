package com.shsy.tubebaby.utils;

import android.os.Looper;
import android.widget.Toast;

import com.shsy.tubebaby.app.MyApplication;


/**
 * 吐司
 */

public class ToastUtil {
    public static void makeText(String text) {

        Toast.makeText(MyApplication.getMyApplication(), text, Toast.LENGTH_SHORT).show();
    }

    public static void makeText(Object text) {
        Toast.makeText(MyApplication.getMyApplication(), text.toString(), Toast.LENGTH_SHORT).show();
    }

    public static void makeText(int text) {
        Toast.makeText(MyApplication.getMyApplication(),
                MyApplication.getMyApplication().getResources().getString(text), Toast.LENGTH_SHORT).show();
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }



}
