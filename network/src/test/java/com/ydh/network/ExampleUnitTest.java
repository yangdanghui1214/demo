package com.ydh.network;

import com.ydh.network.util.PingUtil;

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

    @Test
    public void text() {
//        boolean status = PingUtil.ping("http://checkout.tsignsv.cn/aiKindergartenTerminal/");
//        System.out.println("ping : " + (status ? "成功" : "失败"));

      PingUtil.ping02("https://checkout.tsignsv.cn/aiKindergartenTerminal/");


    }

}