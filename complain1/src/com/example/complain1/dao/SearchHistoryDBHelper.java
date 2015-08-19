package com.example.complain1.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wang on 2015/4/7.
 */
public class SearchHistoryDBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "complain.db";
    private static final int version = 1; //数据库版本

    public SearchHistoryDBHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE table IF NOT EXISTS searchhistory(id INTEGER PRIMARY KEY AUTOINCREMENT,history TEXT);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
