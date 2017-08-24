package com.yyc.greendaodemo.greenone;

import android.content.Context;

import com.yyc.greendaodemo.gen.DaoMaster;
import com.yyc.greendaodemo.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * 1、创建数据库
 * 2、创建数据库表
 * 3、包含对数据库的CRUD
 * 4、对数据库的升级
 */
public class DbManager {
    private static final String TAG = DbManager.class.getSimpleName();
    private static final String DBNAME = "db.sqlite";
    private static volatile DbManager manager;
    private static DaoMaster.DevOpenHelper helper;
    private static DaoSession daoSession;
    private Context mContext;
    private DbManager(Context context){this.mContext = context;}

    public static DbManager getInstance(Context context){
        if (manager == null){
            synchronized (DbManager.class){
                if (manager == null){
                    manager = new DbManager(context);
                }
            }
        }
        return  manager;
    }

    private static DaoMaster daoMaster;

    /**
     * 判断是否存在数据库，没有就创建
     * @return
     */
    public DaoMaster getDaoMaster(){
        if (daoMaster == null){
            helper = new DaoMaster.DevOpenHelper(mContext, DBNAME, null);
            daoMaster = new DaoMaster(helper.getWritableDb());
        }
        return daoMaster;
    }

    /**
     * 数据处理
     * @return
     */
    public DaoSession getDaoSession(){
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return  daoSession;
    }

    /**
     * 输出日志
     */
    public void setDebug(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    public void close(){
        closeHelper();
        closeSession();
    }

    public void closeHelper(){
        if (helper != null){
            helper.close();
            helper =  null;
        }
    }
    /**
     * 关闭session
     */
    public void closeSession(){
        if (daoSession != null){
            daoSession.clear();
            daoSession = null;
        }
    }
}