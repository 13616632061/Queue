package yzx.com.queue.ui.activity.MainActivity.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.yzx.lib.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import yzx.com.queue.constant.Constant;
import yzx.com.queue.entity.OrderInfo;
import yzx.com.queue.entity.OrderType;
import yzx.com.queue.ui.activity.MainActivity.model.MainActivityModel;
import yzx.com.queue.ui.activity.MainActivity.view.MainActivity;
import yzx.com.queue.ui.adapter.MainOrderTypeAdapter;
import yzx.com.queue.ui.adapter.QueueOrderInfoAdapter;

/**
 * Created by Administrator on 2019/10/21.
 */

public class MainActivityPresenter implements IMainActivityPresenterImp {

    private MainActivity mView;
    private MainActivityModel mModel;

    private MainOrderTypeAdapter mOrderTypeAdapter;
    private List<OrderType> mTypeData = new ArrayList<>();
    private QueueOrderInfoAdapter mQueueOrderInfoAdapter;
    private List<OrderInfo> mOrderData = new ArrayList<>();
    private int curSelectTypePosition = 0;//当前选中的分类

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
    public void getOrderType(int position) {
        mTypeData.clear();
        mTypeData.addAll(mModel.getOrderType(mView));
        for (int i = 0; i < mTypeData.size(); i++) {
            if (i > 0 && i < Constant.QUEUE_ORDER_NUM.length) {
                mTypeData.get(i).setNumber(Constant.QUEUE_ORDER_NUM[i]);
            }
            int orderNum = mModel.getQueueOrderInfo(mTypeData.get(i)).size();
            mTypeData.get(i).setOrderNum(orderNum);
        }
        mTypeData.get(position).setIsSelect(true);
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
    public void setCurSelect(int position) {
        curSelectTypePosition = position;
        for (int i = 0; i < mTypeData.size(); i++) {
            mTypeData.get(i).setIsSelect(false);
        }
        mTypeData.get(position).setIsSelect(true);
        mOrderTypeAdapter.notifyDataSetChanged();
        mOrderData.clear();
        mOrderData.addAll(mModel.getQueueOrderInfo(mTypeData.get(position)));
        setQueueOrderNumber();
        getOrderType(position);
        mView.setHistoryViewColor(0);
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
        orderInfo.setTime(TimeUtil.millis2String(TimeUtils.getNowMills()));
        mModel.doTakeNumber(orderInfo);
        mView.showMsg(1);
        setCurSelect(curSelectTypePosition);
    }

    /**
     * 设置排队订单序号
     */
    @Override
    public void setQueueOrderNumber() {
        for (int k = 0; k < mOrderData.size(); k++) {
            int personNum = mOrderData.get(k).getPersonNum();
            for (int i = 0; i < mTypeData.size(); i++) {
                if (personNum <= mTypeData.get(i).getMaxNum() && personNum >= mTypeData.get(i).getMinNum()) {
                    mOrderData.get(k).setNumber(mTypeData.get(i).getNumber() + (k + 1));
                }
            }
        }
        mQueueOrderInfoAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化排队订单
     */
    @Override
    public void initQueueOrderInfoAdapter() {
        mQueueOrderInfoAdapter = mView.initQueueOrderInfoAdapter();
    }

    /**
     * 排队订单数据
     *
     * @return
     */
    @Override
    public List<OrderInfo> getOrderInfoData() {
        return mOrderData;
    }

    /**
     * 取号
     */
    @Override
    public void doTakeNumber() {
        mView.doTakeNumber();
    }

    /**
     * 呼叫
     *
     * @param position
     */
    @Override
    public void doCallNumber(int position) {
        int callNum = mOrderData.get(position).getCallNum();
        mOrderData.get(position).setCallNum(callNum + 1);
        mQueueOrderInfoAdapter.notifyDataSetChanged();

        mModel.updateQueueOrderInfo(mOrderData.get(position));
    }

    /**
     * 就餐
     *
     * @param position
     */
    @Override
    public void doEat(int position) {
        mOrderData.get(position).setStatus(1);
        mQueueOrderInfoAdapter.notifyDataSetChanged();
        mModel.updateQueueOrderInfo(mOrderData.get(position));
        mOrderData.remove(position);
    }

    /**
     * 过号
     *
     * @param position
     */
    @Override
    public void doOverNumber(int position) {
        mOrderData.get(position).setStatus(2);
        mQueueOrderInfoAdapter.notifyDataSetChanged();
        mModel.updateQueueOrderInfo(mOrderData.get(position));
        mOrderData.remove(position);
    }

    /**
     * 获取历史排队订单信息
     */
    @Override
    public void getHistoryQueueOrderInfo() {
        for (int i = 0; i < mTypeData.size(); i++) {
            mTypeData.get(i).setIsSelect(false);
        }
        mOrderTypeAdapter.notifyDataSetChanged();
        mOrderData.clear();
        mOrderData.addAll(mModel.getHistoryQueueOrderInfo());
        setQueueOrderNumber();
        mView.setHistoryViewColor(1);
    }
}
