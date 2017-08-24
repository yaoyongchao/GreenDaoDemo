package com.yyc.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yyc.greendaodemo.greenone.DbHelper;
import com.yyc.greendaodemo.greentwo.DaoUtils;
import com.yyc.greendaodemo.greentwo.UserManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    DbHelper<User> dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper<User>(this);

//        greenone();
        greenTwo();
    }

    private void greenone() {
        User u = new User();
        u.setName("张三");

        dbHelper.insert(u);
        List<User> list = dbHelper.listAll(User.class);

        for(User user:list)
            Log.i(TAG,user.getName()+ "---------" + user.getId());

//        dbHelper.deleteAll();
    }

    private void greenTwo() {
        UserManager userManager = new UserManager(this);
        User u = new User();
        u.setName("张三");

        DaoUtils.getUserInstance().insertObject(u);

        List<User> list = DaoUtils.getUserInstance().QueryAll(User.class);

        for(User user:list)
            Log.i(TAG,user.getName()+ "---------" + user.getId());
    }



}
