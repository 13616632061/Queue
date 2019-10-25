package yzx.com.queue.ui.activity.MainActivity.view;

import android.view.View;

import yzx.com.queue.ui.adapter.MainOrderTypeAdapter;
import yzx.com.queue.ui.adapter.QueueOrderInfoAdapter;

/**
 * Created by Administrator on 2019/10/21.
 */

public interface IMainActivityView {

    //设置历史视图颜色
    void setHistoryViewColor(int type);

    //显示主菜单
    void showMainMenuPopWindow();

    //初始化订单分类
    MainOrderTypeAdapter initMainOrderTypeAdapter();

    //取号
    void doTakeNumber();

    //显示提示信息
    void showMsg(int type);

    //初始化排队订单
    QueueOrderInfoAdapter initQueueOrderInfoAdapter();

    //播放状态
    boolean playStatus();

    //设置空视图
    View setEmptyView();
}
