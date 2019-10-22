package yzx.com.queue.ui.activity.MainActivity.view;

import yzx.com.queue.ui.adapter.MainOrderTypeAdapter;

/**
 * Created by Administrator on 2019/10/21.
 */

public interface IMainActivityView {

    //显示主菜单
    void showMainMenuPopWindow();

    //初始化订单分类
    MainOrderTypeAdapter initMainOrderTypeAdapter();
}
