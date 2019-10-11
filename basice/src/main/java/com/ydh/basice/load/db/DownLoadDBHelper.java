package com.ydh.basice.load.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.ydh.basice.load.Cons;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/11/12 0012:14:19<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：下载的数据库帮助类
 */
public class DownLoadDBHelper extends SQLiteOpenHelper {

    public DownLoadDBHelper(@Nullable Context context) {
        super(context, Cons.DB_NAME, null, Cons.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Cons.DB_SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Cons.DB_SQL_DROP);
        db.execSQL(Cons.DB_SQL_CREATE);
    }
}
