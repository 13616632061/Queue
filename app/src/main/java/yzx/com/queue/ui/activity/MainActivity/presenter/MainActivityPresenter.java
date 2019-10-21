package yzx.com.queue.ui.activity.MainActivity.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;

import yzx.com.queue.ui.activity.MainActivity.view.MainActivity;

/**
 * Created by Administrator on 2019/10/21.
 */

public class MainActivityPresenter implements IMainActivityPresenterImp{

    private MainActivity mView;

    public MainActivityPresenter(MainActivity mView) {
        this.mView = mView;
    }

    /**
     * 显示主菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void showMainMenuPopWindow() {
        mView.showMainMenuPopWindow();
    }
}
