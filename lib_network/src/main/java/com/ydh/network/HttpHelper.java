package com.ydh.network;

import com.ydh.network.call.ICallback;
import com.ydh.network.processor.IHttpProcessor;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.internal.Internal;

/**
 * @author 13001
 */
public class HttpHelper implements IHttpProcessor {

    private static IHttpProcessor httpProcessor = null;
    private static Map<String, Object> map;
    private static HttpHelper httpHelper = null;

    private HttpHelper() {
        map = new HashMap<>();
    }

    public static HttpHelper obtain() {
        synchronized (HttpHelper.class) {
            if (httpHelper == null) {
                httpHelper = new HttpHelper();
            }
        }
        return httpHelper;
    }

    /**
     * 接口
     *
     * @param iHttpProcessor
     */
    public static void init(IHttpProcessor iHttpProcessor) {
        httpProcessor = iHttpProcessor;
    }

    @Override
    public void get(String url, ICallback callbask) {
        httpProcessor.get(url, callbask);
    }

    @Override
    public void get(String url, HashMap<String, String> params, ICallback callnack) {
        httpProcessor.get(url, params, callnack);
    }

    @Override
    public void post(String url, ICallback callbask) {
        httpProcessor.post(url, callbask);
    }

    @Override
    public void post(String url, HashMap<String, String> params, ICallback callbask) {
        httpProcessor.post(url, params, callbask);
    }

    /**
     * put 请求
     *
     * @param url
     * @param callback
     */
    @Override
    public void put(String url, ICallback callback) {
        httpProcessor.put(url, callback);
    }

    @Override
    public void put(String url, HashMap<String, String> params, ICallback callback) {
        httpProcessor.put(url, params, callback);
    }

    /**
     * del 请求
     *
     * @param url
     * @param callback
     */
    @Override
    public void del(String url, ICallback callback) {
        httpProcessor.del(url, callback);
    }

    @Override
    public void del(String url, HashMap<String, String> params, ICallback callback) {
        httpProcessor.del(url, params, callback);
    }
}