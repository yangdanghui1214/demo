package com.ydh.network;

import android.text.TextUtils;
import android.util.Patterns;

import com.ydh.network.util.WebAddress;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.schedulers.Schedulers;

public class AgendaDaoTest {


    @Test  //此方法为自行测试最简单的ping命令方法
    public void test1() throws Exception {
        String ip = "192.168.0.233";
        boolean a = AgendaDaoTest.checkIpStatus(ip);
        System.out.println(ip + "- -" + a);

    }

    public static boolean checkIpStatus(String ipAddress) {
        boolean reachable = false;
        try {
            reachable = InetAddress.getByName(ipAddress).isReachable(3000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reachable;
    }

    @Test
    public void test2() throws Exception {
        String ipAddress = "192.168.0.232";
        ping(ipAddress);
        ping02(ipAddress);
        System.out.println(ping(ipAddress, 5, 5000));

        String searchContent = "https://api.tsignsv.cn";

        WebAddress webAddress = new WebAddress(searchContent);
        System.out.println(webAddress.toString());

        System.out.println(checkIfUrlExists(searchContent));

//        if (Patterns.WEB_URL.matcher(searchContent).matches()) {
//            System.out.println(true);
//        } else {
//            System.out.println(false);
//        }

        System.out.println("校验ip是否合法 ： " + isIPLegal("http://127.0.0.1"));
    }

    @Test
    public void test03() throws InterruptedException {

        System.out.println("校验url是否合法 ： " + checkIfUrlExists("https://api.tsignsv.cn"));
        ping("47.100.223.89");
        System.out.println("校验oss是否合法 ： " + checkIfUrlExists("http://oss.tsignsv.cn/AIGT/t/"));
        System.out.println("校验ip是否合法 ： " + isReachableByTcp("47.100.223.89",7777,3000));


        Thread.sleep(4000);

    }

    public static boolean isReachableByTcp(String host, int port, int timeout) {
        try {
            Socket socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            socket.connect(socketAddress, timeout);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

//以下为java实现ping命令的三种不同方法

    /**
     * @param ipAddress
     * @return
     */
    public static void ping(String ipAddress) {
        Observable.create((Observable.OnSubscribe<Long>) subscriber -> {
            int timeOut = 3000;  //超时应该在3钞以上
            boolean status = false;
            try {
                status = InetAddress.getByName(ipAddress).isReachable(timeOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String str = status ? "合法" : "不合法";
            System.out.println("ip地址：" + str);
        }).subscribeOn(Schedulers.io()).subscribe();
        // 当返回值是true时，说明host是可用的，false则不可。
    }

    /**
     * @param ipAddress
     * @throws Exception
     */
    public static void ping02(String ipAddress) throws Exception {
        String line = null;
        try {
            Process pro = Runtime.getRuntime().exec("ping " + ipAddress);
            BufferedReader buf = new BufferedReader(new InputStreamReader(
                    pro.getInputStream()));
            while ((line = buf.readLine()) != null)
                System.out.println(line);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param ipAddress
     * @param pingTimes
     * @param timeOut
     * @return
     */
    public static boolean ping(String ipAddress, int pingTimes, int timeOut) {
        BufferedReader in = null;
        Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
        try {   // 执行命令并获取输出
            System.out.println(pingCommand);
            Process p = r.exec(pingCommand);
            if (p == null) {
                return false;
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
// 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            int connectedCount = 0;
            String line = null;
            while ((line = in.readLine()) != null) {
                connectedCount += getCheckResult(line);
            }   // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
            return connectedCount == pingTimes;
        } catch (Exception ex) {
            ex.printStackTrace();   // 出现异常则返回假
            return false;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }

    static boolean URL_exists = true;

    /**
     * @param URLName
     * @return
     */
    static boolean checkIfUrlExists(final String URLName) {
        URL_exists = false;
        try {
            //设置此类是否应该自动执行 HTTP 重定向（响应代码为 3xx 的请求）。
            HttpURLConnection.setFollowRedirects(false);
            //到 URL 所引用的远程对象的连接
            HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
            /* 设置 URL 请求的方法， GET POST HEAD OPTIONS PUT DELETE TRACE 以上方法之一是合法的，具体取决于协议的限制。*/
            con.setRequestMethod("GET");
            //从 HTTP 响应消息获取状态码
            // LogUtil.e("ryan","head "+con.getResponseCode());
            URL_exists = con.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return URL_exists;
    }

    /**
     * 获取url对应的域名
     *
     * @param url
     * @return
     */
    public String getDomain(String url) {
        String result = "";
        int j = 0, startIndex = 0, endIndex = 0;
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '/') {
                j++;
                if (j == 2)
                    startIndex = i;
                else if (j == 3)
                    endIndex = i;
            }

        }
        result = url.substring(startIndex + 1, endIndex);
        return result;
    }

    public static boolean isIPLegal(String ipStr) {
        if (ipStr == null || ipStr.equals("")) {
            return false;
        }

        Pattern pattern = Pattern.compile("^((http|https)://)((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$");
        Matcher matcher = pattern.matcher(ipStr);
        return matcher.find();
    }
}
