package com.ydh.animation;

import android.util.Log;

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
     *
     */
    @Test
    public void cc() {

        Object[] strings = new Object[]{"cc", "cc"};

        if (strings.getClass().isArray()) {
            System.out.println("cc: 数组");
            for (int i = 0; i < strings.length; i++) {
                System.out.println("cc: 数组: " + strings[i].toString());
            }
        }
    }
}