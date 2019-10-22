package yzx.com.queue.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import yzx.com.queue.R;
import yzx.com.queue.entity.OrderType;

/**
 * Created by Administrator on 2019/10/22.
 */

public class MainOrderTypeAdapter extends BaseQuickAdapter<OrderType, BaseViewHolder> {

    private Context mContext;

    public MainOrderTypeAdapter(Context mContext, int layoutResId, @Nullable List<OrderType> data) {
        super(layoutResId, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderType item) {
        //分类名称
        helper.setText(R.id.tv_name, item.getTypeName());
        //分类订单数量
        if (item.getOrderNum() > 0) {
            helper.setGone(R.id.tv_num, true);
            helper.setText(R.id.tv_num, item.getOrderNum() + "");
        } else {
            helper.setGone(R.id.tv_num, false);
        }
        //分类选中状态
        if (item.getIsSelect()) {
            helper.setTextColor(R.id.tv_name, mContext.getResources().getColor(R.color.color_FF4500));
            helper.setGone(R.id.cur_view, true);
            helper.setBackgroundColor(R.id.cur_view, mContext.getResources().getColor(R.color.color_FF4500));
            helper.setBackgroundRes(R.id.tv_num, R.drawable.bg_red_cicle);
        } else {
            helper.setTextColor(R.id.tv_name, mContext.getResources().getColor(R.color.color_707070));
            helper.setGone(R.id.cur_view, false);
            helper.setBackgroundRes(R.id.tv_num, R.drawable.bg_gray_cicle);
        }
    }
}
