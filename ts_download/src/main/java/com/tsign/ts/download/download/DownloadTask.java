package com.tsign.ts.download.download;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * APK 下载线程
 *
 * @author 13001
 */
public class DownloadTask extends AsyncTask<String, Integer, String> {

    private Context context;
    private DownloadInterface downloadInterface;
    private PowerManager.WakeLock mWakeLock;
    public static String fileName = "/sdcard/aijk.apk";

    public DownloadTask(Context context, DownloadInterface downloadInterface) {
        this.context = context;
        this.downloadInterface = downloadInterface;
    }

    //onPreExecute(),在execute(Params... params)方法被调用后立即执行，执行在ui线程，
    // 一般用来在执行后台任务前会UI做一些标记
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // take CPU lock to prevent CPU from going off if the user
        // presses the power button during download
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                getClass().getName());
        mWakeLock.acquire();
        downloadInterface.updateStart();
    }

    // doInBackground这个方法在onPreExecute()完成后立即执行，
    // 用于执行较为耗时的操作，
    // 此方法接受输入参数
    // 和返回计算结果（返回的计算结果将作为参数在任务完成是传递到onPostExecute(Result result)中），
    // 在执行过程中可以调用publishProgress(Progress... values)来更新进度信息
    //后台任务的代码块
    @Override
    protected String doInBackground(String... url) {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;

        File file = new File(fileName);
        if (file.exists() && file.isFile() || file.exists() && file.isDirectory()) {
            file.delete();
        }

        try {
            URL urll = new URL(url[0]);
            Log.d("upgrade", "url1:" + urll + "////url:" + url);
            connection = (HttpURLConnection) urll.openConnection();
            connection.connect();
            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage();
            }
            // this will be useful to display download percentage
            // might be -1: server did not report the length
            int fileLength = connection.getContentLength();
            // download the file
            input = connection.getInputStream();
            output = new FileOutputStream(fileName);
            byte[] data = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                if (isCancelled()) {
                    input.close();
                    return null;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) {
                    //在调用这个方法后，执行onProgressUpdate(Progress... values)，
                    //运行在主线程，用来更新pregressbar

                    DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                    String num = df.format((float) total * 100 / fileLength);//返回的是String类型
                    String[] split = num.replace(".", "-").split("-");
                    publishProgress(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                }
                output.write(data, 0, count);
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (IOException ignored) {
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    //onProgressUpdate(Progress... values),
    // 执行在UI线程，在调用publishProgress(Progress... values)时，此方法被执行。
    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        // if we get here, length is known, now set indeterminate to false\
        downloadInterface.updateProgress(progress[0], progress[1]);
    }

    //onPostExecute(Result result),
    // 执行在UI线程，当后台操作结束时，此方法将会被调用。
    @Override
    protected void onPostExecute(String result) {
        mWakeLock.release();
        downloadInterface.updateDismiss();
        if (result != null) {
            Log.e("result", "Download error: " + result);
            downloadInterface.updateError(UpdateType.CODE_1003);
            return;
        }
        downloadInterface.updateInstall();
    }

}
