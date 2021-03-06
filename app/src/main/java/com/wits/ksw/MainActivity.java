package com.wits.ksw;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wits.ksw.databinding.ActivityMainBinding;
import com.wits.ksw.databinding.ActivityMainBmwBinding;
import com.wits.ksw.launcher.base.BaseThemeActivity;
import com.wits.ksw.launcher.view.CanFragment;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.view.MusicFragment;
import com.wits.ksw.launcher.view.SettingFragment;
import com.wits.ksw.launcher.utils.SharedPreUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseThemeActivity {
    private static final String TAG = MainActivity.class.getName();
    private static final int CAR_BMW_ID6 = 6;
    private static final int CAR_BMW_ID7 = 7;
    private ActivityMainBinding mainBinding;
    private ActivityMainBmwBinding bmwBinding;
    private LauncherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int UiIDType = SharedPreUtils.getInstance().getUiIDType();
        Log.i(TAG, "onCreate: UiIDType=" + UiIDType);
        super.onCreate(savedInstanceState);
        if (UiIDType == CAR_BMW_ID6) {
            Log.i(TAG, "onCreate: ID6 ");
            initMainView();
        } else if (UiIDType == CAR_BMW_ID7) {
            Log.i(TAG, "onCreate: ID7 ");
            initBmwView();
        }
        viewModel = ViewModelProviders.of(this).get(LauncherViewModel.class);
    }

    private void initBmwView() {
        bmwBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_bmw);
        bmwBinding.setMainActivity(this);

        bmwBinding.viewPage.setAdapter(new ViewPagerAdpater(getSupportFragmentManager()));
        bmwBinding.viewPage.setCurrentItem(0);
        bmwBinding.viewPage.setOffscreenPageLimit(0);
        bmwBinding.viewPage.setPageTransformer(false, new ViewPager.PageTransformer() {
            private static final float MIN_SCALE = 0.9f;

            @Override
            public void transformPage(@NonNull View view, float position) {

                /**
                 * 过滤那些 <-1 或 >1 的值，使它区于【-1，1】之间
                 */
                if (position < -1) {
                    position = -1;
                } else if (position > 1) {
                    position = 1;
                }
                /**
                 * 判断是前一页 1 + position ，右滑 pos -> -1 变 0
                 * 判断是后一页 1 - position ，左滑 pos -> 1 变 0
                 */
                float tempScale = position < 0 ? 1 + position : 1 - position; // [0,1]
                float scaleValue = MIN_SCALE + tempScale * 0.1f; // [0,1]
                Log.i(TAG, "transformPage: "+position+" tempScale="+tempScale+" scaleValue="+scaleValue);
                view.setScaleX(scaleValue);
                view.setScaleY(scaleValue);
            }
        });
    }

    private void initMainView() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setMainActivity(this);
        mainBinding.viewPage.setAdapter(new ViewPagerAdpater(getSupportFragmentManager()));
        mainBinding.viewPage.setCurrentItem(0);
    }

    public void switchUIStyle(int id) {
        Log.i(TAG, "switschUIStyle: " + id);
        viewModel.setUiIDType(id);
        viewModel.restart(this, getPackageName());
    }

    public void startApps() {
        Intent appsintent = new Intent();
        appsintent.setAction("com.wits.ksw.ACTION_APPS");
        startActivity(appsintent);
    }

    class ViewPagerAdpater extends FragmentPagerAdapter {
        private Fragment canFragment;
        private Fragment musicFragment;
        private Fragment setFragment;
        private List<Fragment> fragmentList;

        public ViewPagerAdpater(FragmentManager fragmentManager) {
            super(fragmentManager);
            fragmentList = new ArrayList<>();
            canFragment = new CanFragment();
            musicFragment = new MusicFragment();
            setFragment = new SettingFragment();
            fragmentList.add(canFragment);
            fragmentList.add(musicFragment);
            fragmentList.add(setFragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList == null ? null : fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
