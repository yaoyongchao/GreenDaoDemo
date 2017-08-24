package com.yyc.greendaodemo.greentwo;

import android.content.Context;

import com.yyc.greendaodemo.User;

/**
 * @author: Page
 * @time: 17-8-24
 * @description:
 */

public class UserManager extends BaseDao<User> {
    public UserManager(Context context) {
        super(context);
    }
}
