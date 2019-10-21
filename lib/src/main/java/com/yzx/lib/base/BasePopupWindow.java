package com.yzx.lib.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;

/**
 * Created by Administrator on 2019/10/21.
 */

public class BasePopupWindow extends PopupWindow {

    private Context context;


    public BasePopupWindow(Context context) {
        super(context);
        this.context = context;
    }

    public void initSet(View view) {
        this.setContentView(view);
        //sdk > 21 解决 标题栏没有办法遮罩的问题
        this.setClippingEnabled(false);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.MATCH_PARENT);//屏幕的高
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimationWindow);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
//        view.setOnTouchListener(this);
    }

    public void hideStatusBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            view.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 触摸事件
     *
     * @param view
     */
    public void setOnTouchListener(View view, final View contentView) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isShouldHideInput(contentView, event);
                return false;
            }
        });
    }

    //判断点击是view以内
    public void isShouldHideInput(View v, MotionEvent event) {
        if (v != null) {//java 中的instanceof 运算符是用来在运行时指出对象是否是特定类的一个实例。instanceof通过返回一个布尔值来指出，这个对象是否是这个特定类或者是它的子类的一个实例,这里也就是用来判断是否为输入框
            int left = v.getLeft();
            int right = v.getRight();
            int bottom = v.getBottom();
            int top = v.getTop();
            int y = (int) event.getY();
            int x = (int) event.getX();
            if (x < left || x > right || y < top || y > bottom) { // 点击的是输入框区域，保留点击EditText的事件
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);//输入法管理器，获取系统输入法系统服务
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //隐藏软键盘
                    v.clearFocus();
                }
            }

        }
    }
}
