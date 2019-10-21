package yzx.com.queue.ui.activity.OrderType.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yzx.lib.base.BaseActivity;

import yzx.com.queue.R;
import yzx.com.queue.constant.RouteMap;

@Route(path = RouteMap.ROUTE_ORDER_TYPE_ACTIVITY)
public class OrderTypeAvtivity extends BaseActivity {


    @Override
    public int getContentView() {
        return R.layout.activity_order_type_avtivity;
    }

    @Override
    protected void initView() {
        inintTitle(getResources().getString(R.string.type_manage));
    }
}
