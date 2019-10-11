package com.tsign.health.model;

/**
 * @author amoslv
 * <p>
 * 一般请求返回的数据格式
 */
public class ResponseResultModel {

    /**
     * msg :
     * code : 0
     * data : {}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
    }

}
