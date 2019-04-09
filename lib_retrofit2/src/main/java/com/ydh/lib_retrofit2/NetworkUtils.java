package com.ydh.lib_retrofit2;

import com.ydh.lib_retrofit2.hickey.CallBack;
import com.ydh.lib_retrofit2.hickey.IHttp;

import java.util.HashMap;

import rx.Observable;

/**
 * @author 13001
 */
public class NetworkUtils implements IHttp {

    private static NetworkUtils networkUtils = null;
    private static IHttp http;

    public static NetworkUtils getInstance(IHttp iHttp) {
        if (networkUtils == null) {
            networkUtils = new NetworkUtils();
            http = iHttp;
        }
        return networkUtils;
    }

    public static NetworkUtils getNetwork() {
        return networkUtils;
    }

    @Override
    public <T> Observable<T> getCustom(String url) {
        return http.getCustom(url);
    }

    @Override
    public <T> Observable<T> getCustom(String url, HashMap<String, String> map) {
        return http.getCustom(url,map);
    }

    @Override
    public <T> Observable<T> postCustom(String url) {
        return http.postCustom(url);
    }

    @Override
    public <T> Observable<T> postCustom(String url, HashMap<String, String> map) {
        return http.postCustom(url, map);
    }

    @Override
    public <T> void get(String url, CallBack<T> callBack) {
        http.get(url, callBack);
    }

    @Override
    public <T> void get(String url, HashMap<String, String> map, CallBack<T> callBack) {
        http.get(url, map, callBack);
    }

    @Override
    public <T> void post(String url, CallBack<T> callBack) {
        http.post(url, callBack);
    }

    @Override
    public <T> void post(String url, HashMap<String, String> map, CallBack<T> callBack) {
        http.post(url, map, callBack);
    }
}
