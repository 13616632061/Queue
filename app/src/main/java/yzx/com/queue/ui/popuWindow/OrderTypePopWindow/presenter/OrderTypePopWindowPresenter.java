package yzx.com.queue.ui.popuWindow.OrderTypePopWindow.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

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

    public OrderTypePopWindowPresenter(OrderTypePopWindow mView) {
        this.mView = mView;
        mModel = new OrderTypePopWindowModel();
    }

    /**
     * 添加分类
     */
    @Override
    public void addType() {
        OrderType orderType=new OrderType();
        if (TextUtils.isEmpty(mView.getTypeName())){
            Toast.makeText(context,context.getResources().getString(R.string.empty_type_name),Toast.LENGTH_SHORT).show();
            return;
        }
        orderType.setTypeName(mView.getTypeName());
    }
}
