//package com.ydh.aop.aspect;
//
//import android.text.TextUtils;
//import android.util.Log;
//
//
//import com.ydh.aop.annotation.Logger;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.aspectj.lang.reflect.SourceLocation;
//
//import java.lang.reflect.Method;
//
///**
// * 用于处理子线程切换的操作
// *
// * @author 13001
// */
//@Aspect
//public class LoggerAspect {
//
//    /**
//     * 切面
//     * 本质是反射
//     * ProceedingJoinPoint
//     */
//    @Around("execution(@com.ydh.aop.annotation.Logger * *(..))")
//    public Object doLoggerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        //计算代码耗时
//        long start = System.currentTimeMillis();
//        Object result = joinPoint.proceed();
//
//        //得到方法签名
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//
//        Method method=signature.getMethod();
//        Logger logger=method.getAnnotation(Logger.class);
//
//        //根据方法签名获取参数 type name = value
//        Class[] parameterTypes = signature.getParameterTypes();
//        String[] parameterNames = signature.getParameterNames();
//        Object[] args = joinPoint.getArgs();
//        StringBuilder builder = new StringBuilder();
//
//        for (int i = 0; i < parameterTypes.length; i++) {
//            builder.append(", ");
//            builder.append(parameterTypes[i].getSimpleName());
//            builder.append(" ");
//            builder.append(parameterNames[i]);
//            builder.append(" = ");
//            builder.append(args[i]);
//        }
//
//        // 根据方法获取返回类型
//        String type = signature.getReturnType().getSimpleName();
//
//        //获取源码文件位置信息 选择日志文件可跳转格式的字符串
//        SourceLocation location = joinPoint.getSourceLocation();
//        int lineNumber = location.getLine();
//        String fileName = location.getFileName();
//        String fullClassName = signature.getDeclaringTypeName();
//        String methodName = signature.getMethod().getName();
//
//        long end = System.currentTimeMillis();
//
//        StringBuilder content = new StringBuilder();
//        content.append("\n╔═══════════════════════════════════════════════════════════════════════════════════════");
//        content.append("\n");
//        content.append("║ 当前线程 ");
//        content.append(Thread.currentThread().getName());
//        content.append("\n");
//        content.append("║------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//        content.append("\n");
//        content.append("║ 函数：");
//        content.append(fullClassName);
//        content.append(".");
//        content.append(methodName);
//        content.append("(");
//        content.append(fileName);
//        content.append(": ");
//        content.append(lineNumber);
//        content.append(")\n");
//        content.append("║ 参数：");
//        content.append(builder);
//        content.append("\n");
//        content.append("║------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//        content.append("\n");
//        content.append("║ 返回：");
//        content.append(type);
//        content.append(" ");
//        content.append(TextUtils.equals(type.toLowerCase(), "void") ? "" : result);
//        content.append("\n");
//        content.append("║ 耗时：");
//        content.append(end - start);
//        content.append("ms\n");
//        content.append("╚═══════════════════════════════════════════════════════════════════════════════════════");
//
//        Log.println(logger.value(),signature.getDeclaringType().getSimpleName(),content.toString());
//
//        return result;
//    }
//
//}
