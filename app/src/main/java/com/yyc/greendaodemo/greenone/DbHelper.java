package com.yyc.greendaodemo.greenone;

import android.content.Context;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 */
public class DbHelper<T> {
    private DbManager manager;

    public DbHelper(Context context) {
        manager = DbManager.getInstance(context);
    }

    private Class<T> clazz;

    private Class<T> getClazz() {
        /*if (clazz == null) {//获取泛型的Class对象
            clazz = ((Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clazz;*/

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        clazz = (Class) params[0];


        //为了得到T的Class，采用如下方法
        //1得到该泛型类的子类对象的Class对象
        Class clz = this.getClass();
        //2得到子类对象的泛型父类类型（也就是BaseDaoImpl<T>）
        ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
        System.out.println(type);
        //
        Type[] types = type.getActualTypeArguments();

        clazz = (Class<T>) types[0];
        System.out.println(clazz.getSimpleName());

        return clazz;
    }

    /**
     * 插入
     * @param t
     * @return
     */
    public boolean insert(T t) {
        return manager.getDaoSession().insert(t) != -1 ? true : false;
    }

    /**
     * 插入集合
     * @param datas
     * @return
     */
    public boolean insertList(final List<T> datas) {
        boolean flag = false;
        try {
            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T t : datas) {
                        manager.getDaoSession().insertOrReplace(t);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除
     * @param t
     * @return
     */
    public boolean delete(T t) {
        try {
            manager.getDaoSession().delete(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除所有
     * @param
     * @return
     */
    public boolean deleteAll() {
        try {
            manager.getDaoSession().deleteAll(clazz);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 列出所有
     * @return
     */
    public List<T> listAll(Class<T> cls) {
        return (List<T>) manager.getDaoSession().loadAll(getClazz());
//        return (List<T>) manager.getDaoSession().loadAll(getClazz());
    }

    public T find(long id){
        return manager.getDaoSession().load(clazz, id);
    }


    /**
     * 更新
     * @param t
     * @return
     */
    public boolean update(T t) {
        try {
            manager.getDaoSession().update(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * queryRaw查询
     * @param where
     * @param selectionArgs
     * @return
     */
    public List<T> queryAll(String where, String... selectionArgs){
        List<T> list = manager.getDaoSession().queryRaw(clazz, where, selectionArgs);
        return list;
    }

    /**
     * biuld查询
     * @param age
     * @param address
     * @param limit
     * @return
     */
    /*public List<Student> queryBuilder(int age, String address, int limit){
        List<Student> list = (List<Student>) manager.getDaoSession().queryBuilder(clazz)
                .where(StudentDao.Properties.Age.eq(age))
                .where(StudentDao.Properties.Address.like(address))
                .limit(limit)
                .list();
        return list;
    }*/
}