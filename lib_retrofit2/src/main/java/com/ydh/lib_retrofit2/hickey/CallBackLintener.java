package com.ydh.lib_retrofit2.hickey;

import com.ydh.lib_retrofit2.exception.AiThrowable;

import java.io.InputStream;

public interface CallBackLintener<T> {

    /**
     * 成功返回
     * @param in
     */
     void onSubscribe(InputStream in);

    /**
     * 失败返回
     * @param e 错误信息
     */
     void onFailure(AiThrowable e);

}
