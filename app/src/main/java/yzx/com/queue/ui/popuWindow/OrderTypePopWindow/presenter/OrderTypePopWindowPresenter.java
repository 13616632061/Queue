package yzx.com.queue.ui.popuWindow.OrderTypePopWindow.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.yzx.lib.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import yzx.com.queue.R;
import yzx.com.queue.entity.OrderType;
import yzx.com.queue.ui.popuWindow.OrderTypePopWindow.model.OrderTypePopWindowModel;
import yzx.com.queue.ui.popuWindow.OrderTypePopWindow.view.OrderTypePopWindow;

/**
 * Created by Administrator on 2019/10/22.
 */

public class OrderTypePopWindowPresenter implements IOrderTypePopWindowPresenterImp {


    private OrderTypePopWindow mView;
    private OrderTypePopWindowModel mModel;
    private OrderType orderType;

    public OrderTypePopWindowPresenter(OrderTypePopWindow mView) {
        this.mView = mView;
        mModel = new OrderTypePopWindowModel();
    }

    /**
     * 设置标题
     *
     * @param orderType
     */
    @Override
    public void setTitle(Context context, OrderType orderType) {
        this.orderType = orderType;
        if (orderType != null) {
            mView.setTitle(context.getResources().getString(R.string.edit_type));
            mView.setTypeName(orderType.getTypeName());
            mView.setMinNum(orderType.getMinNum()+"");
            mView.setMaxNum(orderType.getMaxNum()+"");
        }
    }

    /**
     * 添加货编辑分类
     */
    @Override
    public void addOrEditType() {
        if (TextUtils.isEmpty(mView.getTypeName())) {
            mView.showMsg(1);
            return;
        }
        if (TextUtils.isEmpty(mView.getMinNum()) || TextUtils.isEmpty(mView.getMaxNum())) {
            mView.showMsg(2);
            return;
        }
        if (Integer.parseInt(mView.getMinNum()) > Integer.parseInt(mView.getMaxNum())) {
            mView.showMsg(3);
            return;
        }
        if (orderType == null) {//新增
            OrderType orderType = new OrderType();
            orderType.setTypeName(mView.getTypeName());
            orderType.setMinNum(Integer.parseInt(mView.getMinNum()));
            orderType.setMaxNum(Integer.parseInt(mView.getMaxNum()));
            mModel.addType(orderType);
        } else {//编辑
            orderType.setTypeName(mView.getTypeName());
            orderType.setMinNum(Integer.parseInt(mView.getMinNum()));
            orderType.setMaxNum(Integer.parseInt(mView.getMaxNum()));
            mModel.updateType(orderType);
        }

        EventBus.getDefault().post(new MessageEvent("updateTypeData", 200));
        mView.dismiss();

    }
}
