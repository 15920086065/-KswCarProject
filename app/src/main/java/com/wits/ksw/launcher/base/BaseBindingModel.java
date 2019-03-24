package com.wits.ksw.launcher.base;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * 作者：yt
 * 时间：2019/3/23
 * 邮箱：Y349194924@163.com
 * <p>
 * 备注：
 */
public class BaseBindingModel {

    /**
     * 显示图片
     *
     * @param imageView
     * @param resid
     */
    @BindingAdapter({"srcImage"})
    public static void srcImage(ImageView imageView, Drawable resid) {
        imageView.setImageDrawable(resid);
    }

    /***
     *
     * @param gridView
     * @param adapter
     */
    @BindingAdapter({"setAdpater"})
    public static void setAdpater(GridView gridView,BaseListAdpater adapter) {
        gridView.setAdapter(adapter);
    }

}
