package com.yzx.lib.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.apkfuns.logutils.LogUtils;


/**
 * Created by Administrator on 2019/6/28.
 */

public class ScreenUtil {

    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics display = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(display);
        return display.heightPixels;
    }

    /**
     * 判断是否是全面屏
     */
    private volatile static boolean mHasCheckAllScreen;
    private volatile static boolean mIsAllScreenDevice;

    public static boolean isAllScreenDevice(Context context) {
        if (mHasCheckAllScreen) {
            return mIsAllScreenDevice;
        }
        mHasCheckAllScreen = true;
        mIsAllScreenDevice = false;
        // 低于 API 21的，都不会是全面屏。。。
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getRealSize(point);
            float width, height;
            if (point.x < point.y) {
                width = point.x;
                height = point.y;
            } else {
                width = point.y;
                height = point.x;
            }
            if (height / width >= 1.97f) {
                mIsAllScreenDevice = true;
            }
        }
        return mIsAllScreenDevice;
    }

    /**
     * 判断全面屏是否启用虚拟键盘
     */

    private static final String NAVIGATION = "navigationBarBackground";

    public static boolean isNavigationBarExist(@NonNull Activity activity) {
        ViewGroup vp = (ViewGroup) activity.getWindow().getDecorView();
        if (vp != null) {
            for (int i = 0; i < vp.getChildCount(); i++) {
                vp.getChildAt(i).getContext().getPackageName();
                LogUtils.e("弹出窗体ID" + vp.getChildAt(i).getId() + "");
                if (vp.getChildAt(i).getId() != -1) {
                    LogUtils.e("弹出窗体NAME" + activity.getResources().getResourceEntryName(vp.getChildAt(i).getId()) + "");
                }
                if (vp.getChildAt(i).getId() != -1 && NAVIGATION.equals(activity.getResources().getResourceEntryName(vp.getChildAt(i).getId()))) {
                    return true;
                }
            }
        }
        return false;
    }
}
