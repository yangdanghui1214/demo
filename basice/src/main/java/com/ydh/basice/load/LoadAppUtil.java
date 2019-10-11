package com.ydh.basice.load;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * APP 下载工具类
 *
 * @author 13001
 */
public class LoadAppUtil {

    private static String path = Environment.getExternalStorageDirectory().toString() + "/shidoe";

    /**
     * 从服务器下载文件
     *
     * @param path     下载文件的地址
     * @param FileName 文件名字
     */
    public static void downLoad(final String path, final String FileName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setReadTimeout(5000);
                    con.setConnectTimeout(5000);
                    con.setRequestProperty("Charset", "UTF-8");
                    con.setRequestMethod("GET");

                    if (con.getResponseCode() == 200) {

                        InputStream is = con.getInputStream();//获取输入流
                        FileOutputStream fileOutputStream = null;//文件输出流

                        if (is != null) {
                            fileOutputStream = new FileOutputStream(createFile(FileName));//指定文件保存路径，代码看下一步
                            byte[] buf = new byte[1024];
                            int ch;
                            while ((ch = is.read(buf)) != -1) {
                                fileOutputStream.write(buf, 0, ch);//将获取到的流写入文件中
                            }
                        }

                        if (fileOutputStream != null) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 创建一个文件
     *
     * @param fileName 文件名
     * @return
     */
    private static File createFile(String fileName) {
        File file = new File(path);
        // 如果文件夹不存在就创建
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(path, fileName);
    }

}
