package com.ydh.network.okHttp;

import com.ydh.network.call.ICallback;
import com.ydh.network.call.TsCallback;
import com.ydh.network.common.NetworkCommon;
import com.ydh.network.processor.IHttpProcessor;
import com.ydh.network.util.ParameterUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * okhttp
 *
 * @author 13001
 */
public class OkHttpProcessor implements IHttpProcessor {

    private static OkHttpProcessor okHttpProcessor = new OkHttpProcessor();

    static OkHttpClient client;

    private OkHttpProcessor() {
        if (client == null) {
            client = new OkHttpClient().newBuilder()
                    .connectTimeout(NetworkCommon.connectTimeout, TimeUnit.MILLISECONDS)
                    .writeTimeout(NetworkCommon.writeTimeOut, TimeUnit.MILLISECONDS)
                    .readTimeout(NetworkCommon.readTimeOut, TimeUnit.MILLISECONDS)
                    .build();
        }
    }

    public static OkHttpProcessor getInstance() {
        return okHttpProcessor;
    }

    /**
     * get 请求
     *
     * @param url
     * @param callback
     */
    @Override
    public void get(String url, ICallback callback) {

        Request request = new Request.Builder()
                .url(NetworkCommon.baseUrl + url)
                .get()
                .build();

        client.newCall(request).enqueue(new TsCallback(callback));
    }

    @Override
    public void get(String url, HashMap<String, String> params, ICallback callback) {
        Request request = new Request.Builder()
                .url(NetworkCommon.baseUrl + url + ParameterUtil.getParams(params))
                .get()
                .build();

        client.newCall(request).enqueue(new TsCallback(callback));
    }

    /**
     * post 请求
     *
     * @param url
     * @param callback
     */
    @Override
    public void post(String url, ICallback callback) {
        RequestBody body = appendBody(null);

        Request request = new Request.Builder()
                .url(NetworkCommon.baseUrl + url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new TsCallback(callback));
    }

    @Override
    public void post(String url, HashMap<String, String> params, final ICallback callback) {
        RequestBody body = appendBody(params);

        Request request = new Request.Builder()
                .url(NetworkCommon.baseUrl + url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new TsCallback(callback));
    }

    /**
     * put 请求
     *
     * @param url
     * @param callback
     */
    @Override
    public void put(String url, ICallback callback) {
        put(url, null, callback);
    }

    @Override
    public void put(String url, HashMap<String, String> params, ICallback callback) {
        RequestBody body = appendBody(params);

        Request request = new Request.Builder()
                .url(NetworkCommon.baseUrl + url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new TsCallback(callback));
    }

    /**
     * del 请求
     *
     * @param url
     * @param callback
     */
    @Override
    public void del(String url, ICallback callback) {
        del(url, null, callback);
    }

    @Override
    public void del(String url, HashMap<String, String> params, ICallback callback) {
        RequestBody body = appendBody(params);

        Request request = new Request.Builder()
                .url(NetworkCommon.baseUrl + url)
                .delete(body)
                .build();

        client.newCall(request).enqueue(new TsCallback(callback));
    }

    /**
     * 拼接请求参数
     *
     * @param map
     * @return
     */
    private RequestBody appendBody(Map<String, String> map) {

        FormBody.Builder body = new FormBody.Builder();

        if (map == null || map.isEmpty()) {
            return body.build();
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            body.add(entry.getKey(), entry.getValue());
        }

        return body.build();
    }
}
