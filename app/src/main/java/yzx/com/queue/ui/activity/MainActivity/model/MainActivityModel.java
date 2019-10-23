package yzx.com.queue.ui.activity.MainActivity.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import yzx.com.queue.R;
import yzx.com.queue.entity.OrderInfo;
import yzx.com.queue.entity.OrderType;
import yzx.com.queue.greendao.GreenDaoHelp;
import yzx.com.queue.greendao.OrderInfoDao;

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

    /**
     * 取号
     * @param orderInfo
     */
    @Override
    public void doTakeNumber(OrderInfo orderInfo) {
        GreenDaoHelp.getDaoSession().getOrderInfoDao().insert(orderInfo);
    }

    /**
     * 获取排队订单信息
     * @param personNum
     */
    @Override
    public void getQueueOrderInfo(int personNum) {
        GreenDaoHelp.getDaoSession().getOrderInfoDao().queryBuilder().where(OrderInfoDao.Properties.PersonNum.eq(personNum));
    }
}
