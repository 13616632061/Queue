package yzx.com.queue.ui.activity.OrderType.view;

import yzx.com.queue.entity.OrderType;
import yzx.com.queue.ui.adapter.OrderTypeAdapter;

/**
 * Created by Administrator on 2019/10/21.
 */

public interface IOrderTypeAvtivityView {


    //初始化适配器
    OrderTypeAdapter initOrderTypeAdapter();

    //新增分类
    void addOrderType();

    //删除分类
    void deteleOrderType();

    //显示分类操作窗口
    void showOrderTypePopWindow(OrderType orderType);

    //显示提示信息
    void showMsg(int type);

}
