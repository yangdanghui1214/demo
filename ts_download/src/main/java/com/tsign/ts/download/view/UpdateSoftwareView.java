package com.tsign.ts.download.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tsign.ts.download.R;
import com.tsign.ts.download.download.DownloadInterface;
import com.tsign.ts.download.download.DownloadTask;
import com.tsign.ts.download.download.UpdateType;

import java.io.File;

/**
 * APK 下载的UI界面
 *
 * @author 13001
 */
public class UpdateSoftwareView extends LinearLayout implements DownloadInterface {

    private String TAG = "UpdateSoftwareView";

    private Context mCxt;
    private TextView tvTitle;
    private TextView tvDownloadApk;
    private ProgressBar pbDownloadApk;

    private DownloadTask downloadTask = null;
    private String url = "";
    private int number = 0;

    /**
     * apk安装时的type
     */
    private final String TYPE_APK_INSTALL = "application/vnd.android.package-archive";

    public UpdateSoftwareView(Context context) {
        super(context);
        init(context, null);
    }

    public UpdateSoftwareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        this.mCxt = context;
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_break_point_file, this);
        tvTitle = findViewById(R.id.tv_title);
        tvDownloadApk = findViewById(R.id.tv_download_apk);
        pbDownloadApk = findViewById(R.id.pb_download_apk);
    }

    /**
     * 设置标题
     *
     * @param str
     */
    public void setTitle(String str) {
        tvTitle.setText(str);
    }

    /**
     * 启动异步下载线程
     */
    public void downloadApk(String url) {
        if (downloadTask != null) {
            return;
        }
        number = 0;
        DownloadTask.fileName = "/sdcard/" + url.substring(url.lastIndexOf("/") + 1);
        // 所以我们应该在这两个方法中更新progress。
        downloadTask = new DownloadTask(mCxt, this);
        //execute 执行一个异步任务，通过这个方法触发异步任务的执行。这个方法要在主线程调用。
        downloadTask.execute(url);
    }

    /**
     * 取消异步线程下载
     */
    public void downloadCancel() {
        if (downloadTask == null) {
            return;
        }
        downloadTask.cancel(true);
        downloadTask = null;
    }

    /**
     * 软件下载
     */
    @Override
    public void updateStart() {
        setVisibility(VISIBLE);
    }

    @Override
    public void updateProgress(int progress, int decimal) {
        pbDownloadApk.setMax(100);
        pbDownloadApk.setProgress(progress);
        String person = progress > 9 ? "" + progress : "0" + progress;
        String decimalS = decimal > 9 ? "" + decimal : "0" + decimal;
        tvDownloadApk.setText(person + "." + decimalS + "%");
    }

    @Override
    public void updateError(UpdateType type) {
        number += 10;
        if (number >= 60) {
            setTitle("网络异常,请检查设备网络连接");
            return;
        }
        setTitle("网络异常，10s后后尝试再次连接");
        pbDownloadApk.postDelayed(runnable, 10000);
    }

    @Override
    public void updateDismiss() {
        downloadCancel();
    }

    @Override
    public void updateInstall() {
        number = 0;
        setVisibility(GONE);

        //这里主要是做下载后自动安装的处理
        File file = new File(DownloadTask.fileName);
        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        installIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mCxt.startActivity(installIntent);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            downloadApk(url);
        }
    };

}

