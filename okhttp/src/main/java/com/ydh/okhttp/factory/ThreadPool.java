package com.ydh.okhttp.factory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author haru
 * @date 2017-12-5 20:10:02
 * 线程池的实现方式
 */
public class ThreadPool {

    private static ThreadPool singleton;
    /**
     * 线程池的大小最好设置成为CUP核数的2N
     */
    private final static int POOL_SIZE = 4;
    /**
     * 设置线程池的最大线程数
     */
    private final static int MAX_POOL_SIZE = 6;
    private final static int MAX_SIZE = 3;
    /**
     * 设置线程的存活时间
     */
    private final static int KEEP_ALIVE_TIME = 4;
    private final Executor mExecutor;
    /**
     * 创建一个定长线程池，支持定时及周期性任务执行
     */
    private ScheduledExecutorService scheduledThreadPool;

    // 1. 创建定长线程池对象 & 设置线程池线程数量固定为3
    private ExecutorService fixedThreadPool;

    // 1. 创建单线程化线程池
    private ExecutorService singleThreadExecutor;

    public ExecutorService getSingleThreadExecutor() {
        return singleThreadExecutor;
    }

    public ThreadPool() {
        // 创建线程池工厂
        ThreadFactory factory = new PriorityThreadFactory("thread-pool", android.os.Process.THREAD_PRIORITY_BACKGROUND);
        // 创建工作队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
        mExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue, factory);
        scheduledThreadPool = new ScheduledThreadPoolExecutor(2);
        fixedThreadPool = Executors.newFixedThreadPool(3);
        singleThreadExecutor = Executors.newSingleThreadExecutor();
    }

    public static ThreadPool getInstance() {
        if (singleton == null) {
            singleton = new ThreadPool();
        }
        return singleton;
    }

    /**
     * 在线程池中执行线程
     */
    public void submit(Runnable command) {
        mExecutor.execute(command);
    }

    /**
     * 特点：核心线程数量固定、非核心线程数量无限制（闲置时马上回收）
     * 应用场景：执行定时 / 周期性 任务
     *
     * @param command
     * @param initialDelay
     * @param period
     * @param unit
     */
    public void submitScheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        scheduledThreadPool.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    public void shutDownScheduleAtFixedRate() {
        if (scheduledThreadPool != null) {
            scheduledThreadPool.shutdown();
        }
    }

    /**
     * 特点：只有核心线程 & 不会被回收、线程数量固定、任务队列无大小限制（超出的线程任务会在队列中等待）
     * 应用场景：控制线程最大并发数
     *
     * @param command
     */
    public void submitFixedThreadPool(Runnable command) {
        fixedThreadPool.execute(command);
    }

    public void shutdownFixedThreadPool() {
        if (fixedThreadPool != null) {
            fixedThreadPool.shutdown();
        }
    }

    /**
     * 特点：只有一个核心线程（保证所有任务按照指定顺序在一个线程中执行，不需要处理线程同步的问题）
     * <p>
     * 应用场景：不适合并发但可能引起IO阻塞性及影响UI线程响应的操作，如数据库操作，文件操作等
     *
     * @param command
     */
    public void submitSingleThreadExecutor(Runnable command) {
        singleThreadExecutor.execute(command);
    }

    public void shutdownSingleThreadExecutor() {
        if (singleThreadExecutor != null) {
            singleThreadExecutor.shutdown();
        }
    }
}