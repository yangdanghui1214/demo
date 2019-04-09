package com.ydh.lib_retrofit2.retrofit.api;


import com.ydh.lib_retrofit2.retrofit.model.ResponseResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 网络接口
 *
 * @author amoslv
 * @date 2018/11/14
 */
public interface ApiService {


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
     * 下载文件
     *
     * @param fileUrl 文件路径
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

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
     * 上传数据
     *
     * @param url      路径
     * @param jsonBody json 上传方式
     * @return
     */
    @POST()
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<ResponseBody> postJson(@Url String url, @Body RequestBody jsonBody);

    /**
     * 上传数据
     *
     * @param url  路径
     * @param body body上传
     * @return
     */
    @POST()
    Observable<ResponseBody> postBody(@Url String url, @Body RequestBody body);


    /**
     * 文件上传
     *
     * @param url  路径
     * @param maps
     * @return
     */
    @Multipart
    @POST()
    Observable<ResponseBody> uploadFiles(@Url String url, @PartMap() Map<String, RequestBody> maps);

    /**
     * 文件上传
     *
     * @param url   路径
     * @param parts
     * @return
     */
    @Multipart
    @POST()
    Observable<ResponseBody> uploadFiles(@Url String url, @Part() List<MultipartBody.Part> parts);

}
