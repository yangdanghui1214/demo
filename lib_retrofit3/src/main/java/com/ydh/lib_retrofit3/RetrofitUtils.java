package com.ydh.lib_retrofit3;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ydh.lib_retrofit3.common.NetCommon;
import com.ydh.lib_retrofit3.model.BasicResponse;
import com.ydh.lib_retrofit3.retrofit.BaseApi;
import com.ydh.lib_retrofit3.retrofit.callback.CallBackProxy;
import com.ydh.lib_retrofit3.retrofit.func.ApiResultFunc;
import com.ydh.lib_retrofit3.retrofit.func.CacheResultFunc;
import com.ydh.lib_retrofit3.retrofit.func.HandleFuc;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings(value={"unchecked", "deprecation"})
public class RetrofitUtils {

    private static RetrofitUtils instance;
    private static BaseApi baseApi;
    private static Context mCxt;
    /**
     * mRetrofit
     */
    private Retrofit mRetrofit;
    /**
     * mClient
     */
    private OkHttpClient mClient;


    private RetrofitUtils() {
    }

    /**
     * 单例
     *
     * @return
     */
    public static RetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils();
                    baseApi = get(BaseApi.class);
                }
            }
        }
        return instance;
    }

    public static void init(Context context) {
        mCxt = context;
    }

    public static Context getCxt() {
        return mCxt;
    }

    /**
     * 创建 Retrofit
     *
     * @return Retrofit
     */
    public static synchronized Retrofit createRetrofit() {
        return getInstance().getRetrofit();
    }


    /**
     * 获取Retrofit
     *
     * @return 获取Retrofit
     */
    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(NetCommon.baseUrl)
                    .client(getHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            mRetrofit = builder.build();
        }
        return mRetrofit;
    }


    /**
     * 获取httpclient
     *
     * @return OkHttpClient
     */
    private OkHttpClient getHttpClient() {
        if (mClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(NetCommon.connectTimeout, TimeUnit.MILLISECONDS)
                    .writeTimeout(NetCommon.writeTimeOut, TimeUnit.MILLISECONDS)
                    .readTimeout(NetCommon.readTimeOut, TimeUnit.MILLISECONDS);


            // 拦截器
//            Interceptor[] interceptors = mConfig.configInterceptors();
//            if (interceptors != null && interceptors.length > 0) {
//                for (Interceptor interceptor : interceptors) {
//                    builder.addInterceptor(interceptor);
//                }
//            }
//            if (mConfig.configLogEnable()) {//配置打印
//                HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
//                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                builder.addInterceptor(logInterceptor);
//            }

            mClient = builder.build();
        }
        return mClient;
    }


    /**
     * 创建class
     *
     * @param service 服务class
     * @param <C>     类泛型
     * @return 泛型
     */
    public static <C> C get(Class<C> service) {
        return getInstance().getRetrofit().create(service);
    }


    public <T> Observable<T>  post(String url, HashMap<String, String> map, Class<T> deviceLoginModelClass) {
        return baseApi.post(url, map)
                .map(new ApiResultFunc(new CallBackProxy<BasicResponse<T>, T>(deviceLoginModelClass) {
                }.getType()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new HandleFuc<T>())
                .compose(upstream -> upstream.map(new CacheResultFunc<T>()));

    }

}
