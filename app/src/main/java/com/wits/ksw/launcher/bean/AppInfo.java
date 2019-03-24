package com.wits.ksw.launcher.bean;

import android.graphics.drawable.Drawable;

/**
 * 作者：yt
 * 时间：2019/3/23
 * 邮箱：Y349194924@163.com
 * <p>
 * 备注：
 */
public final class AppInfo {
    public String appLable;
    public Drawable appIcon;
    public String apppkg;
    public String className;

    public String getAppLable() {
        return appLable;
    }

    public void setAppLable(String appLable) {
        this.appLable = appLable;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getApppkg() {
        return apppkg;
    }

    public void setApppkg(String apppkg) {
        this.apppkg = apppkg;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "appInfo{" +
                "appLable='" + appLable + '\'' +
                ", appIcon=" + appIcon +
                ", apppkg='" + apppkg + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

}
