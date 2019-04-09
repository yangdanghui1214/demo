package com.ydh.lib_retrofit2.util;

import com.ydh.lib_retrofit2.hickey.CallBack;
import com.ydh.lib_retrofit2.hickey.IHttp;

import java.util.HashMap;

import rx.Observable;

/**
 * okhttp 请求网络数据
 * @author 13001
 */
public class OkHttpUtil implements IHttp {

    static OkHttpUtil okHttpUtil = null;

    public static OkHttpUtil getInstance() {
        if (okHttpUtil == null) {
            okHttpUtil = new OkHttpUtil();
        }
        return okHttpUtil;
    }

    @Override
    public <T> Observable<T> getCustom(String url) {
        return null;
    }

    @Override
    public <T> Observable<T> getCustom(String url, HashMap<String, String> map) {
        return null;
    }

    @Override
    public <T> Observable<T> postCustom(String url) {
        return null;
    }

    @Override
    public <T> Observable<T> postCustom(String url, HashMap<String, String> map) {
        return null;
    }

    @Override
    public <T> void get(String url, CallBack<T> callBack) {

    }

    @Override
    public <T> void get(String url, HashMap<String, String> map, CallBack<T> callBack) {

    }

    @Override
    public <T> void post(String url, CallBack<T> callBack) {

    }

    @Override
    public <T> void post(String url, HashMap<String, String> map, CallBack<T> callBack) {

    }
}
