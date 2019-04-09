package com.ydh.network.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingUtil {

    public static boolean ping(String ipAddress) {
        //超时时间应该在3钞以上
        int timeOut = 3000;
        // 当返回值是true时，说明host是可用的，false则不可。
        try {
            return InetAddress.getByName(ipAddress).isReachable(timeOut);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void ping02(String ipAddress) {
        String line = null;
        try {
            Process pro = Runtime.getRuntime().exec("ping " + ipAddress);
            BufferedReader buf = new BufferedReader(new InputStreamReader(
                    pro.getInputStream(), "GBK"));
            while ((line = buf.readLine()) != null) {
                //line = new String(line.getBytes("ISO-8859-1"),"UTF-8");
                //line = new String(line.getBytes("UTF-8"),"GBK");
                //line = new String(line.getBytes("ISO-8859-1"),"utf-8");
                //line = new String(line.getBytes("ISO-8859-1"),"GBK");
                //line = new String(line.getBytes("gb2312"),"GBK");
                //line = new String(line.getBytes("gb2312"),"UTF-8");
                //System.out.println(transcoding(line, "gbk") );
                Log.e("zxy", line);

            }
        } catch (Exception ex) {
            Log.e("zxy", ex.getMessage());
        }
    }

}
