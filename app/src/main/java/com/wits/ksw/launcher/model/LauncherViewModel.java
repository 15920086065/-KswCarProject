package com.wits.ksw.launcher.model;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.wits.ksw.launcher.utils.SharedPreUtils;

/**
 * 作者：yt
 * 时间：2019/3/23
 * 邮箱：Y349194924@163.com
 * <p>
 * 备注：
 */
public final class LauncherViewModel extends ViewModel {
    private static final String TAG = LauncherViewModel.class.getName();

    public void restart(Context context, String pkg) {
        //ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //am.restartPackage(pkg);
        Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(pkg);
        LaunchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(LaunchIntent);
        Log.i(TAG, "restart: "+pkg);
    }

    public boolean setUiIDType(int id) {
        SharedPreUtils.getInstance().setUiIDType(id);
        Log.i(TAG, "setUiIDType: " + id);
        return true;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared: ");
    }
}
