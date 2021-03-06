package com.ydh.basice.load.db;

import com.ydh.basice.load.ThreadBean;

import java.util.List;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/11/12 0012:14:36<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：数据访问接口
 */
public interface DownLoadDao {
    /**
     * 在数据库插入线程信息
     *
     * @param threadBean 线程信息
     */
    void insertThread(ThreadBean threadBean);

    /**
     * 在数据库删除线程信息
     *
     * @param url      下载的url
     * @param threadId 线程的id
     */
    void deleteThread(String url, int threadId);

    /**
     * 在数据库更新线程信息---下载进度
     *
     * @param url      下载的url
     * @param threadId 线程的id
     */
    void updateThread(String url, int threadId ,long loadedLen);

    /**
     * 获取一个文件下载的所有线程信息(多线程下载)
     * @param url 下载的url
     * @return  线程信息集合
     */
    List<ThreadBean> getThreads(String url);

    /**
     * 判断数据库中该线程信息是否存在
     *
     * @param url      下载的url
     * @param threadId 线程的id
     */
    boolean isExist(String url, int threadId);
}