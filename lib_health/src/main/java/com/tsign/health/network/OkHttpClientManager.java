package com.tsign.health.network;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author xhj
 * @date 2018/11/14
 */

public class OkHttpClientManager {
    private static OkHttpClientManager okHttpClientManager;

    private OkHttpClient client;

    private OkHttpClientManager() {
        client = new OkHttpClient.Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .addInterceptor(code)
                .build();
    }

    public static OkHttpClientManager getInstance() {
        if (okHttpClientManager == null) {
            okHttpClientManager = new OkHttpClientManager();
        }
        return okHttpClientManager;
    }

    public OkHttpClient getClient() {
        return client;
    }

    Interceptor code = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) {
            Response response = null;

            try {
                response = chain.proceed(chain.request());
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    String data = responseBody.string();
                    if (response.code() == ErrorCodeEnum.ERROR__401.getErrorCode() || response.code() == ErrorCodeEnum.ERROR__403.getErrorCode() || response.code() == ErrorCodeEnum.ERROR__200.getErrorCode()) {
                        MediaType mediaType = responseBody.contentType();
                        return response.newBuilder().code(ErrorCodeEnum.ERROR__200.getErrorCode()).body(ResponseBody.create(mediaType, data)).build();
                    }
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            return response;
        }
    };
}
