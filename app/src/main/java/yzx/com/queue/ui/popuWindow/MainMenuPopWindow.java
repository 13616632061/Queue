package yzx.com.queue.ui.popuWindow;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yzx.lib.base.BasePopupWindow;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import yzx.com.queue.R;
import yzx.com.queue.constant.RouteMap;

/**
 * Created by Administrator on 2019/10/21.
 */

public class MainMenuPopWindow extends BasePopupWindow {


    private Context mContext;
    private View view;

    public static MainMenuPopWindow mainMenuPopupWindowInstance;

    public static MainMenuPopWindow getInstance(Context context) {
        if (mainMenuPopupWindowInstance == null) {
            synchronized (MainMenuPopWindow.class) {
                if (mainMenuPopupWindowInstance == null) {
                    mainMenuPopupWindowInstance = new MainMenuPopWindow(context);
                }
            }
        }
        return mainMenuPopupWindowInstance;
    }


    private MainMenuPopWindow(Context context) {
        super(context);
        mContext = context;
        view = View.inflate(context, R.layout.layout_main_menu_pop, null);
        ButterKnife.inject(this, view);
        initSet(view);
        hideStatusBar(view);

        this.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @OnClick({R.id.tv_type, R.id.tv_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_type://分类管理
                ARouter.getInstance().build(RouteMap.ROUTE_ORDER_TYPE_ACTIVITY).navigation();
                exit();
                break;
            case R.id.tv_set:
                ARouter.getInstance().build(RouteMap.ROUTE_SET_ACTIVITY).navigation();
                exit();
                break;
        }
    }

    public void exit() {
        dismiss();
        mainMenuPopupWindowInstance = null;
    }
}
