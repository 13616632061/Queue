package yzx.com.queue.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2019/10/22.
 */

public class GreenDaoHelp {

    private static GreenDaoHelp instance;
    private Context mContext;

    private GreenDaoHelp(Context mContext) {
        this.mContext = mContext.getApplicationContext();
        initGreenDao();
    }

    public static GreenDaoHelp getInstance(Context mContext) {
        if (instance == null) {
            synchronized (GreenDaoHelp.class) {
                if (instance == null) {
                    instance = new GreenDaoHelp(mContext);
                }
            }
        }
        return instance;
    }

    /**
     * 初始化GreenDao,一般直接在Application中进行初始化操作，因更改数据库地址至sd卡需要读写权限
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(new GreenDaoContext(mContext), "yzxQueue.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private static DaoSession daoSession;

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
