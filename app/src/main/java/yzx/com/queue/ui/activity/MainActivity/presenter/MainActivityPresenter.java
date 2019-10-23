package yzx.com.queue.ui.activity.MainActivity.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import yzx.com.queue.entity.OrderInfo;
import yzx.com.queue.entity.OrderType;
import yzx.com.queue.ui.activity.MainActivity.model.MainActivityModel;
import yzx.com.queue.ui.activity.MainActivity.view.MainActivity;
import yzx.com.queue.ui.adapter.MainOrderTypeAdapter;

/**
 * Created by Administrator on 2019/10/21.
 */

public class MainActivityPresenter implements IMainActivityPresenterImp {

    private MainActivity mView;
    private MainActivityModel mModel;

    private MainOrderTypeAdapter mOrderTypeAdapter;
    private List<OrderType> mTypeData = new ArrayList<>();

    public MainActivityPresenter(MainActivity mView) {
        this.mView = mView;
        mModel = new MainActivityModel();
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
        LogUtils.e("获取订单分类: " + mTypeData);
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
        mOrderTypeAdapter = mView.initMainOrderTypeAdapter();
    }

    /**
     * 设置当前选中项
     *
     * @param position
     */
    @Override
    public void setCurSelect(int position,int personNum) {
        for (int i = 0; i < mTypeData.size(); i++) {
            mTypeData.get(i).setIsSelect(false);
        }
        mTypeData.get(position).setIsSelect(true);
        mOrderTypeAdapter.notifyDataSetChanged();
        mModel.getQueueOrderInfo(personNum);
    }

    /**
     * 取号
     *
     * @param num
     * @param phone
     */
    @Override
    public void doTakeNumber(String num, String phone) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPersonNum(Integer.parseInt(num));
        orderInfo.setPhone(phone);
        orderInfo.setTime(TimeUtils.getNowMills() + "");
        mModel.doTakeNumber(orderInfo);
        mView.showMsg(1);
    }

    /**
     * 获取排队订单信息
     *
     * @param personNum
     */
    @Override
    public void getQueueOrderInfo(int personNum) {
        for (int i = 0; i < mTypeData.size(); i++) {
            if (personNum <= mTypeData.get(i).getMaxNum() && personNum >= mTypeData.get(i).getMinNum()) {
                setCurSelect(i,personNum);
                break;
            }
        }
    }
}
