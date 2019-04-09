package com.ydh.network;

import com.ydh.network.call.ICallback;
import com.ydh.network.processor.IHttpProcessor;

import java.util.HashMap;
import java.util.Map;

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
    public void post(String url, Map<String, Object> params, ICallback callbask) {
        httpProcessor.post(url, params, callbask);
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callnack) {
        httpProcessor.get(url, params, callnack);
    }
}