package com.ydh.lib.retrofit;

import android.content.Context;

import com.ydh.lib.retrofit.common.NetworkCommon;
import com.ydh.lib.retrofit.https.HttpsUtils;
import com.ydh.lib.retrofit.model.HttpHeaders;
import com.ydh.lib.retrofit.model.HttpParams;
import com.ydh.lib.retrofit.retrofit.ApiService;
import com.ydh.lib.retrofit.util.HttpLog;
import com.ydh.lib.retrofit.util.Utils;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.reactivex.disposables.Disposable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 接口请求
 *
 * @author 13001
 */
public class RetrofitUtil {

    private static RetrofitUtil retrofitUtil;

    private static OkHttpClient.Builder builder;
    private static Retrofit.Builder retrofit;
    private static ApiService apiService;

    private static String baseHost = "";
    private static Context mCxt;

    private HttpHeaders mCommonHeaders;
    private HttpParams mCommonParams;

    public static RetrofitUtil getInstance(Context context) {
        mCxt = context;
        if (retrofitUtil == null) {
            retrofitUtil = new RetrofitUtil();
        }
        return retrofitUtil;
    }

    /**
     * 初始化必要对象和参数
     */
    public void init(String host) {
        baseHost = host;
        // 初始化okhttp
        builder = new OkHttpClient.Builder();
        //设置缓存
//        builder.addNetworkInterceptor(cacheInterceptor);
//        builder.addInterceptor(cacheInterceptor);
//        builder.addInterceptor(mLogInterceptor);
//        builder.cache(cache);
        //设置超时
        builder.connectTimeout(NetworkCommon.connectTimeout, TimeUnit.SECONDS);
        builder.readTimeout(NetworkCommon.readTimeOut, TimeUnit.SECONDS);
        builder.writeTimeout(NetworkCommon.writeTimeOut, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);

        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(baseHost)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

//        apiService = retrofit.create(ApiService.class);
    }

    /**
     * @param isPrintException 是否为调试模式
     * @return
     */
    private RetrofitUtil debug(boolean isPrintException) {
        HttpLog.allowE = isPrintException;
        HttpLog.allowD = isPrintException;
        HttpLog.allowI = isPrintException;
        HttpLog.allowV = isPrintException;
        return this;
    }

    public static Context getmCxt() {
        return mCxt;
    }



    /**
     * 此类是用于主机名验证的基接口。 在握手期间，如果 URL 的主机名和服务器的标识主机名不匹配，
     * 则验证机制可以回调此接口的实现程序来确定是否应该允许此连接。策略可以是基于证书的或依赖于其他验证方案。
     * 当验证 URL 主机名使用的默认规则失败时使用这些回调。如果主机名是可接受的，则返回 true
     */
    public class DefaultHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * https的全局访问规则
     */
    public RetrofitUtil setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        builder.hostnameVerifier(hostnameVerifier);
        return this;
    }

    /**
     * https的全局自签名证书
     */
    public RetrofitUtil setCertificates(InputStream... certificates) {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, certificates);
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * https双向认证证书
     */
    public RetrofitUtil setCertificates(InputStream bksFile, String password, InputStream... certificates) {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(bksFile, password, certificates);
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }


    /**
     * 添加全局公共请求参数
     */
    public RetrofitUtil addCommonParams(HttpParams commonParams) {
        if (mCommonParams == null) {
            mCommonParams = new HttpParams();
        }
        mCommonParams.put(commonParams);
        return this;
    }

    /**
     * 获取全局公共请求参数
     */
    public HttpParams getCommonParams() {
        return mCommonParams;
    }

    /**
     * 获取全局公共请求头
     */
    public HttpHeaders getCommonHeaders() {
        return mCommonHeaders;
    }

    /**
     * 添加全局公共请求参数
     */
    public RetrofitUtil addCommonHeaders(HttpHeaders commonHeaders) {
        if (mCommonHeaders == null) {
            mCommonHeaders = new HttpHeaders();
        }
        mCommonHeaders.put(commonHeaders);
        return this;
    }

    /**
     * 添加全局拦截器
     */
    public RetrofitUtil addInterceptor(Interceptor interceptor) {
        builder.addInterceptor(Utils.checkNotNull(interceptor, "interceptor == null"));
        return this;
    }


    public static RetrofitUtil getRetrofitUtil() {
        return retrofitUtil;
    }

    public static OkHttpClient.Builder getBuilder() {
        return builder;
    }

    public static Retrofit.Builder getRetrofit() {
        return retrofit;
    }

    /**
     * 取消订阅
     */
    public static void cancelSubscription(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
