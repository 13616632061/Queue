package yzx.com.queue.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yzx.lib.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import yzx.com.queue.R;
import yzx.com.queue.entity.OrderInfo;

/**
 * Created by Administrator on 2019/10/23.
 */

public class QueueOrderInfoAdapter extends BaseQuickAdapter<OrderInfo, BaseViewHolder> {

    private Context mContext;
    private Timer timer;
    private List<TextView> datelist = null;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            for (TextView textView : datelist
                    ) {
                String time = (String) textView.getTag(R.id.tv_time);
                if (!TextUtils.isEmpty(time)) {
                    Long timeD = TimeUtils.getTimeSpan(TimeUtil.millis2String(TimeUtils.getNowMills()), time, TimeConstants.MIN);
                    textView.setText(timeD + "分钟");
                }
            }
        }
    };

    public QueueOrderInfoAdapter(Context mContext, int layoutResId, @Nullable List<OrderInfo> data) {
        super(layoutResId, data);
        this.mContext = mContext;
        setTimer();
    }

    /**
     * 1分钟自动刷新
     */
    private void setTimer() {
        timer = new Timer();
        datelist = new ArrayList<>();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }, 0, 60000);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderInfo item) {
        //序号
        helper.setText(R.id.tv_num, item.getNumber());
        //手机号
        helper.setText(R.id.tv_phone, item.getPhone());
        //是否有备注信息
        if (item.getIsHasMsg()) {
            helper.setGone(R.id.iv_msg, true);
        }
        //订单状态
        switch (item.getStatus()) {
            case 0://排队
                helper.setGone(R.id.tv_status, false);
                break;
            case 1://就餐
                helper.setGone(R.id.tv_status, true);
                helper.setText(R.id.tv_status, mContext.getResources().getString(R.string.eat));
                helper.setBackgroundRes(R.id.tv_status, R.drawable.bg_green_right_top_radius);
                break;
            case 2://过号
                helper.setGone(R.id.tv_status, true);
                helper.setText(R.id.tv_status, mContext.getResources().getString(R.string.over_number));
                helper.setBackgroundRes(R.id.tv_status, R.drawable.bg_yellow_right_top_radius);
                break;
        }
        //呼叫次数
        if (item.getCallNum() > 0) {
            helper.setGone(R.id.tv_call_num, true);
            helper.setText(R.id.tv_call_num, item.getCallNum() + "");
        } else {
            helper.setGone(R.id.tv_call_num, false);
        }
        //就餐人数
        helper.setText(R.id.tv_person_num, item.getPersonNum() + mContext.getResources().getString(R.string.person));
        //时间
        TextView tvTime = helper.getView(R.id.tv_time);
        if (!TextUtils.isEmpty(item.getTime())) {
            Long timeD = TimeUtils.getTimeSpan(TimeUtil.millis2String(TimeUtils.getNowMills()), item.getTime(), TimeConstants.MIN);
            tvTime.setText(timeD + "分钟");

            tvTime.setTag(R.id.tv_time, item.getTime());
            datelist.add(tvTime);
        }

        helper.addOnClickListener(R.id.layout_call_number);
        helper.addOnClickListener(R.id.layout_eat);
        helper.addOnClickListener(R.id.layout_over_number);
        helper.addOnClickListener(R.id.iv_more);
    }
}
