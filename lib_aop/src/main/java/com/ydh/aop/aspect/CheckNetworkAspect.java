//package com.ydh.aop.aspect;
//
//import android.app.Activity;
//import android.content.Context;
//import android.support.v4.app.Fragment;
//import android.view.View;
//import android.widget.Toast;
//
//import com.ydh.aop.annotation.CheckNetwork;
//import com.ydh.aop.util.Utils;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//
///**
// * 检测有无网络的的注解
// * @author 13001
// */
//@Aspect
//public class CheckNetworkAspect {
//
//    /**
//     * 处理切面
//     */
//    @Around("execution(@com.ydh.aop.annotation.CheckNetwork  * *(..))")
//    public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        CheckNetwork annotation = signature.getMethod().getAnnotation(CheckNetwork.class);
//        if (annotation != null) {
//            Context context = getContext(joinPoint.getThis());
//            if (Utils.isNetworkAvailable(context)) {
//                Toast.makeText(context, "当前网络正常", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "此时没有网络连接", Toast.LENGTH_SHORT).show();
//            }
//            return joinPoint.proceed();
//        }
//        return null;
//    }
//
//    /**
//     * 通过对象获取上下文
//     */
//    private Context getContext(Object object) {
//        if (object instanceof Activity) {
//            return (Activity) object;
//        } else if (object instanceof Fragment) {
//            Fragment fragment = (Fragment) object;
//            return fragment.getActivity();
//        } else if (object instanceof android.app.Fragment) {
//            android.app.Fragment fragment = (android.app.Fragment) object;
//            return fragment.getActivity();
//        } else if (object instanceof View) {
//            View view = (View) object;
//            return view.getContext();
//        }
//        return null;
//    }
//}
