package com.ydh.info.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.ImageView;

import com.tsign.terminal.util.NetworkStateUtils;

import static com.tsign.terminal.util.NetworkStateUtils.NETWORK_TYPE_2G;
import static com.tsign.terminal.util.NetworkStateUtils.NETWORK_TYPE_3G;
import static com.tsign.terminal.util.NetworkStateUtils.NETWORK_TYPE_4G;
import static com.tsign.terminal.util.NetworkStateUtils.networkConnected;

/**
 * 检测网络状态
 *
 * @author amoslv
 * @date 2019/03/16
 */
public class NetStateReceiver extends BroadcastReceiver {

    private ImageView ivWifi;
    private Context mCxt;

    public NetStateReceiver(Context context, ImageView ivWifi ) {
        this.mCxt = context;
        this.ivWifi = ivWifi;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null) {
            return;
        }
        switch (intent.getAction()) {
            case WifiManager.RSSI_CHANGED_ACTION:
                if (networkConnected(mCxt)) {
                    ivWifi.setImageLevel(getStrength(context));
                } else {
                    ivWifi.setImageLevel(10);
                }
                break;
            case WifiManager.NETWORK_STATE_CHANGED_ACTION:
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                    //如果断开连接
                    ivWifi.setImageLevel(0);
                }
                break;
            case WifiManager.WIFI_STATE_CHANGED_ACTION:
                //WIFI开关
                int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                        WifiManager.WIFI_STATE_DISABLED);
                if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
                    // 如果关闭
                    ivWifi.setImageLevel(0);
                }
                break;
            default:
                //default是 com.zbb.AliveService
                break;
        }

        boolean start = true;
        if (NetworkStateUtils.ethrnetConnected(mCxt)) {
            ivWifi.setImageLevel(105);
        } else if (NetworkStateUtils.getNetWorkType(mCxt) == NETWORK_TYPE_2G) {
            ivWifi.setImageLevel(102);
        } else if (NetworkStateUtils.getNetWorkType(mCxt) == NETWORK_TYPE_3G) {
            ivWifi.setImageLevel(103);
        } else if (NetworkStateUtils.getNetWorkType(mCxt) == NETWORK_TYPE_4G) {
            ivWifi.setImageLevel(104);
        } else {
            start = NetworkStateUtils.networkConnected(mCxt);
            if (!start) {
                ivWifi.setImageLevel(100);
            }
        }
    }

    private int getStrength(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().
                getSystemService(Context.WIFI_SERVICE);
        int strength = 0;
        if (wifiManager == null) {
            return strength;
        }
        WifiInfo info = wifiManager.getConnectionInfo();
        if (info.getBSSID() != null) {
            strength = WifiManager.calculateSignalLevel(info.getRssi(), 5);
            return strength;
        } else {
            return strength;
        }
    }

}
