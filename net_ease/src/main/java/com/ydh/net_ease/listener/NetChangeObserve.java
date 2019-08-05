package com.ydh.net_ease.listener;

import com.ydh.net_ease.type.NetType;

/**
 * 接口网络连接状态接口返回工具类
 *
 * @author 13001
 */
public interface NetChangeObserve {

    /**
     * 网络连接超时
     */
    void onConnect(NetType netType);

    /**
     *  没有网络
     */
    void onDisConnect();

}
