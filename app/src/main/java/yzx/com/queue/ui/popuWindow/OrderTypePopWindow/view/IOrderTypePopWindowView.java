package yzx.com.queue.ui.popuWindow.OrderTypePopWindow.view;

/**
 * Created by Administrator on 2019/10/22.
 */

public interface IOrderTypePopWindowView {

    //设置分类名称
    void setTypeName(String name);

    //获取分类名称
    String getTypeName();

    //设置标题
    void setTitle(String title);

    //设置最少人数
    void setMinNum(String minNum);

    //获取最少人数
    String getMinNum();

    //设置最大人数
    void setMaxNum(String num);

    //获取最大人数
    String getMaxNum();

    //显示提示信息
    void showMsg(int type);

}
