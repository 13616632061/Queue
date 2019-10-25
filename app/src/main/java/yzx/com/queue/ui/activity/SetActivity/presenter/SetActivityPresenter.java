package yzx.com.queue.ui.activity.SetActivity.presenter;

import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;

import yzx.com.queue.R;
import yzx.com.queue.ui.activity.SetActivity.model.SetActivityModel;
import yzx.com.queue.ui.activity.SetActivity.view.SetActivity;

/**
 * Created by Administrator on 2019/10/25.
 */

public class SetActivityPresenter implements ISetActivityPresenterImp {


    private SetActivity mView;
    private SetActivityModel mModel;

    public SetActivityPresenter(SetActivity mView) {
        this.mView = mView;
        mModel = new SetActivityModel();
    }

    /**
     * 清空排队信息
     */
    @Override
    public void clearQueueOrderInfo() {
        mModel.clearQueueOrderInfo();
        mView.showToastMsg(1);
    }

    /**
     * 清除显示弹窗
     */
    @Override
    public void showClearDialog() {
        mView.showClearDialog();
    }

    /**
     * 设置播放内容
     */
    @Override
    public void setPlayContent() {
        String playContent= SPUtils.getInstance().getString("playContent");
        mView.setPlayContent(playContent);
    }

    /**
     * 获取播放内容
     */
    @Override
    public void savePlayContent() {
        String playContent=mView.getPlayContent();
        if (!TextUtils.isEmpty(playContent)){
            SPUtils.getInstance().put("playContent",playContent);
            Toast.makeText(mView,mView.getResources().getString(R.string.save_success),Toast.LENGTH_SHORT).show();
        }
    }
}
