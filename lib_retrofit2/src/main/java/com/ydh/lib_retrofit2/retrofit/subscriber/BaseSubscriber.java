package com.ydh.lib_retrofit2.retrofit.subscriber;

import com.ydh.lib_retrofit2.exception.AiException;
import com.ydh.lib_retrofit2.exception.AiThrowable;
import com.ydh.lib_retrofit2.tools.HttpLog;
import com.ydh.lib_retrofit2.tools.Utils;

import java.lang.reflect.Type;

import rx.Subscriber;

/**
 * <p>描述：订阅的基类</p>
 * 1.可以防止内存泄露。<br>
 * 3.统一处理了异常<br>
 * 作者： zhouyou<br>
 * 日期： 2016/12/20 10:35<br>
 * 版本： v2.0<br>
 */
public abstract class BaseSubscriber<T> extends Subscriber<T>  {


    public BaseSubscriber() {
    }

    @Override
    final public void onError(java.lang.Throwable e) {
        if (e != null && e.getMessage() != null) {
            HttpLog.v("Ai"+ e.getMessage());

        } else {
            HttpLog.v("Ai"+ "AiThrowable  || Message == Null");
        }

        try {
            if (e instanceof AiThrowable) {
                HttpLog.e("Ai"+ "--> e instanceof AiThrowable");
                HttpLog.e("Ai"+ "--> " + e.getCause().toString());
                onError((AiThrowable) e);
            } else {
                HttpLog.e("Ai"+ "e !instanceof AiThrowable");
                String detail = "";
                if (e.getCause() != null) {
                    detail = e.getCause().getMessage();
                }
                HttpLog.e("Ai"+ "--> " + detail);
                onError(AiException.handleException(e));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        onCompleted();
    }

    @Override
    public void onStart() {
        super.onStart();
        HttpLog.v("Ai"+ "-->http is start");
        // todo some common as show loadding  and check netWork is NetworkAvailable
        // if  NetworkAvailable no !   must to call onCompleted
    }

    @Override
    public void onCompleted() {
        HttpLog.v("Ai"+ "-->http is Complete");
    }

    public abstract void onError(AiThrowable e);

}
