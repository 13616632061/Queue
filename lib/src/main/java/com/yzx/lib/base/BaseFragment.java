package com.yzx.lib.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yzx.lib.weight.Loading;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by lyf on 2019/5/5.
 */

public abstract class BaseFragment extends LazyLoadFragment {

    private View rootView;
    public Activity mActivity;
    private Loading mloading;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(setContentViewId(), container, false);

            ButterKnife.inject(this, rootView);

            initView(rootView);
            initLisntener();
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }


        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    /**
     * 当第一次可见的时候，加载数据
     */
    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        loadData();
    }

    /**
     * 得到当前界面的布局文件id(由子类实现)
     *
     * @return
     */
    protected abstract int setContentViewId();

    /**
     * 数据加载
     */
    protected abstract void loadData();

    /**
     * 初始化一些view
     */
    public void initView(View rootView) {
    }

    /**
     * 初始化监听
     */
    protected void initLisntener() {
    }


    public void showToast(String msg) {
        Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 加载动画显示
     */
    public void showLoading() {
        if (mloading == null && !getActivity().isFinishing()) {
            mloading = new Loading(getActivity());
            mloading.show();
        }
    }
    /**
     * 取消加载动画
     */
    public void dismissLoading() {
        if (mloading != null && mloading.isShowing()) {
            mloading.dismiss();
            mloading = null;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;
        dismissLoading();
        ButterKnife.reset(this);
    }
}
