package com.ydh.info;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * 断言的使用
     * 断言可以在开发和测试中使用，但不要在正式版本上使用
     * 1.当断言条件成立，程序可以正常运行
     * 2.当断言条件不成立，程序会报错  java.lang.AssertionError
     *
     * 语法1：assert expression;
     * //expression代表一个布尔类型的表达式，如果为真，就继续正常运行，如果为假，程序退出
     *
     * 语法2：assert expression1 : expression2;
     * //expression1是一个布尔表达式，expression2是一个基本类型或者Object类型，如果expression1为真，则程序忽略expression2继续运行；如果expression1为假，则运行expression2，然后退出程序。
     *
     */
    @Test
    public void ccAssert() {
        int a = 1, j = 3;
        a += j;
        assert a == j;
        System.out.println("我可以执行");
    }

}