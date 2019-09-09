package com.tsign.health.network;

import android.util.Log;

import com.tsign.health.constant.AttendanceConstant;
import com.tsign.health.model.RequestModel;
import com.tsign.health.model.ResponseResultModel;
import com.tsign.health.network.callback.AttCompleteCallback;
import com.tsign.health.util.JsonModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author xhj
 * @date 2018/11/10
 */

public class AttendHttp {

    private static String TAG = AttendHttp.class.getSimpleName();
    private static AttHttpInnerCallback attHttpInnerCallback;

    public static void saveAttendances(final RequestModel request, final AttCompleteCallback<RequestModel,
            ResponseResultModel> completedCallback) {
        if (attHttpInnerCallback == null) {
            synchronized (AttendHttp.class) {
                if (attHttpInnerCallback == null) {
                    attHttpInnerCallback = new AttHttpInnerCallback(request, completedCallback);
                }
            }
        }
        postAttend(request.getHost() + request.getUrl(), request.getAttend(),
                request.getAccessToken(), attHttpInnerCallback);
    }

    private static void postAttend(String url, String attend, String accessToken, Callback callback) {
        if (AttendanceConstant.getDebug()) {
            Log.e(TAG, "url=" + url);
            Log.e(TAG, "attend=" + attend);
        }
        RequestBody requestBody = FormBody
                .create(MediaType.parse("application/json; charset=utf-8"), attend);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Authorization", accessToken)
                .build();

        OkHttpClientManager.getInstance().getClient().newCall(request).enqueue(callback);
    }

    private static class AttHttpInnerCallback implements Callback {

        RequestModel mRequest;
        AttCompleteCallback<RequestModel, ResponseResultModel> mCompletedCallback;

        private AttHttpInnerCallback(RequestModel request, AttCompleteCallback<RequestModel,
                        ResponseResultModel> completedCallback) {
            this.mRequest = request;
            this.mCompletedCallback = completedCallback;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            ResponseResultModel responseResultModel = new ResponseResultModel();
            responseResultModel.setMsg(e.getMessage());
            responseResultModel.setCode(ErrorCodeEnum.ERROR__1.getErrorCode());
            mCompletedCallback.onFailure(mRequest, responseResultModel);
        }

        @Override
        public void onResponse(Call call, Response response) {
            try {
                if (response.code() == ErrorCodeEnum.ERROR__200.getErrorCode()) {
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        String json = responseBody.string();
                        JsonModel<ResponseResultModel> resultModelJsonModel =
                                JsonModel.fromJson(json, ResponseResultModel.class);
                        if (resultModelJsonModel == null) {
                            ResponseResultModel responseResultModel = new ResponseResultModel();
                            responseResultModel.setMsg("responseBody == null");
                            responseResultModel.setCode(ErrorCodeEnum.ERROR__5.getErrorCode());
                            mCompletedCallback.onFailure(mRequest, responseResultModel);
                            return;
                        }
                        int responseCode = resultModelJsonModel.getCode();
                        if (responseCode == ErrorCodeEnum.ERROR__0.getErrorCode()) {
                            mCompletedCallback.onSuccess(mRequest, resultModelJsonModel.getData());
                        } else if (responseCode == ErrorCodeEnum.ERROR__300.getErrorCode()
                                || responseCode == ErrorCodeEnum.ERROR__30001.getErrorCode()) {
                            ResponseResultModel responseResultModel = new ResponseResultModel();
                            responseResultModel.setMsg(resultModelJsonModel.getMsg());
                            responseResultModel.setCode(resultModelJsonModel.getCode());
                            mCompletedCallback.onFailure(mRequest, responseResultModel);
                        } else if (responseCode == ErrorCodeEnum.ERROR_90011.getErrorCode()) {
                            ResponseResultModel responseResultModel = new ResponseResultModel();
                            responseResultModel.setMsg(resultModelJsonModel.getMsg());
                            responseResultModel.setCode(resultModelJsonModel.getCode());
                            mCompletedCallback.onFailure(mRequest, responseResultModel);
                        } else {
                            ResponseResultModel responseResultModel = new ResponseResultModel();
                            responseResultModel.setMsg(resultModelJsonModel.getMsg());
                            responseResultModel.setCode(resultModelJsonModel.getCode());
                            mCompletedCallback.onFailure(mRequest, responseResultModel);
                        }
                    } else {
                        ResponseResultModel responseResultModel = new ResponseResultModel();
                        responseResultModel.setMsg("responseBody == null");
                        responseResultModel.setCode(ErrorCodeEnum.ERROR__2.getErrorCode());
                        mCompletedCallback.onFailure(mRequest, responseResultModel);
                    }
                } else {
                    ResponseResultModel responseResultModel = new ResponseResultModel();
                    responseResultModel.setMsg("responseBody == null ");
                    responseResultModel.setCode(ErrorCodeEnum.ERROR__3.getErrorCode());
                    mCompletedCallback.onFailure(mRequest, responseResultModel);
                }
            } catch (Exception e) {
                ResponseResultModel responseResultModel = new ResponseResultModel();
                responseResultModel.setMsg("responseBody == null " + e.getMessage());
                responseResultModel.setCode(ErrorCodeEnum.ERROR__4.getErrorCode());
                mCompletedCallback.onFailure(mRequest, responseResultModel);
            }

        }
    }
}
