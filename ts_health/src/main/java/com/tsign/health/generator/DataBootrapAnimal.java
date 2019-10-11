package com.tsign.health.generator;

import com.litesuits.orm.db.assit.WhereBuilder;
import com.tsign.health.helper.AnimalHelper;
import com.tsign.health.helper.AnimalHelperLock;
import com.tsign.health.listener.TokenUpdateListener;
import com.tsign.health.model.AttendAnimal;
import com.tsign.health.model.HttpRequestModel;
import com.tsign.health.sender.SenderAnimal;
import com.tsign.health.util.AttLiteOrm;
import com.tsign.health.util.Singleton;

import java.util.List;
import java.util.Random;

/**
 * 幼儿园项目使用
 *
 * @author amosLv
 * @date 2019/03/06
 */
public class DataBootrapAnimal {

    private TokenUpdateListener tokenUpdateListener;

    private AnimalHelperLock animalHelperLock;

    public static DataBootrapAnimal getSingleton() {
        return Singleton.getSingleton(DataBootrapAnimal.class);
    }


    public DataBootrapAnimal registerTokenListener(TokenUpdateListener tokenUpdateListener) {
        getSingleton().tokenUpdateListener = tokenUpdateListener;
        return getSingleton();
    }

    public void notifyDataPoolListenerEventUpdateToken(int code) {
        getSingleton().tokenUpdateListener.sendError(code);
    }

    /**
     * 开始测试
     */
    public DataBootrapAnimal init(TokenUpdateListener tokenUpdateListener) {
        getSingleton().registerTokenListener(tokenUpdateListener);
        getSingleton().animalHelperLock = new AnimalHelperLock(new AnimalHelper());
        return getSingleton();
    }

    public String getRandomString(int length) {
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 这里可以是获取limit=20 serirequesNum =nulll 的考勤数据
     * 无论获取什么样的考勤数据，建议都是一个方法
     * 而不是直接提供数据库的入口
     *
     * @return
     */
    public List<AttendAnimal.DetailsBean> getAttenDances() {

        return getSingleton().animalHelperLock.get();
    }

    public String getSerialRequestNum() {
        return getSingleton().animalHelperLock.getSerialRequestNum();
    }

    public List<AttendAnimal.DetailsBean> getAttenDances(String requstNum) {

        return getSingleton().animalHelperLock.get(requstNum);
    }


    public void putAttenDances(List<AttendAnimal.DetailsBean> list) {
        getSingleton().animalHelperLock.putAttendance(list);
    }


    public void updateAttenDances(List<AttendAnimal.DetailsBean> list) {
        getSingleton().animalHelperLock.update(list);
    }


    /**
     * 以下要补充sender里面的方法
     */

    public void senderReuqestNum(String serRequstNum) {
        SenderAnimal.getSingleton().senderReuqestNum(serRequstNum);
    }

    public long deleteAttendance(List<AttendAnimal.DetailsBean> detailsBeans) {
        return getSingleton().animalHelperLock.delete(detailsBeans);
    }

    public long deleteAttendance(String requstNum) {
        WhereBuilder whereAttend = new WhereBuilder(AttendAnimal.DetailsBean.class);
        whereAttend.where("serialRequestNum = ?", new String[]{requstNum});
        long c = AttLiteOrm.getAttLiteOrm().single().delete(whereAttend);
        return c;
    }

    /**
     * 查询本地时候有考勤数据
     *
     * @return
     */
    public long countAttendance() {
        return AttLiteOrm.getAttLiteOrm().single().queryCount(AttendAnimal.DetailsBean.class);
    }

    /**
     * 保存请求失败记录
     *
     * @param httpRequestModel
     */
    public void putHttpRequestLogBean(HttpRequestModel httpRequestModel) {
        getSingleton().animalHelperLock.putHttpRequestLogBean(httpRequestModel);
    }

}
