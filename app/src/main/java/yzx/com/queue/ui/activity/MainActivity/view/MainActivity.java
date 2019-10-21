package yzx.com.queue.ui.activity.MainActivity.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.nshmura.recyclertablayout.RecyclerTabLayout;
import com.yzx.lib.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yzx.com.queue.R;
import yzx.com.queue.constant.RouteMap;
import yzx.com.queue.ui.activity.MainActivity.presenter.MainActivityPresenter;
import yzx.com.queue.ui.popuWindow.MainMenuPopWindow;
import yzx.com.queue.view.TakeNumberView;

@Route(path = RouteMap.ROUTE_MAIN_ACTIVITY)
public class MainActivity extends BaseActivity implements IMainActivityView {


    @InjectView(R.id.iv_menu)
    ImageView ivMenu;
    @InjectView(R.id.tab_layout)
    RecyclerTabLayout tabLayout;
    @InjectView(R.id.tv_history)
    TextView tvHistory;
    @InjectView(R.id.list)
    RecyclerView list;
    @InjectView(R.id.take_number)
    TakeNumberView takeNumber;
    @InjectView(R.id.layout_title)
    RelativeLayout layoutTitle;

    private MainActivityPresenter mPresenter;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mPresenter = new MainActivityPresenter(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.iv_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_menu://菜单
                mPresenter.showMainMenuPopWindow();
                break;
        }
    }

    /**
     * 显示主菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void showMainMenuPopWindow() {
        MainMenuPopWindow menuPopWindow = MainMenuPopWindow.getInstance(this);
        menuPopWindow.showAsDropDown(layoutTitle, 0, 0, Gravity.NO_GRAVITY);
    }


}
