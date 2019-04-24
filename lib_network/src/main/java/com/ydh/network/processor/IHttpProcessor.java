package com.ydh.network.processor;

import com.ydh.network.call.ICallback;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 13001
 */
public interface IHttpProcessor {

    void post(String url, HashMap<String,String> params, ICallback callbask);
    void get(String url , HashMap<String,String> params, ICallback callnack);

}
