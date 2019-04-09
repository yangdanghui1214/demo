package com.ydh.lib_retrofit3.retrofit.subscriber;

import com.ydh.lib_retrofit3.exception.AiException;
import com.ydh.lib_retrofit3.exception.AiThrowable;
import com.ydh.lib_retrofit3.util.HttpLog;

import io.reactivex.Observer;


/**
 * 界面数据返回
 * @author 13001
 */
public abstract class BaseObserver implements Observer {

    @Override
    public void onError(Throwable e) {
        if (e != null && e.getMessage() != null) {
            HttpLog.v("Ai" + e.getMessage());

        } else {
            HttpLog.v("Ai" + "AiThrowable  || Message == Null");
        }

        try {
            if (e instanceof AiThrowable) {
                HttpLog.e("Ai" + "--> e instanceof AiThrowable");
                HttpLog.e("Ai" + "--> " + e.getCause().toString());
                onError((AiThrowable) e);
            } else {
                HttpLog.e("Ai" + "e !instanceof AiThrowable");
                String detail = "";
                if (e != null && e.getCause() != null) {
                    detail = e.getCause().getMessage();
                }
                HttpLog.e("Ai" + "--> " + detail);
                onError(AiException.handleException(e));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        onComplete();
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onError(AiThrowable e);

}
