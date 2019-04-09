package com.ydh.lib.retrofit.common;

/**
 * @author 13001
 */
public class NetworkCommon {

    /**
     * 主机地址
     */
    public static String baseUrl = "";

    /**
     * token
     */
    public static String token = "";
    public static String tokenName = "";
    public static int tokenCode = 8888;

    /**
     * 读超时
     */
    public static long readTimeOut = 10000;
    /**
     * 写超时
     */
    public static long writeTimeOut = 10000;
    /**
     * 连接
     */
    public static long connectTimeout = 10000;

    /**
     * 连接失败重连次数
     */
    public static int cacheTime = 0;

    /**
     * 连接失败重连次数
     */
    public static int retryCount = 0;
    /**
     * 延迟重试时间 MS
     */
    public static int retryDelay = 10000;
    /**
     * 叠加延迟
     */
    public static int retryIncreaseDelay = 10000;

}
