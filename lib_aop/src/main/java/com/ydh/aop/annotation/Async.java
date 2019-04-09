package com.ydh.aop.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//让注解保留到哪一步
// RetentionPolicy.SOURCE 只在源文件中保留，编译成javac后就不丛在了
// RetentionPolicy.CLASS 只在源文件中保留，编译成javac后就不丛在了
// RetentionPolicy.RUNTIME 只在源文件中保留，编译成javac后就不丛在了，反射
//　@Target:注解的作用目标　　　　　　　　
//      @Target(ElementType.TYPE)   //接口、类、枚举、注解
//      @Target(ElementType.FIELD) //字段、枚举的常量
//      @Target(ElementType.METHOD) //方法
//      @Target(ElementType.PARAMETER) //方法参数
//      @Target(ElementType.CONSTRUCTOR)  //构造函数
//      @Target(ElementType.LOCAL_VARIABLE)//局部变量
//      @Target(ElementType.ANNOTATION_TYPE)//注解
//      @Target(ElementType.PACKAGE) ///包
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Async {

}
