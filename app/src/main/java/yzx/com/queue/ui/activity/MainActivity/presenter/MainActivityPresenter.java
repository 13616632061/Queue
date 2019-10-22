package yzx.com.queue.ui.activity.MainActivity.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import yzx.com.queue.entity.OrderType;
import yzx.com.queue.ui.activity.MainActivity.model.MainActivityModel;
import yzx.com.queue.ui.activity.MainActivity.view.MainActivity;
import yzx.com.queue.ui.adapter.MainOrderTypeAdapter;

/**
 * Created by Administrator on 2019/10/21.
 */

public class MainActivityPresenter implements IMainActivityPresenterImp{

    private MainActivity mView;
    private MainActivityModel mModel;

    private MainOrderTypeAdapter mOrderTypeAdapter;
    private List<OrderType> mTypeData=new ArrayList<>();

    public MainActivityPresenter(MainActivity mView) {
        this.mView = mView;
        mModel=new MainActivityModel();
    }

    /**
     * 显示主菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void showMainMenuPopWindow() {
        mView.showMainMenuPopWindow();
    }

    /**
     * 获取订单分类
     */
    @Override
    public void getOrderType() {
        mTypeData.clear();
        mTypeData.addAll(mModel.getOrderType(mView));
        mOrderTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public List<OrderType> getTypeData() {
        return mTypeData;
    }

    /**
     * 初始化订单分类
     */
    @Override
    public void initMainOrderTypeAdapter() {
        mOrderTypeAdapter= mView.initMainOrderTypeAdapter();
    }
}
