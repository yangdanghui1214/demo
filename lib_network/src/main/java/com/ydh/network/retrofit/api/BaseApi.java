package com.ydh.network.retrofit.api;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 公共接口请求类
 *
 * @author 13001
 */
public interface BaseApi {

    /**
     * get 请求
     *
     * @param url 请求路径
     * @return
     */
    @GET()
    Call<ResponseBody> getSync(@Url String url);

    /**
     * get 请求
     *
     * @param url  地址
     * @param maps 入参
     * @return
     */
    @GET()
    Call<ResponseBody> getSync(@Url String url, @QueryMap HashMap<String, String> maps);

    /**
     * 表单请求
     *
     * @param url  地址
     * @param maps 入参
     * @return
     */
    @POST()
    @FormUrlEncoded
    Call<ResponseBody> postSync(@Url String url, @FieldMap HashMap<String, String> maps);

    /**
     * body 的传输数据
     *
     * @param url
     * @param requestBody
     * @return
     */
    @POST()
    Call<ResponseBody> postBody(@Url String url, @Body RequestBody requestBody);

    /**
     * put 传输数据
     *
     * @param url
     * @return
     */
    @PUT()
    Call<ResponseBody> putSync(@Url String url);

    @PUT()
    Call<ResponseBody> putSync(@Url String url, @QueryMap HashMap<String, String> maps);

    /**
     * DELETE 传输数据
     *
     * @param url
     * @return
     */
    @DELETE()
    Call<ResponseBody> delSync(@Url String url);

    @DELETE()
    Call<ResponseBody> delSync(@Url String url, @QueryMap HashMap<String, String> maps);


    /**
     * get 请求
     *
     * @param url 请求路径
     * @return
     */
    @GET()
    Observable<ResponseBody> get(@Url String url);

    /**
     * get 请求
     *
     * @param url  请求路径
     * @param maps 入参
     * @return
     */
    @GET()
    Observable<ResponseBody> get(@Url String url, @QueryMap HashMap<String, String> maps);

    /**
     * 表单请求
     *
     * @param url 地址
     * @return
     */
    @POST()
    @FormUrlEncoded
    Observable<ResponseBody> post(@Url String url);

    /**
     * 表单请求
     *
     * @param url  地址
     * @param maps 入参
     * @return
     */
    @POST()
    @FormUrlEncoded
    Observable<ResponseBody> post(@Url String url, @FieldMap HashMap<String, String> maps);

    /**
     * put 请求
     *
     * @param url 地址
     * @return
     */
    @PUT
    Observable<ResponseBody> put(@Url String url);

    @PUT
    Observable<ResponseBody> put(@Url String url, @QueryMap HashMap<String, String> maps);

    /**
     * DELETE 请求
     *
     * @param url 地址
     * @return
     */
    @DELETE
    Observable<ResponseBody> del(@Url String url);

    @DELETE
    Observable<ResponseBody> del(@Url String url, @QueryMap HashMap<String, String> maps);

}
