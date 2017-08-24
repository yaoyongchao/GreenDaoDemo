package com.yyc.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yyc.greendaodemo.greenone.DbHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    DbHelper<User> dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper<>(this);

        greenone();
    }

    private void greenone() {
        User u = new User();
        u.setName("张三");

        dbHelper.insert(u);
        dbHelper.listAll();
        List<User> list = dbHelper.listAll();

        for(User user:list)
            Log.i(TAG,user.getName()+ "---------" + user.getId());
    }



}
