package com.tsign.health.network;

/**
 * 考勤errorCode枚举类, 方便统一管理
 *
 * @author amoslv
 * @date 2019/03/02
 */

public enum ErrorCodeEnum {

    /**
     * errorCode
     */
    ERROR__1(-1, "http请求失败code"),
    ERROR__2(-2, "ResponseBody为null异常code"),
    ERROR__3(-3, "http请求成功不为200异常code"),
    ERROR__4(-4, "数据处理异常code"),
    ERROR__5(-5, "Json数据解析异常"),
    ERROR__0(0, "业务接口请求成功"),
    ERROR__200(200, "http请求成功"),
    ERROR__300(300, "门禁考勤上传重复code, 班牌和云考勤都是此code"),
    ERROR__401(401, "未授权"),
    ERROR__403(403, "http请求成功"),
    ERROR__500(500, "服务器内部错误"),
    ERROR__8888(8888, "Token失效code"),
    ERROR__30001(30001, "从国寿项目开始重复code使用30001"),
    /**
     * 旷目天眼罪犯上传重复code
     * 2019-01-16 为11122
     * 2019-01-30 调整为90011
     * 2019-2/1服务器并未改掉先恢复之前的
     */
    ERROR_90011(90011, "旷目天眼罪犯上传重复code");

    private int errorCode;

    private String errorInfo;

    ErrorCodeEnum(int errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
