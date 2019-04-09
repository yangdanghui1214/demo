package com.ydh.lib_retrofit2.hickey;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * @author 13001
 */
public class HttpTask<T> implements Runnable {

    /**
     * @param url
     * @param requestData
     * @param request
     * @param callBackLintener
     */
    public HttpTask(String url,T requestData ,IHttpRequest request,CallBackLintener callBackLintener){
        request.setUrl(url);
        request.setListener(callBackLintener);
        String content = new Gson().toJson(requestData);
        try {
            request.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

    }
}
