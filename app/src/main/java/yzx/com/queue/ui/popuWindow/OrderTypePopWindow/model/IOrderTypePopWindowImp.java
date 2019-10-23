package yzx.com.queue.ui.popuWindow.OrderTypePopWindow.model;

import yzx.com.queue.entity.OrderType;

/**
 * Created by Administrator on 2019/10/22.
 */

public interface IOrderTypePopWindowImp {

    //添加分类
    void addType(OrderType orderType);

    //更新分类
    void updateType(OrderType orderType);
}
