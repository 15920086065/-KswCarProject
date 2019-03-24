package com.wits.ksw;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.wits.ksw.launcher.utils.SharedPreUtils;

/**
 * 作者：yt
 * 时间：2019/3/23
 * 邮箱：Y349194924@163.com
 * <p>
 * 备注：
 */
public class KswApplication extends Application {
    private static final String TAG = KswApplication.class.getName();

    public static Context appContext;

    @Override
    public void onCreate() {
        appContext = this;
        super.onCreate();
    }
}
