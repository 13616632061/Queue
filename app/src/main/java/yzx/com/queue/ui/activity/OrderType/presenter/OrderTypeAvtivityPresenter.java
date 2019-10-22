package yzx.com.queue.ui.activity.OrderType.presenter;

import java.util.ArrayList;
import java.util.List;

import yzx.com.queue.entity.OrderType;
import yzx.com.queue.ui.activity.OrderType.model.OrderTypeAvtivityModel;
import yzx.com.queue.ui.activity.OrderType.view.OrderTypeAvtivity;
import yzx.com.queue.ui.adapter.OrderTypeAdapter;

/**
 * Created by Administrator on 2019/10/21.
 */

public class OrderTypeAvtivityPresenter implements IOrderTypeAvtivityPresenterImp {

    private OrderTypeAvtivity mView;
    private OrderTypeAvtivityModel mModel;
    private List<OrderType> mData=new ArrayList<>();
    private OrderTypeAdapter mAdapter;


    public OrderTypeAvtivityPresenter(OrderTypeAvtivity mView) {
        this.mView = mView;
        mModel = new OrderTypeAvtivityModel();
    }

    /**
     * 初始化适配器
     */
    @Override
    public void initOrderTypeAdapter() {

    }

    /**
     * 获取订单分类
     */
    @Override

    public void getOrderType() {
        mData.clear();
        mData.addAll(mModel.getOrderType());
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 订单分类
     * @return
     */
    @Override
    public List<OrderType> getData() {
        return mData;
    }

    /**
     * 显示分类操作窗口
     */
    @Override
    public void showOrderTypePopWindow() {
        mView.showOrderTypePopWindow();
    }

    /**
     * 新增分类
     */
    @Override
    public void addOrderType() {
        mView.addOrderType();
    }

    /**
     * 删除分类
     */
    @Override
    public void deteleOrderType() {

    }
}
