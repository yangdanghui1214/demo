package com.ydh.lib_retrofit3.lintener;


import com.ydh.lib_retrofit3.exception.AiThrowable;

public interface CallBackLintener<T>  {

    /**
     * 成功返回
     *
     * @param t 数据
     */
    void onSubscribe(T t);

    /**
     * 失败返回
     *
     * @param e 错误信息
     */
    void onFailure(String e);

}
