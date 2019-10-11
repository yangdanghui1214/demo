package com.ydh.basice.load;

import android.os.Environment;

public class Cons {

    //intent传递数据----开始下载时，传递FileBean到Service 标示
    public static final String SEND_FILE_BEAN = "send_file_bean";
    //广播更新进度
    public static final String SEND_LOADED_PROGRESS = "send_loaded_length";

    //下载地址
    public static final String URL = "https://imtt.dd.qq.com/16891/4611E43165D203CB6A52E65759FE7641.apk?fsname=com.daimajia.gold_5.6.2_196.apk&csr=1bbd";

    //文件下载路径
    public static final String DOWNLOAD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/b_download/";

    //Handler的Message处理的常量
    public static final int MSG_CREATE_FILE_OK = 0x00;

    // 下载 APK 数据更新广播
    public static final String ACTION_UPDATE = "com.tsign.load.action.update";
    // 下载 APK 错误广播
    public static final String ACTION_ERROR = "com.tsign.load.action.error";
    // 下载 APK 结束广播
    public static final String ACTION_FINISH = "com.tsign.load.action.finish";


    /**
     * 数据库相关常量
     */
    public static final String DB_NAME = "download.db";//数据库名
    public static final int VERSION = 1;//版本
    public static final String DB_TABLE_NAME = "thread_info";//数据库名
    public static final String DB_SQL_CREATE = //创建表
            "CREATE TABLE " + DB_TABLE_NAME + "(\n" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "thread_id INTEGER,\n" +
                    "url TEXT,\n" +
                    "start INTEGER,\n" +
                    "end INTEGER,\n" +
                    "loadedLen INTEGER\n" +
                    ")";
    public static final String DB_SQL_DROP =//删除表表
            "DROP TABLE IF EXISTS " + DB_TABLE_NAME;
    public static final String DB_SQL_INSERT =//插入
            "INSERT INTO " + DB_TABLE_NAME + " (thread_id,url,start,end,loadedLen) values(?,?,?,?,?)";
    public static final String DB_SQL_DELETE =//删除
            "DELETE FROM " + DB_TABLE_NAME + " WHERE url = ? AND thread_id = ?";
    public static final String DB_SQL_UPDATE =//更新
            "UPDATE " + DB_TABLE_NAME + " SET loadedLen = ? WHERE url = ? AND thread_id = ?";
    public static final String DB_SQL_FIND =//查询
            "SELECT * FROM " + DB_TABLE_NAME + " WHERE url = ?";
    public static final String DB_SQL_FIND_IS_EXISTS =//查询是否存在
            "SELECT * FROM " + DB_TABLE_NAME + " WHERE url = ? AND thread_id = ?";

}
