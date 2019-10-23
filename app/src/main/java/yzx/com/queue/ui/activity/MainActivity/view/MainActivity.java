package yzx.com.queue.ui.activity.MainActivity.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yzx.lib.base.BaseActivity;
import com.yzx.lib.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yzx.com.queue.R;
import yzx.com.queue.constant.RouteMap;
import yzx.com.queue.greendao.GreenDaoHelp;
import yzx.com.queue.ui.activity.MainActivity.presenter.MainActivityPresenter;
import yzx.com.queue.ui.adapter.MainOrderTypeAdapter;
import yzx.com.queue.ui.popuWindow.MainMenuPopWindow;
import yzx.com.queue.view.TakeNumberView;

@Route(path = RouteMap.ROUTE_MAIN_ACTIVITY)
public class MainActivity extends BaseActivity implements IMainActivityView {


    @InjectView(R.id.iv_menu)
    ImageView ivMenu;
    @InjectView(R.id.tv_history)
    TextView tvHistory;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.take_number)
    TakeNumberView takeNumber;
    @InjectView(R.id.layout_title)
    RelativeLayout layoutTitle;
    @InjectView(R.id.tab_list)
    RecyclerView tabList;

    private MainActivityPresenter mPresenter;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        GreenDaoHelp.getInstance(this);
        EventBus.getDefault().register(this);
        mPresenter = new MainActivityPresenter(this);
        mPresenter.initMainOrderTypeAdapter();
        mPresenter.getOrderType();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.iv_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_menu://菜单
                mPresenter.showMainMenuPopWindow();
                break;
        }
    }

    /**
     * 显示主菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void showMainMenuPopWindow() {
        MainMenuPopWindow menuPopWindow = MainMenuPopWindow.getInstance(this);
        menuPopWindow.showAsDropDown(layoutTitle, 0, 0, Gravity.NO_GRAVITY);
    }

    /**
     * 初始化订单分类
     */
    @Override
    public MainOrderTypeAdapter initMainOrderTypeAdapter() {
        MainOrderTypeAdapter adapter = new MainOrderTypeAdapter(this, R.layout.item_main_order_type, mPresenter.getTypeData());
        tabList.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tabList.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.setCurSelect(position);
            }
        });
        return adapter;
    }

    /**
     * 取号
     */
    @Override
    public void doTakeNumber() {
        takeNumber.setTakeNumber(new TakeNumberView.takeNumber() {
            @Override
            public void takeNumber(String num, String phone) {

            }
        });
    }
    /**
     * 显示提示信息
     */
    @Override
    public void showMsg(int type) {
        switch (type) {
            case 1://取号成功
                Toast.makeText(this, getResources().getString(R.string.take_num_success), Toast.LENGTH_SHORT).show();
                break;
            case 2://请选择要删除的分类
                Toast.makeText(this,getResources().getString(R.string.please_select_delete_type), Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvenBus(MessageEvent msg) {
        if (msg.getKey().equals("updateTypeData")) {
            mPresenter.getOrderType();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
