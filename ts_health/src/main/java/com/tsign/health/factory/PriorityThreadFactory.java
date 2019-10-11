package com.tsign.health.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A thread factory that create threads with a given thread priority
 *
 * @author haru
 * @version 1.0
 */
public class PriorityThreadFactory implements ThreadFactory {
    private final String mName;
    private final int mPriority;
    private final AtomicInteger mNumber = new AtomicInteger();

    public PriorityThreadFactory(String name, int priority) {
        // 线程池的名称
        mName = name;
        //线程池的优先级
        mPriority = priority;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, mName + "-" + mNumber.getAndIncrement()) {
            @Override
            public void run() {
                // 设置线程的优先级  
                android.os.Process.setThreadPriority(mPriority);
                super.run();
            }
        };
    }
}  