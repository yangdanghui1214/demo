package com.tsign.health.model;

/**
 * @author xhj
 * @date 2018/11/12
 */

public class RequestModel {

    private String host;
    private String url;
    private String attend;
    private int count;
    private String serialRequestNum;
    private String accessToken;

    public RequestModel() {
    }

    public RequestModel(String host, String url, String attend, String accessToken) {
        this.host = host;
        this.url = url;
        this.attend = attend;
        this.accessToken = accessToken;
    }

    public RequestModel(String host, String url, String attend, int count, String serialRequestNum, String accessToken) {
        this.host = host;
        this.url = url;
        this.attend = attend;
        this.count = count;
        this.serialRequestNum = serialRequestNum;
        this.accessToken = accessToken;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSerialRequestNum() {
        return serialRequestNum;
    }

    public void setSerialRequestNum(String serialRequestNum) {
        this.serialRequestNum = serialRequestNum;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "host='" + host + '\'' +
                ", url='" + url + '\'' +
                ", attend='" + attend + '\'' +
                ", count=" + count +
                ", serialRequestNum='" + serialRequestNum + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
