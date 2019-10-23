package yzx.com.queue.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import yzx.com.queue.entity.OrderInfo;

/**
 * Created by Administrator on 2019/10/23.
 */

public class QueueOrderInfoAdapter extends BaseQuickAdapter<OrderInfo, BaseViewHolder> {

    private Context mContext;

    public QueueOrderInfoAdapter(Context mContext, int layoutResId, @Nullable List<OrderInfo> data) {
        super(layoutResId, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderInfo item) {

    }
}
