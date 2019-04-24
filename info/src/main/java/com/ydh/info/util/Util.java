package com.ydh.info.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.DisplayMetrics;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author 13001
 */
public class Util {

    /**
     * @param context 上下文
     * @return 屏幕的分辨率
     */
    public static String getResolutionRatio(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int heigth = dm.heightPixels;
        int width = dm.widthPixels;
        return "屏幕的宽 ： "+width+"\n屏幕的高为 ： "+heigth;
    }

    /**
     * @param mCxt 上下文
     * @return 设备的Mac地址
     */
    @SuppressLint("HardwareIds")
    public static String getMac(Context mCxt) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
                Iterator var12 = all.iterator();

                while(var12.hasNext()) {
                    NetworkInterface nif = (NetworkInterface)var12.next();
                    if (nif.getName().equalsIgnoreCase("wlan0")) {
                        byte[] macBytes = nif.getHardwareAddress();
                        if (macBytes == null) {
                            return "";
                        }

                        StringBuilder res1 = new StringBuilder();
                        int var7 = macBytes.length;

                        for (byte b : macBytes) {
                            res1.append(String.format("%02X:", b));
                        }

                        if (res1.length() > 0) {
                            res1.deleteCharAt(res1.length() - 1);
                        }

                        return res1.toString();
                    }
                }
            } catch (Exception var10) {
                var10.printStackTrace();
            }

            return "02:00:00:00:00:00";
        } else {
            String macAddress = null;
            WifiManager wifiManager = (WifiManager)mCxt.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = null == wifiManager ? null : wifiManager.getConnectionInfo();
            assert wifiManager != null;
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
                wifiManager.setWifiEnabled(false);
            }

            if (null != info) {
                macAddress = info.getMacAddress();
            }

            return macAddress;
        }
    }

}
