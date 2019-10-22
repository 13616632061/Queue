package yzx.com.queue.ui.popuWindow.OrderTypePopWindow.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.lib.base.BasePopupWindow;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yzx.com.queue.R;

/**
 * Created by Administrator on 2019/10/22.
 */

public class OrderTypePopWindow extends BasePopupWindow implements IOrderTypePopWindowView{

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

    public OrderTypePopWindow(Context context) {
        super(context);

        mContext = context;
        view = View.inflate(context, R.layout.layout_order_type_pop, null);
        ButterKnife.inject(this, view);
        initSet(view);
        hideStatusBar(view);
        setOnTouchListener(layoutContent, view);

    }

    @OnClick({R.id.btn_add, R.id.btn_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                break;
            case R.id.btn_delete:
                dismiss();
                break;
        }
    }

    /**
     * 获取分类名称
     */
    @Override
    public String getTypeName() {
       return etName.getText().toString().trim();
    }

    /**
     * 获取最少人数
     * @return
     */
    @Override
    public String getMinNum() {
        return etMinNum.getText().toString().trim();
    }

    /**
     * 获取最大人数
     * @return
     */
    @Override
    public String getMaxNum() {
        return etMaxNum.getText().toString().trim();
    }
}
