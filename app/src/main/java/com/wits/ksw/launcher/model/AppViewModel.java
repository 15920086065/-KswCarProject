package com.wits.ksw.launcher.model;

import android.arch.lifecycle.ViewModel;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.launcher.base.BaseListAdpater;
import com.wits.ksw.launcher.bean.AppInfo;
import com.wits.ksw.launcher.view.AppsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yt
 * 时间：2019/3/23
 * 邮箱：Y349194924@163.com
 * <p>
 * 备注：
 */
@BindingMethods({@BindingMethod(type = AppsActivity.class, attribute = "setOnItemClickListener", method = "onItemClickListener")
,@BindingMethod(type = AppsActivity.class, attribute = "setOnItemLongClickListener", method = "onItemLongClickListener")})
public final class AppViewModel extends ViewModel {
    //apps
    public ObservableField<BaseListAdpater<AppInfo>> listAdpater = new ObservableField();

    public void queryApps() {
        new QueryAppsAsyncTask().execute();
    }

    public AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AppInfo appInfo = (AppInfo) parent.getItemAtPosition(position);
            ComponentName mComponent = new ComponentName(appInfo.getApppkg(), appInfo.getClassName());
            openApp(mComponent, view.getContext());
        }
    };

    public AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            AppInfo appInfo = (AppInfo) parent.getItemAtPosition(position);
            uninstallAppIntent(appInfo.getApppkg(),view.getContext());
            return true;
        }
    };

    /***
     * 启动APK
     * @param mComponent
     * @param context
     */
    private void openApp(ComponentName mComponent, Context context) {
        Intent intent = new Intent();
        intent.setComponent(mComponent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 卸载APK
     *
     * @param pkg
     */
    private void uninstallAppIntent(String pkg,Context context) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + pkg));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private class QueryAppsAsyncTask extends AsyncTask<Void, Void, List<AppInfo>> {

        public List<AppInfo> queryApps() {
            Log.i("AppViewModel", "queryApps: ");
            List<ResolveInfo> resolveInfoList = getResolveInfos();
            PackageManager pm = KswApplication.appContext.getPackageManager();
            List<AppInfo> appInfoList = new ArrayList<>();
            if (resolveInfoList != null || !resolveInfoList.isEmpty()) {
                for (ResolveInfo resolveInfo : resolveInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    if (TextUtils.equals(KswApplication.appContext.getPackageName(), packageName)) {
                        continue;
                    }
                    AppInfo appInfo = new AppInfo();
                    appInfo.setAppIcon(resolveInfo.loadIcon(pm));
                    appInfo.setAppLable(resolveInfo.loadLabel(pm).toString());
                    appInfo.setApppkg(packageName);
                    appInfo.setClassName(resolveInfo.activityInfo.name);
                    appInfoList.add(appInfo);
                }
            }
            return appInfoList;
        }

        public List<ResolveInfo> getResolveInfos() {
            PackageManager pm = KswApplication.appContext.getPackageManager();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            return pm.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        }

        @Override
        protected List<AppInfo> doInBackground(Void... voids) {
            return queryApps();
        }

        @Override
        protected void onPostExecute(List<AppInfo> listLiveData) {
            super.onPostExecute(listLiveData);
            listAdpater.set(new BaseListAdpater<AppInfo>(listLiveData, R.layout.app_item));
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
