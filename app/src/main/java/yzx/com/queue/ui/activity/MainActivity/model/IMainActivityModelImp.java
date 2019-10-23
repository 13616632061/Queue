package yzx.com.queue.ui.activity.MainActivity.model;

import android.content.Context;

import java.util.List;

import yzx.com.queue.entity.OrderInfo;
import yzx.com.queue.entity.OrderType;

/**
 * Created by Administrator on 2019/10/22.
 */

public interface IMainActivityModelImp {

    //获取订单分类
    List<OrderType> getOrderType(Context context);

    //取号
    void doTakeNumber(OrderInfo orderInfo);

    //获取排队订单信息
    void getQueueOrderInfo(int personNum);
}
