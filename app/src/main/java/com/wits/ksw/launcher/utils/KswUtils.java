package com.wits.ksw.launcher.utils;

import android.content.pm.PackageManager;

import com.wits.ksw.KswApplication;

public class KswUtils {

    private static volatile KswUtils singleton;

    private KswUtils() {
    }

    public static KswUtils getInstance() {
        if (singleton == null) {
            synchronized (KswUtils.class) {
                if (singleton == null) {
                    singleton = new KswUtils();
                }
            }
        }
        return singleton;
    }


    /**
     * APK是否安装
     *
     * @param uri
     * @return
     */
    public static boolean isAppInstalled(String uri) {
        PackageManager pm = KswApplication.appContext.getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }
}