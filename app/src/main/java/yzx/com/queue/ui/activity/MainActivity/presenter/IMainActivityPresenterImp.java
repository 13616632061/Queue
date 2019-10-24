package yzx.com.queue.ui.activity.MainActivity.presenter;

import java.util.List;

import yzx.com.queue.entity.OrderInfo;
import yzx.com.queue.entity.OrderType;
import yzx.com.queue.ui.adapter.MainOrderTypeAdapter;
import yzx.com.queue.ui.adapter.QueueOrderInfoAdapter;

/**
 * Created by Administrator on 2019/10/21.
 */

public interface IMainActivityPresenterImp {

    //显示主菜单
    void showMainMenuPopWindow();

    //获取订单分类
    void getOrderType(int position);

    List<OrderType> getTypeData();

    //初始化订单分类
    void initMainOrderTypeAdapter();

    //设置当前选中项
    void setCurSelect(int position);

    //取号
    void doTakeNumber(String num, String phone);

    //设置排队订单序号
    void setQueueOrderNumber();

    //初始化排队订单
    void initQueueOrderInfoAdapter();

    //排队订单数据
    List<OrderInfo> getOrderInfoData();

    //取号
    void doTakeNumber();

    //呼叫
    void doCallNumber(int position);

    //就餐
    void doEat(int position);

    //过号
    void doOverNumber(int position);

    //获取历史排队订单信息
    void getHistoryQueueOrderInfo();


}
