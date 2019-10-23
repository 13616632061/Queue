package yzx.com.queue.ui.popuWindow.OrderTypePopWindow.model;

import yzx.com.queue.entity.OrderType;
import yzx.com.queue.greendao.GreenDaoHelp;

/**
 * Created by Administrator on 2019/10/22.
 */

public class OrderTypePopWindowModel implements IOrderTypePopWindowImp {
    /**
     * 添加分类
     * @param orderType
     */
    @Override
    public void addType(OrderType orderType) {
        GreenDaoHelp.getDaoSession().getOrderTypeDao().insert(orderType);
    }

    /**
     * 更新分类
     * @param orderType
     */
    @Override
    public void updateType(OrderType orderType) {
        GreenDaoHelp.getDaoSession().getOrderTypeDao().update(orderType);
    }
}
