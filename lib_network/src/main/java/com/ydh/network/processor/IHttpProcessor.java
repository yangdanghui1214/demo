package com.ydh.network.processor;

import com.ydh.network.call.ICallback;

import java.util.Map;

/**
 * @author 13001
 */
public interface IHttpProcessor {

    void post(String url, Map<String,Object> params, ICallback callbask);
    void get(String url , Map<String,Object> params, ICallback callnack);

}
