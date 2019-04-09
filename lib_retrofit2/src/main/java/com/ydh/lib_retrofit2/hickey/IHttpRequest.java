package com.ydh.lib_retrofit2.hickey;

public interface IHttpRequest {

    void setUrl(String url);

    void setData(byte[] data);

    void setListener(CallBackLintener callBackLintener);

    void execute();

}
