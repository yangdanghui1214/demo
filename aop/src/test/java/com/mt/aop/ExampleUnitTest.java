package com.mt.aop;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void test() {

        AnalyseDomain("api.tsignsv.com");
//        AnalyseDomain1("https://www.jianshu.com");

    }

    private void AnalyseDomain(String host) {
        String IPAddress = "";
        InetAddress ReturnStr = null;
        try {
            ReturnStr = java.net.InetAddress.getByName(host);
            IPAddress = ReturnStr.getHostAddress();
            System.out.println("有效");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("无效");
        }
    }

    private void AnalyseDomain1(String host) {

        String pattern = "^((http://)|(https://))?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(host);

        System.out.println(m.find());
        if(m.find()){
            //匹配结果
            System.out.println("=" + m.group());
        }
        //替换
        System.out.println(host.replaceAll(pattern, ""));

    }

}