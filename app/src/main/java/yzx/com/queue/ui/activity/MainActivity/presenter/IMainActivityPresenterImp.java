package yzx.com.queue.ui.activity.MainActivity.presenter;

import java.util.List;

import yzx.com.queue.entity.OrderType;
import yzx.com.queue.ui.adapter.MainOrderTypeAdapter;

/**
 * Created by Administrator on 2019/10/21.
 */

public interface IMainActivityPresenterImp {

    //显示主菜单
    void showMainMenuPopWindow();

    //获取订单分类
    void getOrderType();

    List<OrderType> getTypeData();

    //初始化订单分类
    void initMainOrderTypeAdapter();


}
