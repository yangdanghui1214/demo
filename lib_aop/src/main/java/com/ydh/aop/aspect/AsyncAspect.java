package com.ydh.aop.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 用于处理子线程切换的操作
 *
 * @author 13001
 */
@Aspect
public class AsyncAspect {

//    @Pointcut("execution(@com.ydh.aop.annotation.Async void *(..))")
//    public void asyncMethod() {
//
//    }

    /**
     * 切面
     * ProceedingJoinPoint
     */
//    @Around("asyncMethod()")
    @Around("execution(@com.ydh.aop.annotation.Async void *(..))")
    public void doAsyncMethod(ProceedingJoinPoint joinPoint) {

        Observable.create(e -> {
            long l = System.currentTimeMillis();
            try {
                //执行真实的方法
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            long l2 = System.currentTimeMillis();
            Log.e("zxy", "doAsyncMethod: 执行时间  " + (l2 - l) + "ms" + Thread.currentThread().toString());
        }).subscribeOn(Schedulers.io()).subscribe();

    }

}
