package com.ydh.basice.load.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ydh.basice.load.Cons;
import com.ydh.basice.load.ThreadBean;

import java.util.ArrayList;
import java.util.List;

public class DownLoadDaoImpl implements DownLoadDao {


    private DownLoadDBHelper mDBHelper;
    private Context mContext;

    public DownLoadDaoImpl(Context context) {
        mContext = context;
        mDBHelper = new DownLoadDBHelper(mContext);
    }

    @Override
    public void insertThread(ThreadBean threadBean) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(Cons.DB_SQL_INSERT,
                new Object[]{threadBean.getId(), threadBean.getUrl(),
                        threadBean.getStart(), threadBean.getEnd(), threadBean.getLoadedLen()});
        db.close();
    }

    @Override
    public void deleteThread(String url, int threadId) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(Cons.DB_SQL_DELETE,
                new Object[]{url, threadId});
        db.close();
    }

    @Override
    public void updateThread(String url, int threadId, long loadedLen) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(Cons.DB_SQL_UPDATE,
                new Object[]{loadedLen, url, threadId});
        db.close();
    }

    @Override
    public List<ThreadBean> getThreads(String url) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(Cons.DB_SQL_FIND, new String[]{url});
        List<ThreadBean> threadBeans = new ArrayList<>();
        while (cursor.moveToNext()) {
            ThreadBean threadBean = new ThreadBean();
            threadBean.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
            threadBean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            threadBean.setStart(cursor.getLong(cursor.getColumnIndex("start")));
            threadBean.setEnd(cursor.getLong(cursor.getColumnIndex("end")));
            threadBean.setLoadedLen(cursor.getLong(cursor.getColumnIndex("loadedLen")));
            threadBeans.add(threadBean);
        }
        cursor.close();
        db.close();
        return threadBeans;
    }

    @Override
    public boolean isExist(String url, int threadId) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(Cons.DB_SQL_FIND_IS_EXISTS, new String[]{url, threadId + ""});
        boolean exists = cursor.moveToNext();
        cursor.close();
        db.close();

        return exists;
    }

}
