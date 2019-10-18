package com.shsy.tubebaby.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class MyApplication extends Application {
    private static MyApplication myApplication;

    public static String USER_PHONE="phone";
    public static String USER_PSW="password";
    public static boolean isLogin = true;

    public static String localVersion;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        localVersion = getLocalVersion(this);
    }
    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static String getLocalVersion(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
}
