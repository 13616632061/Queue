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
        typeList.add(orderType);
        typeList.addAll(GreenDaoHelp.getDaoSession().getOrderTypeDao().queryBuilder().list());
        return typeList;
    }

    /**
     * 取号
     *
     * @param orderInfo
     */
    @Override
    public void doTakeNumber(OrderInfo orderInfo) {
        GreenDaoHelp.getDaoSession().getOrderInfoDao().insert(orderInfo);
    }

    /**
     * 获取排队订单信息
     *
     * @param orderType
     */
    @Override
    public List<OrderInfo> getQueueOrderInfo(OrderType orderType) {
        List<OrderInfo> list;
        switch (orderType.getMaxNum()) {
            case -99:
                list = GreenDaoHelp.getDaoSession().getOrderInfoDao().queryBuilder().where(OrderInfoDao.Properties.Status.eq(0)).list();
                break;
            default:
                list = GreenDaoHelp.getDaoSession().getOrderInfoDao().queryBuilder().where(OrderInfoDao.Properties.Status.eq(0),OrderInfoDao.Properties.PersonNum.le(orderType.getMaxNum()), OrderInfoDao.Properties.PersonNum.ge(orderType.getMinNum())).list();
                break;
        }
        return list;
    }

    /**
     * 获取历史排队订单信息
     * @return
     */
    @Override
    public List<OrderInfo> getHistoryQueueOrderInfo() {
        return GreenDaoHelp.getDaoSession().getOrderInfoDao().queryBuilder().where(OrderInfoDao.Properties.Status.gt(0)).list();
    }

    /**
     * 更新排队订单信息信息
     * @param orderInfo
     */
    @Override
    public void updateQueueOrderInfo(OrderInfo orderInfo) {
        GreenDaoHelp.getDaoSession().getOrderInfoDao().update(orderInfo);
    }
}
