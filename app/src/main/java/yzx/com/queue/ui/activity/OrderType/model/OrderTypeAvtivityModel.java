package yzx.com.queue.ui.activity.OrderType.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import yzx.com.queue.R;
import yzx.com.queue.entity.OrderType;
import yzx.com.queue.greendao.GreenDaoHelp;

/**
 * Created by Administrator on 2019/10/21.
 */

public class OrderTypeAvtivityModel implements IOrderTypeAvtivityModelImp{

    /**
     * 获取订单分类
     * @return
     */
    @Override
    public List<OrderType> getOrderType() {
        List<OrderType> typeList = new ArrayList<>();
        typeList.addAll(GreenDaoHelp.getDaoSession().getOrderTypeDao().queryBuilder().list());
        return typeList;
    }
}
