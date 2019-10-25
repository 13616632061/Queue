package yzx.com.queue.ui.activity.SetActivity.view;

/**
 * Created by Administrator on 2019/10/25.
 */

public interface ISetActivityView {

    //清除显示弹窗
    void showClearDialog();

    //显示提示信息
    void showToastMsg(int type);

    //设置播放内容
    void setPlayContent(String playContent);

    //获取播放内容
    String getPlayContent();
}
