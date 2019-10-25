package yzx.com.queue.ui.activity.OrderType.view;

import android.support.annotation.MainThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yzx.lib.base.BaseActivity;
import com.yzx.lib.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import butterknife.InjectView;
import butterknife.OnClick;
import yzx.com.queue.R;
import yzx.com.queue.constant.RouteMap;
import yzx.com.queue.entity.OrderType;
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
        EventBus.getDefault().register(this);
        mPresenter = new OrderTypeAvtivityPresenter(this);
        mPresenter.initOrderTypeAdapter();
        mPresenter.getOrderType();

    }

    @OnClick({R.id.btn_add, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add://新增
                mPresenter.addOrderType();
                break;
            case R.id.btn_delete://删除
                mPresenter.deteleOrderType();
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
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.layout_item:
                        mPresenter.setItemSelectStatus(position);
                        break;
                    case R.id.tv_edit:
                        mPresenter.showOrderTypePopWindow(mPresenter.getData().get(position));
                        break;
                }
            }
        });
        return adapter;
    }

    /**
     * 新增分类
     */
    @Override
    public void addOrderType() {
        mPresenter.showOrderTypePopWindow(null);
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
    public void showOrderTypePopWindow(OrderType orderType) {
        OrderTypePopWindow typePopWindow = new OrderTypePopWindow(this,orderType);
        typePopWindow.showAtLocation(layoutRoot, Gravity.NO_GRAVITY, 0, 0);
    }

    /**
     * 显示提示信息
     */
    @Override
    public void showMsg(int type) {
        switch (type) {
            case 1://删除成功
                Toast.makeText(this, getResources().getString(R.string.delete_success), Toast.LENGTH_SHORT).show();
                break;
            case 2://请选择要删除的分类
                Toast.makeText(this,getResources().getString(R.string.please_select_delete_type), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvenBus(MessageEvent msg){
        if (msg.getKey().equals("updateTypeData")){
            mPresenter.getOrderType();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
