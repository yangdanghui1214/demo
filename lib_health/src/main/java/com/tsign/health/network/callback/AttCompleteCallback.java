package com.tsign.health.network.callback;

import com.tsign.health.model.RequestModel;
import com.tsign.health.model.ResponseResultModel;

/**
 * @author xhj
 * @date 2018/11/10
 */

public interface AttCompleteCallback<T1 extends RequestModel, T2 extends ResponseResultModel> {

    void onSuccess(T1 request, T2 result);

    void onFailure(T1 request, T2 result);
}
