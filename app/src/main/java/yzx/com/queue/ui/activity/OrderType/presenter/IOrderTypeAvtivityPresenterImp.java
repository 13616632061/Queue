package yzx.com.queue.ui.activity.OrderType.presenter;

import java.util.List;

import yzx.com.queue.entity.OrderType;
import yzx.com.queue.ui.adapter.OrderTypeAdapter;

/**
 * Created by Administrator on 2019/10/21.
 */

public interface IOrderTypeAvtivityPresenterImp {

    //初始化适配器
    void initOrderTypeAdapter();

    //获取订单分类
    void getOrderType();

    List<OrderType> getData();

    //显示分类操作窗口
    void showOrderTypePopWindow();

    //新增分类
    void addOrderType();

    //删除分类
    void deteleOrderType();
}
