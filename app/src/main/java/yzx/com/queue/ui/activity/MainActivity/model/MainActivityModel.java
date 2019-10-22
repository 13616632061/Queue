package yzx.com.queue.ui.activity.MainActivity.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import yzx.com.queue.R;
import yzx.com.queue.entity.OrderType;
import yzx.com.queue.greendao.GreenDaoHelp;

/**
 * Created by Administrator on 2019/10/22.
 */

public class MainActivityModel implements IMainActivityModelImp {

    /**
     * 获取订单分类
     *
     * @return
     */
    @Override
    public List<OrderType> getOrderType(Context context) {
        List<OrderType> typeList = new ArrayList<>();
        OrderType orderType = new OrderType();
        orderType.setTypeName(context.getResources().getString(R.string.all));
        orderType.setMaxNum(-99);
        orderType.setIsSelect(true);
        typeList.add(orderType);
        typeList.addAll(GreenDaoHelp.getDaoSession().getOrderTypeDao().queryBuilder().list());
        return typeList;
    }
}
