package com.ydh.net_ease.manager;

import android.app.Application;
import android.content.IntentFilter;

import com.ydh.net_ease.listener.NetChangeObserve;
import com.ydh.net_ease.receiver.NetStartReceiver;
import com.ydh.net_ease.utils.Constants;

/**
 * 网络管理类
 *
 * @author 13001
 */
public class NetworkManager {

    //  volatile  轻量级的同步块
    private static volatile NetworkManager manager = null;
    private Application application = null;
    private NetStartReceiver receiver = null;


    public static NetworkManager getDefault() {
        if (manager == null) {
            synchronized (NetworkManager.class) {
                if (manager == null) {
                    manager = new NetworkManager();
                }
            }
        }
        return manager;
    }

    private NetworkManager() {
        receiver = new NetStartReceiver();
    }

    /**
     * 初始化方法
     *
     * @param application
     */
    public void init(Application application) {
        if (application == null) {
            return;
        }
        this.application = application;

        // 动态注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.CONNECTIVITY_CHANGE);
        application.registerReceiver(receiver, filter);

    }

    public Application getApplication() {
        if (application == null) {
            throw new RuntimeException("*******  init() 没有被初始化");
        }
        return application;
    }


    public void setListener(NetChangeObserve observe) {
        receiver.setListener(observe);
    }
}
