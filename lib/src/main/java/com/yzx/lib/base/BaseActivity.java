package com.yzx.lib.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.squareup.leakcanary.RefWatcher;
import com.yzx.lib.R;
import com.yzx.lib.app.LibAplication;
import com.yzx.lib.entity.MessageEvent;
import com.yzx.lib.weight.TopBarLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by lyf on 2019/4/29.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnSystemUiVisibilityChangeListener {

//    public static Activity mCurrentActivity;

//    private static List<Activity> mActivities = new LinkedList<>();
    private Handler mHandler;
    private android.support.v4.app.FragmentManager fragmentManager;
    //当前正在展示的Fragment
    private BaseFragment showFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.inject(this);
        // 处理editText弹出软键盘后，沉侵式状态栏重新显示
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(this);
        //路由自动属性注入
        ARouter.getInstance().inject(this);
        fragmentManager = getSupportFragmentManager();

        mHandler = new Handler();
        initView();
        initListener();

    }

    /**
     * 初始化标题栏
     *
     * @param title
     */
    public void inintTitle(String title) {
        TopBarLayout topBarLayout = findViewById(R.id.top_bar_layout);
        topBarLayout.setTitleText(title);
        topBarLayout.setTopBarLayoutLeftOnClickListener(new TopBarLayout.TopBarLayoutLeftOnClickListener() {
            @Override
            public void leftOnClick() {
                finish();
            }
        });

    }

    /**
     * 隐藏底部导航栏和顶部系统状态栏
     */
    private void hideStatusBar() {
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideStatusBar();
        }
    }

    /**
     * 返回布局id
     *
     * @return
     */

    public abstract int getContentView();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化监听
     */
    protected void initListener() {
    }

    ;

    @Override
    protected void onResume() {
        super.onResume();
//        mCurrentActivity = this;
//        hideStatusBar();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mCurrentActivity = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //测试内存泄漏，正式一定要隐藏
//        RefWatcher refWatcher = LibAplication.getRefWatcher(this);//1
//        refWatcher.watch(this);

//        synchronized (mActivities) {
//            mActivities.remove(this);
//        }

        KeyboardUtils.hideSoftInput(this);

    }


    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * TODO 点击空白处软键盘消失
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {//触摸事件，监听对屏幕的触摸情况，
//ACTION_DOWN 按下   ACTION_MOVE 移动 ACTION_UP 离开
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {//判断手指是否按下
            View v = getCurrentFocus();//获取当前的焦点
            if (isShouldHideInput(v, ev)) {//判断软件盘是否隐藏 ，true为点击是输入框之外，隐藏软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);//输入法管理器，获取系统输入法系统服务
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //隐藏软键盘
                    v.clearFocus();
//                    hideStatusBar();
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    //判断点击是否在输入框以内
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {//java 中的instanceof 运算符是用来在运行时指出对象是否是特定类的一个实例。instanceof通过返回一个布尔值来指出，这个对象是否是这个特定类或者是它的子类的一个实例,这里也就是用来判断是否为输入框
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);//获取在当前窗口内的绝对坐标，基于当前窗口的左上角，getLeft , getTop, getBottom, getRight,  这一组是获取相对在它父窗口里的坐标
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) { // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    @Override
    public void onSystemUiVisibilityChange(int visibility) {
        Log.d("immerse", "onSystemUiVisibilityChange: " + visibility);
        if (visibility == 0) {
            mHandler.removeCallbacks(enterImmerseMode);
            mHandler.post(enterImmerseMode);
        }

    }

    private Runnable enterImmerseMode = new Runnable() {
        @Override
        public void run() {
            hideStatusBar();
        }
    };

    /**
     * 显示隐藏Fragment
     * isRemove:处理每次点击都要加载的需求
     */
    protected void showFragment(int resid, BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //隐藏正在显示的Fragment
        if (showFragment != null) {
            fragmentTransaction.hide(showFragment);
        }
        //展示需要显示的Fragment对象
        Fragment mFragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());
        if (mFragment != null) {
            fragmentTransaction.show(mFragment);
            showFragment = (BaseFragment) mFragment;
        } else {
            fragmentTransaction.add(resid, fragment, fragment.getClass().getName());
            showFragment = fragment;
        }
        fragmentTransaction.commit();
    }
}
