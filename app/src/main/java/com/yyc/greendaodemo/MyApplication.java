package com.yyc.greendaodemo;

import android.app.Application;

import com.yyc.greendaodemo.greentwo.DaoUtils;

/**
 * @author: Page
 * @time: 17-8-24
 * @description:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DaoUtils.init(this);
    }
}
