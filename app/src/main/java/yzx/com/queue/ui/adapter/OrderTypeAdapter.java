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

public class OrderTypeAdapter extends BaseQuickAdapter<OrderType, BaseViewHolder> {

    private Context mContext;

    public OrderTypeAdapter(Context mContext, int layoutResId, @Nullable List<OrderType> data) {
        super(layoutResId, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderType item) {
        //商品选中状态
        if (item.getIsSelect()) {
            helper.setImageResource(R.id.iv_select, R.mipmap.select);
        } else {
            helper.setImageResource(R.id.iv_select, R.mipmap.unselect);
        }
        //背景
        if (helper.getAdapterPosition() % 2 == 0) {
            helper.setBackgroundRes(R.id.layout_item, R.color.white);
        } else {
            helper.setBackgroundRes(R.id.layout_item, R.color.color_d4d4d4);
        }

        //分类名称
        helper.setText(R.id.tv_name,item.getTypeName());

        helper.addOnClickListener(R.id.tv_edit);
        helper.addOnClickListener(R.id.iv_select);
    }
}
