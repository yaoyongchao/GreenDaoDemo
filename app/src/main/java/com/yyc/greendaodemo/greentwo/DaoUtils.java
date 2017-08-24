package com.yyc.greendaodemo.greentwo;

import android.content.Context;

/**
 * @author: Page
 * @time: 17-8-24
 * @description:
 */

public class DaoUtils {
    public static Context context;


    public static void init (Context context) {
        DaoUtils.context = context;
    }

    private static class UserManagerHolder{
        private final static UserManager instance = new UserManager(context);
    }

    public static UserManager getUserInstance() {
        return UserManagerHolder.instance;
    }
}
