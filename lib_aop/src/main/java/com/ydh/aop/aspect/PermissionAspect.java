//package com.ydh.aop.aspect;
//
//import android.app.Fragment;
//import android.content.Context;
//
//import com.ydh.aop.annotation.PermissionCanceled;
//import com.ydh.aop.annotation.PermissionDenied;
//import com.ydh.aop.annotation.PermissionNeed;
//import com.ydh.aop.aty.PermissionRequestActivity;
//import com.ydh.aop.bean.CancelBean;
//import com.ydh.aop.bean.DenyBean;
//import com.ydh.aop.interf.IPermission;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.List;
//
//@Aspect
//public class PermissionAspect {
//
//    private static final String PERMISSION_REQUEST_POINTCUT =
//            "execution(@com.ydh.aop.annotation.NeedPermission * *(..))";
//
//    @Pointcut(PERMISSION_REQUEST_POINTCUT + " && @annotation(needPermission)")
//    public void requestPermissionMethod(PermissionNeed needPermission) {
//    }
//
//    @Around("requestPermissionMethod(needPermission)")
//    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, PermissionNeed needPermission) {
//
//        Context context = null;
//        final Object object = joinPoint.getThis();
//        if (object instanceof Context) {
//            context = (Context) object;
//        } else if (object instanceof Fragment) {
//            context = ((Fragment) object).getActivity();
//        } else if (object instanceof android.support.v4.app.Fragment) {
//            context = ((android.support.v4.app.Fragment) object).getActivity();
//        }
//        if (context == null || needPermission == null) {
//            return;
//        }
//
//        PermissionRequestActivity.PermissionRequest(context, needPermission.value(),
//                needPermission.requestCode(), new IPermission() {
//                    @Override
//                    public void permissionGranted() {
//                        try {
//                            joinPoint.proceed();
//                        } catch (Throwable throwable) {
//                            throwable.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void permissionDenied(int requestCode, List<String> denyList) {
//                        Class<?> cls = object.getClass();
//                        Method[] methods = cls.getDeclaredMethods();
//                        if (methods == null || methods.length == 0) {
//                            return;
//                        }
//                        for (Method method : methods) {
//                            //过滤不含自定义注解PermissionDenied的方法
//                            boolean isHasAnnotation = method.isAnnotationPresent(PermissionDenied.class);
//                            if (isHasAnnotation) {
//                                method.setAccessible(true);
//                                //获取方法类型
//                                Class<?>[] types = method.getParameterTypes();
//                                if (types == null || types.length != 1) {
//                                    return;
//                                }
//                                //获取方法上的注解
//                                PermissionDenied aInfo = method.getAnnotation(PermissionDenied.class);
//                                if (aInfo == null) {
//                                    return;
//                                }
//                                //解析注解上对应的信息
//                                DenyBean bean = new DenyBean();
//                                bean.setRequestCode(requestCode);
//                                bean.setDenyList(denyList);
//                                try {
//                                    method.invoke(object, bean);
//                                } catch (IllegalAccessException e) {
//                                    e.printStackTrace();
//                                } catch (InvocationTargetException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void permissionCanceled(int requestCode) {
//                        Class<?> cls = object.getClass();
//                        Method[] methods = cls.getDeclaredMethods();
//                        if (methods == null || methods.length == 0) {
//                            return;
//                        }
//                        for (Method method : methods) {
//                            //过滤不含自定义注解PermissionCanceled的方法
//                            boolean isHasAnnotation = method.isAnnotationPresent(PermissionCanceled.class);
//                            if (isHasAnnotation) {
//                                method.setAccessible(true);
//                                //获取方法类型
//                                Class<?>[] types = method.getParameterTypes();
//                                if (types == null || types.length != 1) {
//                                    return;
//                                }
//                                //获取方法上的注解
//                                PermissionCanceled aInfo = method.getAnnotation(PermissionCanceled.class);
//                                if (aInfo == null) {
//                                    return;
//                                }
//                                //解析注解上对应的信息
//                                CancelBean bean = new CancelBean();
//                                bean.setRequestCode(requestCode);
//                                try {
//                                    method.invoke(object, bean);
//                                } catch (IllegalAccessException e) {
//                                    e.printStackTrace();
//                                } catch (InvocationTargetException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                });
//    }
//}
