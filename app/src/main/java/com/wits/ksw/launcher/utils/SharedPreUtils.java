package com.wits.ksw.launcher.utils;

import android.content.SharedPreferences;

import com.wits.ksw.KswApplication;

import static android.content.Context.MODE_MULTI_PROCESS;

public class SharedPreUtils {

    private static volatile SharedPreUtils singleton;
    private SharedPreferences preferences;
    private static final String UI_ID_TYLE = "UI_ID_TYLE";

    private SharedPreUtils() {
        preferences = KswApplication.appContext.getSharedPreferences("kswcar", MODE_MULTI_PROCESS);
    }

    public static SharedPreUtils getInstance() {
        if (singleton == null) {
            synchronized (SharedPreUtils.class) {
                if (singleton == null) {
                    singleton = new SharedPreUtils();
                }
            }
        }
        return singleton;
    }

    public int getUiIDType() {
        return preferences.getInt(UI_ID_TYLE, 6);
    }

    public void setUiIDType(int idType) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(UI_ID_TYLE, idType);
        editor.apply();
    }
}