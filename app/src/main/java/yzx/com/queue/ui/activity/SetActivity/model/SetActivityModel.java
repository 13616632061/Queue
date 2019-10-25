package yzx.com.queue.ui.activity.SetActivity.model;

import yzx.com.queue.greendao.GreenDaoHelp;

/**
 * Created by Administrator on 2019/10/25.
 */

public class SetActivityModel implements ISetActivityModelImp {
    /**
     * 清空排队信息
     */
    @Override
    public void clearQueueOrderInfo() {
        GreenDaoHelp.getDaoSession().getOrderInfoDao().deleteAll();
    }
}
