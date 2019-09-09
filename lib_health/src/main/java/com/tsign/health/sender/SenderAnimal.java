package com.tsign.health.sender;

import android.util.Log;

import com.google.gson.Gson;
import com.tsign.health.constant.AttendanceConstant;
import com.tsign.health.factory.ThreadPool;
import com.tsign.health.generator.DataBootrapAnimal;
import com.tsign.health.model.AttendAnimal;
import com.tsign.health.model.HttpRequestModel;
import com.tsign.health.model.RequestModel;
import com.tsign.health.model.ResponseResultModel;
import com.tsign.health.network.AttendHttp;
import com.tsign.health.network.ErrorCodeEnum;
import com.tsign.health.network.callback.AttCompleteCallback;
import com.tsign.health.util.DataUtil;
import com.tsign.health.util.Singleton;

import java.util.List;

public class SenderAnimal {

    private final String TAG = SenderAnimal.class.getSimpleName();
    private final Object lock = new Object();
    private String mSerRequstNum;
    private static AttCompleteInnerCallback attCompleteInnerCallback;
    private static boolean hasAttend = true;
    private static int WAIT_TIME = 0;

    public static SenderAnimal getSingleton() {
        return Singleton.getSingleton(SenderAnimal.class);
    }

    /**
     * 更新考勤数据
     */
    public void notifyUpload() {
        ThreadPool.getInstance().submitFixedThreadPool(() -> {
            synchronized (lock) {
                logcat("考勤原始数据生成了...");
                lock.notifyAll();
                hasAttend = true;
            }
        });
    }

    /**
     * 有tag数据先取tag
     * 获取数据库20条数据，如果不足20条则取所有
     */
    private List<AttendAnimal.DetailsBean> readyData(String requstNum) {
        return DataBootrapAnimal.getSingleton().getAttenDances(requstNum);
    }

    /**
     * 给这一批次的数据打上tag
     */
    private AttendAnimal readyDataTag(List<AttendAnimal.DetailsBean> attendenceList, String serRequstNum) {
        for (AttendAnimal.DetailsBean attendence : attendenceList) {
            attendence.setSerialRequestNum(serRequstNum);
            attendence.setSerialNum(attendence.getSerialNum().substring(0, 10) + "_" +
                    DataBootrapAnimal.getSingleton().getRandomString(1));
        }
        DataBootrapAnimal.getSingleton().updateAttenDances(attendenceList);
        return new AttendAnimal(attendenceList, serRequstNum);
    }

    /**
     * 保存log数据到数据库
     *
     * @param httpRequestModel
     */
    public void putHttpRequestLogBean(HttpRequestModel httpRequestModel) {
        if (AttendanceConstant.getDebug()) {
            DataBootrapAnimal.getSingleton().putHttpRequestLogBean(httpRequestModel);
        }
    }

