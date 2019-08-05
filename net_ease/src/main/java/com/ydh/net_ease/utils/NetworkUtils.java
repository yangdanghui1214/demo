package com.ydh.net_ease.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ydh.net_ease.type.NetType;

import static com.ydh.net_ease.manager.NetworkManager.getDefault;

/**
 * @author 13001
 */
public class NetworkUtils {

    /**
     * 检测网络是否可用
     */
    @SuppressLint("MissingPermission")
    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getDefault().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        // 返回所有的网络信息
        NetworkInfo[] info = manager.getAllNetworkInfo();
        if (info == null || info.length < 1) {
            return false;
        }
        for (NetworkInfo networkInfo : info) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 读取当前的网络类型
     * -1 :没有网络
     * 1  :wifi
     * 2  :wap
     * 3  :net
     */
    @SuppressLint("MissingPermission")
    public static NetType getNetType() {
        ConnectivityManager manager = (ConnectivityManager) getDefault().getApplication()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return NetType.NONE;
        }
        // 获取当前的网络信息
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            return NetType.NONE;
        }
        int nType = info.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            if (info.getExtraInfo().toLowerCase().equals("cmnet")) {
                return NetType.CMNET;
            } else {
                return NetType.CMWAP;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }
        return NetType.NONE;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Context context, int requestCode) {
        Intent intent = new Intent("/");
        ComponentName componentName = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(componentName);
        intent.setAction("android.intent.action.VIEW");
        ((Activity) context).startActivityForResult(intent, requestCode);
    }
}
