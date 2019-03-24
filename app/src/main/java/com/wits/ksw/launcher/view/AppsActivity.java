package com.wits.ksw.launcher.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AppsActiviyImpl;
import com.wits.ksw.launcher.base.BaseThemeActivity;
import com.wits.ksw.launcher.model.AppViewModel;


/**
 * 作者：yt
 * 时间：2019/3/23
 * 邮箱：Y349194924@163.com
 * <p>
 * 备注：
 */
public final class AppsActivity extends BaseThemeActivity {
    private AppViewModel viewModel;
    private AppsActiviyImpl binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_apps);
        binding.setAppViewModel(viewModel);
        viewModel.queryApps();
    }

}
