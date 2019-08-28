package com.ydh.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ydh.network.call.ICallback;
import com.ydh.network.common.NetworkCommon;
import com.ydh.network.exception.AiThrowable;
import com.ydh.network.interceptor.LogInterceptor;
import com.ydh.network.processor.IHttpProcessor;
import com.ydh.network.retrofit.api.BaseApi;
import com.ydh.network.retrofit.subscriber.BaseObserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 对外部的接口请求的定义
 *
 * @author 13001
 */
public class RetrofitCustom implements IHttpProcessor {

    private static RetrofitCustom retrofitCustom = new RetrofitCustom();

    /**
     * 拦截器
     */
    private Interceptor[] configInterceptors;

    /**
     * api
     */
    private BaseApi baseApi;

    private RetrofitCustom() {
    }

    /**
     * @param url 主机地址
     * @return 上下文
     */
    public static RetrofitCustom init(String url) {
        NetworkCommon.baseUrl = url;
        return retrofitCustom;
    }

    /**
     * @param time 请求超时时间
     * @return 上下文
     */
    public RetrofitCustom requestTime(long time) {
        NetworkCommon.connectTimeout = time;
        return retrofitCustom;
    }

    /**
     * @param interceptor 请求的拦截器
     * @return 上下文
     */
    public RetrofitCustom setInterceptor(Interceptor[] interceptor) {
        configInterceptors = interceptor;
        return retrofitCustom;
    }

    /**
     * @return
     */
    public RetrofitCustom getRetrofit() {

        // okHttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(NetworkCommon.connectTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(NetworkCommon.writeTimeOut, TimeUnit.MILLISECONDS)
                .readTimeout(NetworkCommon.readTimeOut, TimeUnit.MILLISECONDS);

        // 拦截器
        if (configInterceptors != null && configInterceptors.length > 0) {
            for (Interceptor interceptor : configInterceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        //配置打印
        if (NetworkCommon.logEnable) {
            LogInterceptor logInterceptor = new LogInterceptor();
            builder.addInterceptor(logInterceptor);
        }

        OkHttpClient mClient = builder.build();

        // retrofit
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(NetworkCommon.baseUrl)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        baseApi = mRetrofit.create(BaseApi.class);
        return retrofitCustom;
    }


    public String getSync(String url) {
        String body = "";
        try {
            body = Objects.requireNonNull(baseApi.getSync(url).execute().body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * @param url    接口地址
     * @param params 参数
     */
    public String getSync(String url, HashMap<String, String> params) {
        String body = "";
        Call<ResponseBody> call = params == null ? baseApi.getSync(url) : baseApi.getSync(url, params);
        try {
            body = Objects.requireNonNull(call.execute().body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * @param url    接口地址
     * @param params 参数
     */
    public String postSync(String url, HashMap<String, String> params) {
        String body = "";
        Call<ResponseBody> call = baseApi.postSync(url, params);
        try {
            body = Objects.requireNonNull(call.execute().body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * @param url    接口地址
     * @param params 参数
     */
    public String postBody(String url, HashMap<String, String> params) {
        String body = "";
        Call<ResponseBody> call = baseApi.postBody(url, getRequestBody(params));
        try {
            body = Objects.requireNonNull(call.execute().body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }


    /**
     * get 请求
     * @param url
     * @param callbask
     */
    @Override
    public void get(String url, ICallback callbask) {
        Observable<ResponseBody> observable = baseApi.get(url);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ResponseBody>() {

                    @Override
                    public void onNext(ResponseBody body) {
                        String content = null;
                        try {
                            content = body.string();
                            callbask.onSuccess(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                            callbask.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    protected void onError(AiThrowable e) {
                        callbask.onFailure(e.getMessage());
                    }
                });
    }

    @Override
    public void get(String url, HashMap<String, String> params, ICallback callbask) {
        Observable<ResponseBody> observable = params == null ? baseApi.get(url) : baseApi.get(url, params);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ResponseBody>() {

                    @Override
                    public void onNext(ResponseBody body) {
                        String content = null;
                        try {
                            content = body.string();
                            callbask.onSuccess(content);
                            if (params != null) {
                                params.clear();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            callbask.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    protected void onError(AiThrowable e) {
                        callbask.onFailure(e.getMessage());
                        if (params != null) {
                            params.clear();
                        }
                    }
                });
    }

    @Override
    public void post(String url, ICallback callbask) {
        Observable<ResponseBody> observable = baseApi.post(url);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ResponseBody>() {

                    @Override
                    public void onNext(ResponseBody body) {
                        String content = null;
                        try {
                            content = body.string();
                            callbask.onSuccess(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                            callbask.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    protected void onError(AiThrowable e) {
                        callbask.onFailure(e.getMessage());
                    }
                });
    }


    /**
     * @param url      接口地址
     * @param params   参数
     * @param callbask 返回
     */
    @Override
    public void post(String url, HashMap<String, String> params, ICallback callbask) {
        Observable<ResponseBody> observable = params == null ? baseApi.post(url) : baseApi.post(url, params);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ResponseBody>() {

                    @Override
                    public void onNext(ResponseBody body) {
                        String content = null;
                        try {
                            content = body.string();
                            callbask.onSuccess(content);
                            if (params != null) {
                                params.clear();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            callbask.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    protected void onError(AiThrowable e) {
                        callbask.onFailure(e.getMessage());
                        if (params != null) {
                            params.clear();
                        }
                    }
                });
    }

    /**
     * put 请求参数
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
        Observable<ResponseBody> observable = params == null ? baseApi.get(url) : baseApi.get(url, params);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ResponseBody>() {

                    @Override
                    public void onNext(ResponseBody body) {
                        String content = null;
                        try {
                            content = body.string();
                            callback.onSuccess(content);
                            if (params != null) {
                                params.clear();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            callback.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    protected void onError(AiThrowable e) {
                        callback.onFailure(e.getMessage());
                        if (params != null) {
                            params.clear();
                        }
                    }
                });
    }

    /**
     * del 的请求方式
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
        Observable<ResponseBody> observable = params == null ? baseApi.get(url) : baseApi.get(url, params);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ResponseBody>() {

                    @Override
                    public void onNext(ResponseBody body) {
                        String content = null;
                        try {
                            content = body.string();
                            callback.onSuccess(content);
                            if (params != null) {
                                params.clear();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            callback.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    protected void onError(AiThrowable e) {
                        callback.onFailure(e.getMessage());
                        if (params != null) {
                            params.clear();
                        }
                    }
                });
    }

    /**
     * 将 map 转换为 RequestBody
     * @param hashMap
     * @return
     */
    public RequestBody getRequestBody(HashMap<String, String> hashMap) {
        StringBuffer data = new StringBuffer();
        if (hashMap != null && hashMap.size() > 0) {
            Iterator iter = hashMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                data.append(key).append("=").append(val).append("&");
            }
        }
        String jso = data.substring(0, data.length() - 1);
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),jso);

        return requestBody;
    }

}
