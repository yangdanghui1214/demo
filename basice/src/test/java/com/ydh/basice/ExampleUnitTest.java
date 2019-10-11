package com.ydh.basice;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

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

    @Test
    public void text(){
        try {
            System.out.println(java.net.URLDecoder.decode("-","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println(" 转义失败");
            e.printStackTrace();
        }
    }

}