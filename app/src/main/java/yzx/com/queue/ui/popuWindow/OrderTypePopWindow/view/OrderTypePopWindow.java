package yzx.com.queue.ui.popuWindow.OrderTypePopWindow.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yzx.lib.base.BasePopupWindow;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yzx.com.queue.R;
import yzx.com.queue.entity.OrderType;
import yzx.com.queue.ui.popuWindow.OrderTypePopWindow.presenter.OrderTypePopWindowPresenter;

/**
 * Created by Administrator on 2019/10/22.
 */

public class OrderTypePopWindow extends BasePopupWindow implements IOrderTypePopWindowView {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.et_name)
    EditText etName;
    @InjectView(R.id.et_min_num)
    EditText etMinNum;
    @InjectView(R.id.et_max_num)
    EditText etMaxNum;
    @InjectView(R.id.btn_delete)
    Button btnDelete;
    @InjectView(R.id.btn_add)
    Button btnAdd;
    @InjectView(R.id.layout_content)
    LinearLayout layoutContent;

    private Context mContext;
    private View view;
    private OrderTypePopWindowPresenter mPresenter;

    public OrderTypePopWindow(Context context, OrderType orderType) {
        super(context);
        mContext = context;
        view = View.inflate(context, R.layout.layout_order_type_pop, null);
        ButterKnife.inject(this, view);
        initSet(view);
        hideStatusBar(view);
        setOnTouchListener(view, layoutContent);

        mPresenter = new OrderTypePopWindowPresenter(this);
        mPresenter.setTitle(context,orderType);


    }

    @OnClick({R.id.btn_add, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                mPresenter.addOrEditType();
                break;
            case R.id.btn_delete:
                dismiss();
                break;
        }
    }

    /**
     * 设置分类名称
     * @param name
     */
    @Override
    public void setTypeName(String name) {
        etName.setText(name);
    }

    /**
     * 获取分类名称
     */
    @Override
    public String getTypeName() {
        return etName.getText().toString().trim();
    }

    /**
     * 设置标题
     *
     * @param title
     */
    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 设置最少人数
     * @param minNum
     */
    @Override
    public void setMinNum(String minNum) {
        etMinNum.setText(minNum);
    }

    /**
     * 获取最少人数
     *
     * @return
     */
    @Override
    public String getMinNum() {
        return etMinNum.getText().toString().trim();
    }

    /**
     * 设置最大人数
     * @param num
     */
    @Override
    public void setMaxNum(String num) {
        etMaxNum.setText(num);
    }

    /**
     * 获取最大人数
     *
     * @return
     */
    @Override
    public String getMaxNum() {
        return etMaxNum.getText().toString().trim();
    }

    /**
     * 显示提示信息
     */
    @Override
    public void showMsg(int type) {
        switch (type) {
            case 1://分类为空
                Toast.makeText(mContext, mContext.getResources().getString(R.string.empty_type_name), Toast.LENGTH_SHORT).show();
                break;
            case 2://人数范围为空
                Toast.makeText(mContext, mContext.getResources().getString(R.string.empty_type_num), Toast.LENGTH_SHORT).show();
                break;
            case 3://最大就餐人数大于最小就餐人数，请重新输入
                Toast.makeText(mContext, mContext.getResources().getString(R.string.min_greater_max), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
