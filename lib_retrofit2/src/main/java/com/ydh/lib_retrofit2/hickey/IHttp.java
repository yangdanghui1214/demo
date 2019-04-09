package com.ydh.lib_retrofit2.hickey;

import java.util.HashMap;

import rx.Observable;

/**
 * 接口请求类型
 *
 * @author 13001
 */
public interface IHttp {

    /**
     * @param url
     * @param <T>
     */
    <T> Observable<T> getCustom(String url);

    /**
     * @param url
     * @param map
     * @param <T>
     */
    <T> Observable<T> getCustom(String url, HashMap<String, String> map);

    /**
     * @param url
     * @param <T>
     */
    <T> Observable<T> postCustom(String url);

    /**
     * @param url
     * @param map
     * @param <T>
     */
    <T> Observable<T> postCustom(String url, HashMap<String, String> map);

    /**
     * @param url
     * @param callBack
     * @param <T>
     */
    <T> void get(String url, CallBack<T> callBack);

    /**
     * @param url
     * @param map
     * @param callBack
     * @param <T>
     */
    <T> void get(String url, HashMap<String, String> map, CallBack<T> callBack);

    /**
     * @param url
     * @param callBack
     * @param <T>
     */
    <T> void post(String url, CallBack<T> callBack);

    /**
     * @param url
     * @param map
     * @param callBack
     * @param <T>
     */
    <T> void post(String url, HashMap<String, String> map, CallBack<T> callBack);

}
