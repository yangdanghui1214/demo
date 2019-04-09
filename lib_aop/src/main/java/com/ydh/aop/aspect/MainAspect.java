//package com.ydh.aop.aspect;
//
//import android.util.Log;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//
//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//
///**
// * 用于处理子线程切换的操作
// *
// * @author 13001
// */
//@Aspect
//public class MainAspect {
//
//    /**
//     * 切面
//     * ProceedingJoinPoint
//     */
//    @Around("execution(@com.ydh.aop.annotation.MainThread void *(..))")
//    public void doAsyncMethod(ProceedingJoinPoint joinPoint) {
//
//        Observable.create(e -> {
//            long l = System.currentTimeMillis();
//            try {
//                //执行真实的方法
//                joinPoint.proceed();
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }
//            long l2 = System.currentTimeMillis();
//            Log.e("zxy", "doAsyncMethod: 执行时间  " + (l2 - l) + "ms");
//        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
//
//    }
//
//}
