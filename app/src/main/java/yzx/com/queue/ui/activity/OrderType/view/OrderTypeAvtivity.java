package yzx.com.queue.ui.activity.OrderType.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yzx.lib.base.BaseActivity;

import butterknife.InjectView;
import butterknife.OnClick;
import yzx.com.queue.R;
import yzx.com.queue.constant.RouteMap;
import yzx.com.queue.ui.activity.OrderType.presenter.OrderTypeAvtivityPresenter;
import yzx.com.queue.ui.adapter.OrderTypeAdapter;
import yzx.com.queue.ui.popuWindow.OrderTypePopWindow.view.OrderTypePopWindow;

@Route(path = RouteMap.ROUTE_ORDER_TYPE_ACTIVITY)
public class OrderTypeAvtivity extends BaseActivity implements IOrderTypeAvtivityView {


    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.btn_add)
    Button btnAdd;
    @InjectView(R.id.btn_delete)
    Button btnDelete;
    @InjectView(R.id.layout_root)
    RelativeLayout layoutRoot;

    private OrderTypeAvtivityPresenter mPresenter;

    @Override
    public int getContentView() {
        return R.layout.activity_order_type_avtivity;
    }

    @Override
    protected void initView() {
        inintTitle(getResources().getString(R.string.type_manage));
        mPresenter = new OrderTypeAvtivityPresenter(this);
        mPresenter.initOrderTypeAdapter();

    }

    @OnClick({R.id.btn_add, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                mPresenter.addOrderType();
                break;
            case R.id.btn_delete:
                break;
        }
    }

    /**
     * 初始化适配器
     *
     * @return
     */
    @Override
    public OrderTypeAdapter initOrderTypeAdapter() {
        OrderTypeAdapter adapter = new OrderTypeAdapter(this, R.layout.item_order_type, mPresenter.getData());
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        return adapter;
    }

    /**
     * 新增分类
     */
    @Override
    public void addOrderType() {
        mPresenter.showOrderTypePopWindow();
    }

    /**
     * 删除分类
     */
    @Override
    public void deteleOrderType() {

    }

    /**
     * 显示分类操作窗口
     */
    @Override
    public void showOrderTypePopWindow() {
        OrderTypePopWindow typePopWindow = new OrderTypePopWindow(this);
        typePopWindow.showAtLocation(layoutRoot, Gravity.NO_GRAVITY, 0, 0);
    }


}
