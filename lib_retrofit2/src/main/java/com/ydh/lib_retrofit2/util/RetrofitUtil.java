package com.ydh.lib_retrofit2.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ydh.lib_retrofit2.common.NetworkCommon;
import com.ydh.lib_retrofit2.exception.AiThrowable;
import com.ydh.lib_retrofit2.hickey.CallBack;
import com.ydh.lib_retrofit2.hickey.IHttp;
import com.ydh.lib_retrofit2.interceptor.RequestHeaderInterceptor;
import com.ydh.lib_retrofit2.retrofit.api.ApiService;
import com.ydh.lib_retrofit2.retrofit.model.ResponseResult;
import com.ydh.lib_retrofit2.retrofit.subscriber.BaseSubscriber;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author 13001
 */
@SuppressWarnings("unchecked")
public class RetrofitUtil implements IHttp {

    private static RetrofitUtil retrofitUtil = null;
    private static ApiService api;

    public static RetrofitUtil getInstance() {
        if (retrofitUtil == null) {

            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            //这里可以选择拦截级别
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(NetworkCommon.connectTimeout, TimeUnit.MILLISECONDS)
                    .writeTimeout(NetworkCommon.writeTimeOut, TimeUnit.MILLISECONDS)
                    .readTimeout(NetworkCommon.readTimeOut, TimeUnit.MILLISECONDS)
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(new RequestHeaderInterceptor())
                    .build();

            //添加gson的支持
            //添加Rxjava的支持
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .baseUrl(NetworkCommon.baseUrl)
                    .build();
            api = retrofit.create(ApiService.class);


            retrofitUtil = new RetrofitUtil();
        }
        return retrofitUtil;
    }

    private Observable.Transformer schedulersTransformer() {
        return observable -> ((Observable) observable).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public <T> Observable<T> getCustom(String url) {
        return null;
    }

    @Override
    public <T> Observable<T> getCustom(String url, HashMap<String, String> map) {
        return null;
    }

    @Override
    public <T> Observable<T> postCustom(String url) {
        return null;
    }

    @Override
    public <T> Observable<T> postCustom(String url, HashMap<String, String> map) {
        return null;
    }

    @Override
    public <T> void get(String url, CallBack<T> callBack) {
        getCustom(url)
                .compose(schedulersTransformer())
                .subscribe(new BaseSubscriber<T>() {
                    @Override
                    public void onNext(T t) {
                        callBack.onSubscribe(t);
                    }

                    @Override
                    public void onError(AiThrowable e) {
                        callBack.onFailure(e);
                    }
                });
    }

    @Override
    public <T> void get(String url, HashMap<String, String> map, CallBack<T> callBack) {
        api.get(url, map)
                .compose(schedulersTransformer())
                .subscribe(new BaseSubscriber<T>() {
                    @Override
                    public void onNext(T t) {
                        callBack.onSubscribe(t);
                    }

                    @Override
                    public void onError(AiThrowable e) {
                        callBack.onFailure(e);
                    }
                });
    }

    @Override
    public <T> void post(String url, CallBack<T> callBack) {
        api.post(url)
                .compose(schedulersTransformer())
                .subscribe(new BaseSubscriber<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody t) {
//                        callBack.onSubscribe(t);
                    }

                    @Override
                    public void onError(AiThrowable e) {
                        callBack.onFailure(e);
                    }
                });

    }

    @Override
    public <T> void post(String url, HashMap<String, String> map, CallBack<T> callBack) {
         api.post(url, map)
                .compose(schedulersTransformer())
                .subscribe(new BaseSubscriber<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody body) {
                        Type jsonType = new TypeToken<ResponseResult<T>>() {
                        }.getType();
                        ResponseResult<List<T>> responseResult=new Gson().fromJson(body.toString(),jsonType);
//                        T t=new Gson().fromJson(body.toString(),jsonType);
//                        callBack.onSubscribe(responseResult.getData());
                    }

                    @Override
                    public void onError(AiThrowable e) {
                        callBack.onFailure(e);
                    }
                });
    }
}
