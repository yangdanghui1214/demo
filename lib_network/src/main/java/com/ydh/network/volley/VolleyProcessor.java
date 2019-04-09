package com.ydh.network.volley;

import android.content.Context;

import com.ydh.network.call.ICallback;
import com.ydh.network.processor.IHttpProcessor;

import java.util.Map;

/**
 * volley加载框架 是谷歌的网络加载框架 2015年停更
 *
 * @author 13001
 */
public class VolleyProcessor implements IHttpProcessor {
//    private static RequestQueue mQueue=null;

    public VolleyProcessor(Context context) {
//        mQueue=Volley.newRequestQueue(context);

    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback callbask) {

    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callnack) {

    }


}
