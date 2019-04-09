package com.ydh.okhttp.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 13001
 */
public class OkHttpNet {
    static OkHttpClient client;

    static OkHttpNet okHttpNet;

    public static OkHttpNet getIn() {
        if (okHttpNet==null){
            okHttpNet=new OkHttpNet();
            client=new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        }
        return okHttpNet;
    }


    /**
     *同步请求
     */
    public static void synRequest(){
        Request request=new Request.Builder().url("http://www.baidu.com").get().build();

        Call call=client.newCall(request);
        try{
            Response response=call.execute();
            System.out.println(response.body().toString());
        }catch (Exception e){

        }
    }

    /**
     *异步请求
     */
    public static void asyRequest(){
        Request request=new Request.Builder().url("http://www.baidu.com").get().build();

        Call call=client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }


    
}
