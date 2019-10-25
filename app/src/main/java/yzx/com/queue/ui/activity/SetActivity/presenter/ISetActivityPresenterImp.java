package yzx.com.queue.ui.activity.SetActivity.presenter;

/**
 * Created by Administrator on 2019/10/25.
 */

public interface ISetActivityPresenterImp {

    //清空排队信息
    void clearQueueOrderInfo();

    //清除显示弹窗
    void showClearDialog();

    //设置播放内容
    void setPlayContent();

    //获取播放内容
    void savePlayContent();
}
