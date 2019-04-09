package com.ydh.lib_retrofit2.hickey;

import com.ydh.lib_retrofit2.exception.AiException;
import com.ydh.lib_retrofit2.exception.AiThrowable;

public interface CallBack<T> {

    /**
     * 成功返回
     * @param t 数据
     */
     void onSubscribe(T t);

    /**
     * 失败返回
     * @param e 错误信息
     */
     void onFailure(AiThrowable e);

}
