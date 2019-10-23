package yzx.com.queue.ui.popuWindow.OrderTypePopWindow.presenter;

import android.content.Context;

import yzx.com.queue.entity.OrderType;

/**
 * Created by Administrator on 2019/10/22.
 */

public interface IOrderTypePopWindowPresenterImp {


    //设置标题
    void setTitle(Context context, OrderType orderType);

    //添加分类
    void addOrEditType();
}
