package yzx.com.queue.ui.activity.OrderType.model;

import android.content.Context;

import java.util.List;

import yzx.com.queue.entity.OrderType;

/**
 * Created by Administrator on 2019/10/21.
 */

public interface IOrderTypeAvtivityModelImp {

    //获取订单分类
    List<OrderType> getOrderType();
}
