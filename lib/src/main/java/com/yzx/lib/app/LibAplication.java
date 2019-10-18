package com.yzx.lib.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by lyf on 2019/4/29.
 */

public class LibAplication extends Application {

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = setupLeakCanary();
        //初始化工具类
        Utils.init(this);
        //初始化路由
        ARouter.openDebug();
        ARouter.openDebug();
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 初始化内存监测工具 LeakCanary
     * @return
     */
    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        LibAplication leakApplication = (LibAplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}

