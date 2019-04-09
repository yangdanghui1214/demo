package com.ydh.lib_retrofit2.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 请求队列（线程池管理类
 *
 * @author 13001
 */
public class ThreadPoolManager {

    private static ThreadPoolManager threadPoolManager = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return threadPoolManager;
    }

    // LinkedBlockingQueue 这种队列是按照 FIFO （先进先出) 排序元素
    // 新元素会自动到元素尾部
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();

    public void addTask(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        try {
            mQueue.add(runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 创建线程池
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                runnable -> null,
                (runnable, threadPoolExecutor) -> {

                });
        mThreadPoolExecutor.execute(coreThread);
    }

    // 创建核心线程，不停的获取队列中的请求，并提交线程池处理s
    public Runnable coreThread=new Runnable() {
        Runnable run;
        @Override
        public void run() {
            while (true){
                try {
                    run=mQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mThreadPoolExecutor.execute(run);
            }
        }
    };


}
