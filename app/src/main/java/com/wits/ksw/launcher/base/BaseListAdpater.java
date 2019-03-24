package com.wits.ksw.launcher.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wits.ksw.KswApplication;

import java.util.List;

import static com.wits.ksw.BR.listItem;


/**
 * 作者：yt
 * 时间：2018/12/6
 * 邮箱：witsyt@163.com / Y349194924@163.comn
 * <p>
 * 备注：
 */
public class BaseListAdpater<T> extends BaseAdapter {

    private List<T> mlist;
    private LayoutInflater mInflater;
    private int resId;

    public BaseListAdpater(List<T> mlist, int resId) {
        this.mlist = mlist;
        this.resId = resId;
        mInflater = LayoutInflater.from(KswApplication.appContext);
    }

    public void setData(List<T> mlist) {
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return (mlist == null || mlist.isEmpty()) ? 0 : mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return (mlist == null || mlist.isEmpty()) ? null : mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewDataBinding binding = null;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(mInflater, resId, viewGroup, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ViewDataBinding) convertView.getTag();
        }
        //listItem对应XML中variable
        binding.setVariable(listItem, mlist.get(position));
        return binding.getRoot();
    }

}
