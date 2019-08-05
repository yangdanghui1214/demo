package com.ydh.network;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface BaseApiService {


    /**
     * 终端登录
     *
     * @param mac
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<DeviceLoginModel> login(@Field("deviceMac") String mac);


    /**
     * 获取绑定的部门列表考勤统计
     *
     * @param date
     * @param time
     * @return
     */
    @FormUrlEncoded
    @POST("attendance/getDepartmentAttendResultCount")
    Observable<ResponseResult<ResponseDepartmentAttendResult>> getDepartmentAttendResultCount(@Field("date") String date,
                                                                                              @Field("time") String time);

}
