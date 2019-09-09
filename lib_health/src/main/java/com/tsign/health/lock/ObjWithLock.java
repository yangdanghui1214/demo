package com.tsign.health.lock;


import java.io.Serializable;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自带读写锁的对象.
 *
 * @author xhj
 */
public class ObjWithLock<T> implements Serializable {

    private static final long serialVersionUID = -3048283373239453901L;

    /**
     *
     */
    private T obj = null;

    /**
     *
     */
    private ReentrantReadWriteLock lock = null;

    /**
     * @param obj
     */
    public ObjWithLock(T obj) {
        this(obj, new ReentrantReadWriteLock());
    }

    /**
     * @param obj
     * @param lock
     */
    public ObjWithLock(T obj, ReentrantReadWriteLock lock) {
        super();
        this.obj = obj;
        this.lock = lock;
    }

    /**
     * @return
     */
    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    /**
     * @return
     * @author tanyaowu
     */
    public T getObj() {
        return obj;
    }

    /**
     * @param obj
     */
    public void setObj(T obj) {
        this.obj = obj;
    }

}
