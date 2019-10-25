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
    List<OrderInfo> getQueueOrderInfo(OrderType orderType);

    //获取历史排队订单信息
    List<OrderInfo> getHistoryQueueOrderInfo();

    //更新排队订单信息信息
    void updateQueueOrderInfo(OrderInfo orderInfo);

    //获取当前分类所有订单信息
    List<OrderInfo> getCurTypeALLQueueOrderInfo(OrderType orderType);

    //根据就餐人数查询
    List<OrderInfo> searchQueueInfo(String num,String phone);
}
