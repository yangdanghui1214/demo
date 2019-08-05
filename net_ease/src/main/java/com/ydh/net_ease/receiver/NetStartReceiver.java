package com.ydh.net_ease.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ydh.net_ease.listener.NetChangeObserve;
import com.ydh.net_ease.type.NetType;
import com.ydh.net_ease.utils.Constants;
import com.ydh.net_ease.utils.NetworkUtils;

public class NetStartReceiver extends BroadcastReceiver {

    NetType netType;
    private NetChangeObserve observe = null;

    public NetStartReceiver() {
        netType = NetType.NONE;
    }

    public void setListener(NetChangeObserve observe) {
        this.observe = observe;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        // 处理广播事件  不区分大小写
        if (intent.getAction().equalsIgnoreCase(Constants.CONNECTIVITY_CHANGE)){
            Log.e(Constants.LOG_TAG, "onReceive: 网络发生变化" );
            // 获取当前网络的类型
            netType= NetworkUtils.getNetType();
            // 判断有无网络
            if (NetworkUtils.isNetworkAvailable()){
                Log.e(Constants.LOG_TAG, "onReceive: 网络连接成功" );
                if (observe==null){
                    return;
                }
                observe.onConnect(netType);
            }else {
                Log.e(Constants.LOG_TAG, "onReceive: 没有网络" );
                observe.onDisConnect();
            }
        }

    }

}
