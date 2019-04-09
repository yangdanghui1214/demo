package com.ydh.lib.retrofit.interceptor;

import android.support.annotation.NonNull;

import com.ydh.lib.retrofit.common.NetworkCommon;
import com.ydh.lib.retrofit.util.HttpLog;

import org.json.JSONObject;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * 拦截请求头中的token, 失效时进行替换
 *
 * @author amoslv
 * @date 2018/07/10
 */
public class RequestHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) {
        //原始请求
        Request originalRequest = chain.request();
        Response response = null;

        // 添加请求头accessToken
        Request addTokenRequest = originalRequest.newBuilder()
                .addHeader(NetworkCommon.tokenName, NetworkCommon.token)
                .build();

        try {
            response = chain.proceed(addTokenRequest);
            if (response.code() == 200) {
                MediaType mediaType = response.body().contentType();

                //切记response.body只能调用一次,因为第二次调用的时候直接抛异常, 详细的可以查看源码
                String data = response.body().string();

                HttpLog.e("zxy  ----------Request Start----------------");
                HttpLog.e("zxy  | " + originalRequest.toString());
                HttpLog.e("zxy  | Response:" + data);
                HttpLog.e("zxy  ----------Request End:" + "毫秒----------");
                JSONObject jsonObject = new JSONObject(data);
                int code = jsonObject.getInt("code");
                //这个是与后台约定好的token失效的code
                if (code == NetworkCommon.tokenCode) {
                    Request addTokenRequest2 = originalRequest.newBuilder()
                            .addHeader(NetworkCommon.tokenName, NetworkCommon.token)
                            .build();
                    return chain.proceed(addTokenRequest2);
                }
                //因为调用response.body().string, response就会为null
                return response.newBuilder().body(ResponseBody.create(mediaType, data)).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
