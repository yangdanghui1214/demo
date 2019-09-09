package com.tsign.health.factory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
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
    private final static int POOL_SIZE = 8;
    /**
     * 设置线程池的最大线程数
     */
    private final static int MAX_POOL_SIZE = 6;
    private final static int MAX_SIZE = 3;
    /**
     * 设置线程的存活时间
     */
    private final static long KEEP_ALIVE_TIME = 60L;
    private final static long KEEP_NO_ALIVE_TIME = 0L;


    // 1. 创建定长线程池对象 & 设置线程池线程数量固定为3

    private ExecutorService fixedThreadPool;

    public ExecutorService getFixedThreadPool() {
        return fixedThreadPool;
    }

    //2.创建一个定长线程池，支持定时及周期性任务执行

    private ScheduledExecutorService scheduledThreadPool;

    public ScheduledExecutorService getScheduledThreadPool() {
        return scheduledThreadPool;
    }

    // 3. 创建可缓存线程池对象

    private ExecutorService cachedThreadPool;

    public ExecutorService getCachedThreadPool() {
        return cachedThreadPool;
    }

    // 4. 创建单线程化线程池

    private ExecutorService singleThreadExecutor;

    public ExecutorService getSingleThreadExecutor() {
        return singleThreadExecutor;
    }

    private ThreadPool() {
        // 创建工作队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
        ThreadFactory fixedFactory = new PriorityThreadFactory("fixed-thread-pool",
                android.os.Process.THREAD_PRIORITY_BACKGROUND);
        fixedThreadPool = new ThreadPoolExecutor(MAX_SIZE, MAX_SIZE, KEEP_NO_ALIVE_TIME,
                TimeUnit.MILLISECONDS, workQueue, fixedFactory);

        ThreadFactory scheduledFactory = new PriorityThreadFactory("scheduled-thread-pool",
                android.os.Process.THREAD_PRIORITY_BACKGROUND);
        scheduledThreadPool = new ScheduledThreadPoolExecutor(5, scheduledFactory);

        ThreadFactory cachedFactory = new PriorityThreadFactory("cache-thread-pool",
                android.os.Process.THREAD_PRIORITY_BACKGROUND);
        cachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, KEEP_ALIVE_TIME,
                TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), cachedFactory);

        ThreadFactory singleFactory = new PriorityThreadFactory("single-thread-pool",
                android.os.Process.THREAD_PRIORITY_BACKGROUND);
        singleThreadExecutor = new ThreadPoolExecutor(1, 1,
                KEEP_NO_ALIVE_TIME, TimeUnit.MILLISECONDS, workQueue, singleFactory);
    }

    public static ThreadPool getInstance() {
        if (singleton == null) {
            singleton = new ThreadPool();
        }
        return singleton;
    }

    /**
     * 1、定长线程池（FixedThreadPool）
     * 特点：只有核心线程 & 不会被回收、线程数量固定、任务队列无大小限制（超出的线程任务会在队列中等待）
     * 应用场景：控制线程最大并发数
     *
     * @param command Runnable
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
     * 2、定时线程池（ScheduledThreadPool ）
     * 特点：核心线程数量固定、非核心线程数量无限制（闲置时马上回收）
     * 应用场景：执行定时 / 周期性 任务
     *
     * @param command      Runnable
     * @param initialDelay 初始化延迟时间
     * @param period       执行周期
     * @param unit         时间单位
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
     * 3、可缓存线程池（CachedThreadPool）
     * 特点：只有非核心线程、线程数量不固定（可无限大）、灵活回收空闲线程（具备超时机制，全部回收时几乎不占系统资源）、新建线程（无线程可用时）
     * 任何线程任务到来都会立刻执行，不需要等待
     * 应用场景：执行大量、耗时少的线程任务
     *
     * @param command Runnable
     */
    public void submitCachedThreadPool(Runnable command) {
        cachedThreadPool.execute(command);
    }

    public void shutdownCachedThreadPool() {
        if (cachedThreadPool != null) {
            cachedThreadPool.shutdown();
        }
    }

    /**
     * 4、单线程化线程池（SingleThreadExecutor）
     * 特点：只有一个核心线程（保证所有任务按照指定顺序在一个线程中执行，不需要处理线程同步的问题）
     * 应用场景：不适合并发但可能引起IO阻塞性及影响UI线程响应的操作，如数据库操作，文件操作等
     *
     * @param command Runnable
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