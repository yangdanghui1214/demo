package com.ydh.network.processor;

import com.ydh.network.call.ICallback;

import java.util.HashMap;

/**
 * @author 13001
 */
public interface IHttpProcessor {

    void get(String url, ICallback callback);

    void get(String url, HashMap<String, String> params, ICallback callback);

    void post(String url, ICallback callback);

    void post(String url, HashMap<String, String> params, ICallback callback);

    void put(String url, ICallback callback);

    void put(String url, HashMap<String, String> params, ICallback callback);

    void del(String url, ICallback callback);

    void del(String url, HashMap<String, String> params, ICallback callback);

}
