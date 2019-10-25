package yzx.com.queue.ui.activity.SetActivity.view;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yzx.lib.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yzx.com.queue.R;
import yzx.com.queue.constant.RouteMap;
import yzx.com.queue.ui.activity.SetActivity.presenter.SetActivityPresenter;
import yzx.com.queue.ui.popuWindow.SureAndCancelPopWindow;

@Route(path = RouteMap.ROUTE_SET_ACTIVITY)
public class SetActivity extends BaseActivity implements ISetActivityView {


    @InjectView(R.id.layout_root)
    LinearLayout layoutRoot;
    @InjectView(R.id.et_paly_content)
    EditText etPalyContent;
    private SetActivityPresenter mPresenter;

    @Override
    public int getContentView() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        inintTitle(getResources().getString(R.string.set));
        mPresenter = new SetActivityPresenter(this);
        mPresenter.setPlayContent();
    }

    @OnClick({R.id.btn_clear_order_info, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clear_order_info:
                mPresenter.showClearDialog();
                break;
            case R.id.btn_save:
                mPresenter.savePlayContent();
                break;
        }
    }

    /**
     * 清除显示弹窗
     */
    @Override
    public void showClearDialog() {
        final SureAndCancelPopWindow sureAndCancelPopWindow = new SureAndCancelPopWindow(this);
        sureAndCancelPopWindow.setTvMsg(getResources().getString(R.string.if_clear_order_info));
        sureAndCancelPopWindow.showAtLocation(layoutRoot, Gravity.NO_GRAVITY, 0, 0);
        sureAndCancelPopWindow.setSureOnClickLinsenter(new SureAndCancelPopWindow.setSureOnClickLinsenter() {
            @Override
            public void setSureOnClickLinsenter() {
                mPresenter.clearQueueOrderInfo();
                sureAndCancelPopWindow.dismiss();
            }
        });
    }

    /**
     * 显示提示信息
     *
     * @param type
     */
    @Override
    public void showToastMsg(int type) {
        switch (type) {
            case 1://操作成功
                Toast.makeText(this, getResources().getString(R.string.handle_success), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 设置播放内容
     * @param playContent
     */
    @Override
    public void setPlayContent(String playContent) {
        etPalyContent.setText(playContent);
    }

    /**
     * 获取播放内容
     *
     * @return
     */
    @Override
    public String getPlayContent() {
        return etPalyContent.getText().toString().trim();
    }

}
