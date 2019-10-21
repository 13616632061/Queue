package yzx.com.queue.app;

import com.yzx.lib.app.LibAplication;

import yzx.com.queue.greendao.DaoMaster;
import yzx.com.queue.greendao.DaoOpenHelper;
import yzx.com.queue.greendao.DaoSession;

/**
 * Created by Administrator on 2019/10/21.
 */

public class MyAplication extends LibAplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }
    /**
     * 初始化GreenDao,直接在Application中进行初始化操作,因更改数据库地址至sd卡需要读写权限
     */
    private void initGreenDao() {
        DaoOpenHelper helper = new DaoOpenHelper(this, "yzxQueue.db",
                null);
        DaoMaster  daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    private static DaoSession daoSession;

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
