package com.example.complain1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by wang on 2015/4/7.
 */
public class SearchHistoryDB {
    private SearchHistoryDBHelper DBHelper;
    private String TABLE = "searchhistory";

    public SearchHistoryDB(Context context){
        DBHelper = new SearchHistoryDBHelper(context);
    }
//添加历史记录
    public void insertHistory(String history){
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("history",history);
        db.insert(TABLE,null,cv);
    }
//获取所有历史记录
    public ArrayList<String> getHistory(ArrayList<String> historyList){
        historyList.clear();
        SQLiteDatabase db = DBHelper.getReadableDatabase();
        Cursor c = db.query(TABLE, null, null, null, null, null, null);//查询并获得游标
        if(c.getCount() > 10){
            deleteSingle(db);
        }
        while (c.moveToNext()){
            String history = c.getString(c.getColumnIndex("history"));
            historyList.add(history);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return historyList;
    }
//清除历史记录
    public void clearHistory(){
        SQLiteDatabase db = DBHelper.getReadableDatabase();
        String DELETE_SQL = "DELETE FROM " + TABLE;
        db.execSQL(DELETE_SQL);
    }

    private void deleteSingle(SQLiteDatabase db){
        ArrayList<String> historyList = new ArrayList<String>();
        historyList = getHistory(historyList);
        String[] firstHistory ={ historyList.get(0)};
        String whereClause = "history=?";
        db.delete(TABLE,whereClause,firstHistory);
    }
}
