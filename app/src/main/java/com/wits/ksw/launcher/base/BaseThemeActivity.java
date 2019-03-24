package com.wits.ksw.launcher.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.SharedPreUtils;

/**
 * 作者：yt
 * 时间：2019/3/24
 * 邮箱：Y349194924@163.com
 * <p>
 * 备注：
 */
public class BaseThemeActivity extends AppCompatActivity {
    private static final int CAR_BMW_ID6 = 6;
    private static final int CAR_BMW_ID7 = 7;
    protected int UiIDType = SharedPreUtils.getInstance().getUiIDType();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("BaseThemeActivity", "onCreate: UiIDType=" + UiIDType);
        if (UiIDType == CAR_BMW_ID6) {
            Log.i("BaseThemeActivity", "onCreate: ID6 ");
            setTheme(R.style.DayAppTheme);
        } else if (UiIDType == CAR_BMW_ID7) {
            Log.i("BaseThemeActivity", "onCreate: ID7 ");
            setTheme(R.style.NihtAppTheme);
        }
        super.onCreate(savedInstanceState);
    }
}
