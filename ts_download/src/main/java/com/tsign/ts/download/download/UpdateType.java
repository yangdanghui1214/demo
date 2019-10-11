package com.tsign.ts.download.download;

/**
 * 文件下载方式失败coed返回
 * @author 13001
 */
public enum UpdateType {

    /**
     * 版本升级，失败code返回
     */
    CODE_1003(1003,"网络异常，请检查网络连接"),

    CODE_1004(1004,"暂停下载APK"),

    CODE_1005(1005,"下载地址错误，请重新上传");


    private int code;
    private String msg;

    UpdateType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "UpdateType{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
