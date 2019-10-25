package yzx.com.queue.ui.activity.MainActivity.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.liangmayong.text2speech.OnText2SpeechListener;
import com.liangmayong.text2speech.Text2Speech;
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
        setEmptyView();
        mQueueOrderInfoAdapter.notifyDataSetChanged();
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
        int personNum = Integer.parseInt(num);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPersonNum(personNum);
        orderInfo.setPhone(phone);
        orderInfo.setTime(TimeUtil.millis2String(TimeUtils.getNowMills()));
        OrderType orderType = getQueueOrderType(personNum);
        if (orderType != null) {
            String orderNumer = orderType.getNumber() + (mModel.getCurTypeALLQueueOrderInfo(orderType).size() + 1);
            orderInfo.setNumber(orderNumer);
        }
        mModel.doTakeNumber(orderInfo);
        mView.showMsg(1);
        setCurSelect(curSelectTypePosition);
    }

    /**
     * 设置排队订单属于哪个分类
     */
    @Override
    public OrderType getQueueOrderType(int personNum) {
        OrderType orderType = null;
        for (int i = 0; i < mTypeData.size(); i++) {
            if (personNum <= mTypeData.get(i).getMaxNum() && personNum >= mTypeData.get(i).getMinNum()) {
                orderType = mTypeData.get(i);
                break;
            }
        }
        return orderType;
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
        if (mOrderData.get(position).getStatus() == 0) {
            String number = mOrderData.get(position).getNumber();
            final String speechText = number + "的客户请您就餐";
            LogUtils.e("呼叫: " + speechText);
            Text2Speech.speech(mView, speechText, false);
            int callNum = mOrderData.get(position).getCallNum();
            mOrderData.get(position).setCallNum(callNum + 1);
            mQueueOrderInfoAdapter.notifyDataSetChanged();

            mModel.updateQueueOrderInfo(mOrderData.get(position));
        }
    }

    /**
     * 就餐
     *
     * @param position
     */
    @Override
    public void doEat(int position) {
        if (mOrderData.get(position).getStatus() == 0) {
            mOrderData.get(position).setStatus(1);
            mQueueOrderInfoAdapter.notifyDataSetChanged();
            mModel.updateQueueOrderInfo(mOrderData.get(position));
            mOrderData.remove(position);
        }
    }

    /**
     * 过号
     *
     * @param position
     */
    @Override
    public void doOverNumber(int position) {
        if (mOrderData.get(position).getStatus() == 0) {
            mOrderData.get(position).setStatus(2);
            mQueueOrderInfoAdapter.notifyDataSetChanged();
            mModel.updateQueueOrderInfo(mOrderData.get(position));
            mOrderData.remove(position);
        }
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
        setEmptyView();
        mQueueOrderInfoAdapter.notifyDataSetChanged();
        mView.setHistoryViewColor(1);
    }

    /**
     * 查询排队信息
     *
     * @param num
     * @param phone
     */
    @Override
    public void searchQueueInfo(String num, String phone) {
        if (!TextUtils.isEmpty(num) || !TextUtils.isEmpty(phone)) {
            mOrderData.clear();
            mOrderData.addAll(mModel.searchQueueInfo(num, phone));
            setEmptyView();
            mQueueOrderInfoAdapter.notifyDataSetChanged();
            mView.setHistoryViewColor(0);
            getOrderType(0);
        }
    }

    /**
     * 设置空视图
     */
    @Override
    public void setEmptyView() {
        if (mOrderData.size() <= 0) {
            mQueueOrderInfoAdapter.setEmptyView(mView.setEmptyView());
            mQueueOrderInfoAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 播放
     */
    @Override
    public void play() {
        final String playContent = SPUtils.getInstance().getString("playContent");
        if (TextUtils.isEmpty(playContent)) {
            mView.showMsg(3);
            return;
        }
        LogUtils.e("playContent: " + playContent);
        Text2Speech.speech(mView, playContent, false);
        Text2Speech.addText2SpeechListener(new OnText2SpeechListener() {
            @Override
            public void onCompletion() {
                if (mView.playStatus() && !Text2Speech.isSpeeching()) {
                    Text2Speech.speech(mView, playContent, false);
                }
            }

            @Override
            public void onPrepared() {

            }

            @Override
            public void onError(Exception e, String s) {

            }

            @Override
            public void onStart() {
            }

            @Override
            public void onLoadProgress(int i, int i1) {

            }

            @Override
            public void onPlayProgress(int i, int i1) {

            }
        });
    }

    /**
     * 暂停
     */
    @Override
    public void onPause() {
        if (Text2Speech.isSpeeching()) {
            Text2Speech.pause(mView);
        }
    }
}