    /**
     * 先生成一个serquestNum
     *
     * @return
     */
    public void senderReuqestNum(final String serRequstNum) {
        ThreadPool.getInstance().submitFixedThreadPool(() -> {
            synchronized (lock) {
                //请求等待10s，防止请求过快导致数据库使用被关闭
                while (!hasAttend) {
                    logcat("等待考勤原始数据..." + serRequstNum);
                    try {
                        if (WAIT_TIME > 0) {
                            lock.wait(WAIT_TIME * 1000);
                        } else {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            logcat("等待考勤原始数据结束..." + serRequstNum);
            long c = DataBootrapAnimal.getSingleton().countAttendance();
            if (c <= 0) {
                hasAttend = false;
            }
            if (serRequstNum == null || serRequstNum.equals("1")) {
                mSerRequstNum = AttendanceConstant.getDeviceId() + "_"
                        + System.currentTimeMillis();
            } else {
                mSerRequstNum = serRequstNum;
            }
            logcat("开始上传考勤原始数据..." + serRequstNum);
            //调用http方法
            dataSender(mSerRequstNum);
        });
    }


    /**
     * 回调
     *
     * @param requstNum
     */
    private void dataSender(String requstNum) {
        if (requstNum == null) {
            senderReuqestNum(null);
        } else {
            //回掉是否可用如果不可用则删除掉这一批次的数据
            List<AttendAnimal.DetailsBean> details = readyData(requstNum);
            if (details != null) {
                if (details.size() == 0) {
                    details = readyData(null);
                    if (details != null && details.size() > 0) {
                        AttendAnimal attendAnimal = readyDataTag(details, requstNum);
                        if (AttendanceConstant.getDebug()) {
                            attendAnimal.setTest("on");
                        }
                        attendAnimal.setSize(new Gson().toJson(details).length());
                        //调用http方法
                        saveAttendances(new Gson().toJson(attendAnimal),
                                attendAnimal.getDetails().size(),
                                attendAnimal.getSerialRequestNum());
                    } else {
                        senderReuqestNum(mSerRequstNum);
                    }
                } else {
                    AttendAnimal attendAnimal = readyDataTag(details, requstNum);
                    if (AttendanceConstant.getDebug()) {
                        attendAnimal.setTest("on");
                    }
                    attendAnimal.setSize(new Gson().toJson(details).length());
                    //调用http方法
                    saveAttendances(new Gson().toJson(attendAnimal),
                            attendAnimal.getDetails().size(),
                            attendAnimal.getSerialRequestNum());
                }
            } else {
                senderReuqestNum(mSerRequstNum);
            }
        }
    }

    private void saveAttendances(String attend, int count, String requestSerialNum) {

        final HttpRequestModel httpRequestModel = new HttpRequestModel();
        httpRequestModel.requestStartTime = DataUtil.formatDate(System.currentTimeMillis());
        httpRequestModel.requestData = attend;
        httpRequestModel.mCount = count;
        httpRequestModel.serialRequestNum = requestSerialNum;
        RequestModel requestModel = new RequestModel();
        requestModel.setAttend(attend);
        requestModel.setCount(count);
        requestModel.setSerialRequestNum(requestSerialNum);
        requestModel.setHost(AttendanceConstant.getHOST());
        requestModel.setUrl(AttendanceConstant.getURL());
        requestModel.setAccessToken(AttendanceConstant.getAccessToken());
        if (attCompleteInnerCallback == null) {
            synchronized (SenderAnimal.class) {
                if (attCompleteInnerCallback == null) {
                    attCompleteInnerCallback = new AttCompleteInnerCallback(httpRequestModel);
                }
            }
        }
        AttendHttp.saveAttendances(requestModel, attCompleteInnerCallback);
    }

    /**
     * 接口请求回调
     */
    class AttCompleteInnerCallback implements AttCompleteCallback<RequestModel, ResponseResultModel> {

        HttpRequestModel mHttpRequestModel;

        private AttCompleteInnerCallback(HttpRequestModel httpRequestModel) {
            this.mHttpRequestModel = httpRequestModel;
        }

        @Override
        public void onSuccess(RequestModel request, ResponseResultModel result) {
            //判断回掉的requst如果正常则删除数据库
            mHttpRequestModel.requestStatus = "success";
            mHttpRequestModel.requestMsgOrCode = "0";
            long c = DataBootrapAnimal.getSingleton().deleteAttendance(mSerRequstNum);
            if (c > 0) {
                mHttpRequestModel.mDelCount = c;
                senderReuqestNum(null);
            } else {
                List<AttendAnimal.DetailsBean> details = readyData(mSerRequstNum);
                if (details.size() > 0) {
                    senderReuqestNum(mSerRequstNum);
                } else {
                    senderReuqestNum(null);
                }
            }

            putHttpRequestLogBean(mHttpRequestModel);
        }

        @Override
        public void onFailure(RequestModel request, ResponseResultModel result) {
            if (result.getCode() != ErrorCodeEnum.ERROR__0.getErrorCode() ||
                    result.getCode() != ErrorCodeEnum.ERROR__300.getErrorCode() ||
                    result.getCode() != ErrorCodeEnum.ERROR__30001.getErrorCode()) {
                //此处强制让程序停留10s再去请求接口
                WAIT_TIME = 10;
                hasAttend = false;
            } else {
                WAIT_TIME = 0;
            }
            if (result.getCode() == ErrorCodeEnum.ERROR__300.getErrorCode()
                    || result.getCode() == ErrorCodeEnum.ERROR__30001.getErrorCode()) {
                mHttpRequestModel.requestStatus = "repeat";
                mHttpRequestModel.requestMsgOrCode = result.getCode() + ":" + result.getMsg();
                long count = DataBootrapAnimal.getSingleton().deleteAttendance(mSerRequstNum);
                if (count > 0) {
                    mHttpRequestModel.mDelCount = count;
                    senderReuqestNum(null);
                } else {
                    mHttpRequestModel.mDelCount = count;
                    List<AttendAnimal.DetailsBean> details = readyData(mSerRequstNum);
                    if (details.size() > 0) {
                        senderReuqestNum(mSerRequstNum);
                    } else {
                        senderReuqestNum(null);
                    }
                }
            } else {
                mHttpRequestModel.requestStatus = "failed";
                mHttpRequestModel.requestMsgOrCode = result.getCode() + ":" + result.getMsg();
                DataBootrapAnimal.getSingleton().notifyDataPoolListenerEventUpdateToken(result.getCode());
                //如果不正常则把上次的senderReuqestNum传进去
                senderReuqestNum(mSerRequstNum);
            }
            putHttpRequestLogBean(mHttpRequestModel);
        }
    }

    /**
     * 打印 log
     *
     * @param str
     */
    private void logcat(String str) {
        if (AttendanceConstant.getDebug()) {
            Log.e(TAG, str);
        }
    }

}

