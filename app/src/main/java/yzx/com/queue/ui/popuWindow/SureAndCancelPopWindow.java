package yzx.com.queue.ui.popuWindow;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.lib.base.BasePopupWindow;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yzx.com.queue.R;

/**
 * Created by Administrator on 2019/10/25.
 */

public class SureAndCancelPopWindow extends BasePopupWindow {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_msg)
    TextView tvMsg;
    @InjectView(R.id.layout_content)
    LinearLayout layoutContent;
    private Context mContext;
    private View view;

    private setSureOnClickLinsenter msetSureOnClickLinsenter;


    public SureAndCancelPopWindow(Context context) {
        super(context);

        mContext = context;
        view = View.inflate(context, R.layout.layout_sure_cancel, null);
        ButterKnife.inject(this, view);
        initSet(view);
        hideStatusBar(view);
        setOnTouchListener(view, layoutContent);
    }

    @OnClick({R.id.btn_sure, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_sure:
                if (msetSureOnClickLinsenter != null) {
                    msetSureOnClickLinsenter.setSureOnClickLinsenter();
                }
                break;
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTvTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 设置提示信息内容
     *
     * @param msg
     */
    public void setTvMsg(String msg) {
        tvMsg.setText(msg);
    }

    public interface setSureOnClickLinsenter {
        void setSureOnClickLinsenter();
    }

    public void setSureOnClickLinsenter(setSureOnClickLinsenter msetSureOnClickLinsenter) {
        this.msetSureOnClickLinsenter = msetSureOnClickLinsenter;
    }
}
