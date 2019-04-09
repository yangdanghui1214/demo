//package com.ydh.aop.aspect;
//
//import android.util.Log;
//
//import com.ydh.aop.annotation.SingleClick;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//
//import java.util.Calendar;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * 重复判断处理
// * @author 13001
// */
//@Aspect
//public class SingleClickAspect {
//    private Map<Integer, Long> map = new ConcurrentHashMap<>();
//
//    /**
//     * 在连接点进行方法替换
//     *
//     * @param joinPoint
//     * @param singleClick
//     * @throws Throwable
//     */
//    @Around("execution(@com.ydh.aop.annotation.SingleClick * *(..)) && @annotation(singleClick)")
////    @Around("execution(@com.ydh.aop.annotation.SingleClick * *(..))")
//    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, SingleClick singleClick) throws Throwable {
//        if (singleClick.request() == 0) {
//            return;
//        }
//        Log.e("zxy", "aroundJoinPoint: 开始执行" );
//        if (map.containsKey(singleClick.request())) {
//            Log.e("zxy", "aroundJoinPoint: 复点击" );
//            long currentTime = Calendar.getInstance().getTimeInMillis();
//            //过滤掉600毫秒内的连续点击
//            long l = map.get(singleClick.request()) == null ? 0L : map.get(singleClick.request());
//            if (currentTime - l > singleClick.timeout()) {
//                Log.e("zxy", "aroundJoinPoint: 有效点击" );
//                map.put(singleClick.request(), currentTime);
//                joinPoint.proceed();//执行原方法
//            }
//            Log.e("zxy", "aroundJoinPoint: 重复点击" );
//        } else {
//            Log.e("zxy", "aroundJoinPoint: 初次点击" );
//            map.put(singleClick.request(), 0L);
//            joinPoint.proceed();//执行原方法
//        }
//    }
//
//}
