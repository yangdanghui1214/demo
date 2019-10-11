package com.tsign.health.helper;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.tsign.health.constant.AttendanceConstant;
import com.tsign.health.model.AttendAnimal;
import com.tsign.health.model.HttpRequestModel;
import com.tsign.health.util.AttLiteOrm;

import java.util.List;

/**
 * 健康数据 基础操作类
 * @author 13001
 */
public class AnimalHelper {

    /**
     * @return 获取考勤数据
     */
    public List<AttendAnimal.DetailsBean> getAttendacne() {
        QueryBuilder<AttendAnimal.DetailsBean> qbAttend =
                new QueryBuilder<>(AttendAnimal.DetailsBean.class)
                        .where("serialRequestNum = ?", new String[]{"1"})
                        .limit(0, 100);
        return AttLiteOrm.getAttLiteOrm().single().query(qbAttend);
    }

    /**
     * 根据 requstNum  获取健康数据
     * @return 获取健康数据
     */
    public List<AttendAnimal.DetailsBean> getAttendacne(String requstNum) {
        if (requstNum == null) {
            requstNum = getSerialRequestNum();
        }
        QueryBuilder<AttendAnimal.DetailsBean> qbAttend = new
                QueryBuilder<>(AttendAnimal.DetailsBean.class)
                .where("serialRequestNum = ?", new String[]{requstNum})
                .limit(0, 50);
        return AttLiteOrm.getAttLiteOrm().single().query(qbAttend);
    }

    /**
     * 获取数据库中已有的serialRequestNum
     *
     * @return
     */
    public String getSerialRequestNum() {
        String requstNum;
        AttendAnimal.DetailsBean detailsBean = AttLiteOrm.getAttLiteOrm()
                .query(AttendAnimal.DetailsBean.class).get(0);
        if (detailsBean != null) {
            requstNum = detailsBean.getSerialRequestNum();
        } else {
            requstNum = "1";
        }
        return requstNum;
    }

    /**
     * 保存考勤数据
     *
     * @param list
     */
    public void putAttendacne(List<AttendAnimal.DetailsBean> list) {
        //调整之前的逻辑, 对考勤的数据先进行数据库保存, 用定时器定时去上传。
        AttLiteOrm.getAttLiteOrm().single().save(list);
        AttendanceConstant.WhetherAttendNeedUpload = true;
    }

    /**
     * 保存考勤数据
     *
     * @param detailsBean
     */
    public void putAttendacne(AttendAnimal.DetailsBean detailsBean) {
        //调整之前的逻辑, 对考勤的数据先进行数据库保存, 用定时器定时去上传。
        AttLiteOrm.getAttLiteOrm().single().save(detailsBean);
        AttendanceConstant.WhetherAttendNeedUpload = true;
    }

    /**
     * 保存考勤数据
     *
     * @param list
     */
    public void updateAttendacne(List<AttendAnimal.DetailsBean> list) {
        AttLiteOrm.getAttLiteOrm().single().update(list);
    }

    /**
     * 删除考勤数据
     *
     * @param detailsBeans
     */
    public long deleteAttendance(List<AttendAnimal.DetailsBean> detailsBeans) {
        return AttLiteOrm.getAttLiteOrm().single().delete(detailsBeans);
    }


    /**
     * 删除考勤数据
     *
     * @param requstNum
     */
    public long deleteAttendance(String requstNum, String tag) {
        WhereBuilder whereAttend = new WhereBuilder(AttendAnimal.DetailsBean.class);
        whereAttend.where("serialRequestNum = ?", new String[]{requstNum});
        return (long) AttLiteOrm.getAttLiteOrm().single().delete(whereAttend);
    }

    /**
     * 保存 请求失败的msg
     * @param httpRequestModel
     */
    public void putHttpRequestLogBean(HttpRequestModel httpRequestModel) {
        AttLiteOrm.getAttLiteOrm().single().save(httpRequestModel);
    }

}
