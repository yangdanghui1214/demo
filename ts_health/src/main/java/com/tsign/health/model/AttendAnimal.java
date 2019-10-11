package com.tsign.health.model;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.util.List;

public class AttendAnimal {


    private List<DetailsBean> details;

    private String serialRequestNum;

    private String test;

    private int size;


    public AttendAnimal(List<DetailsBean> details, String serialRequestNum) {
        this.details = details;
        this.serialRequestNum = serialRequestNum;
    }

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public String getSerialRequestNum() {
        return serialRequestNum;
    }

    public void setSerialRequestNum(String serialRequestNum) {
        this.serialRequestNum = serialRequestNum;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 体温上传失败时会保存到本地数据库
     */
    @Table("animalUpload")
    public static class DetailsBean {
        /**
         * userId : 34
         * value : http://oss.tingshan.cn/Android/terminal/attend/1531553014841.jpg
         * date : 2018-06-26 10:00:00
         */

        @PrimaryKey(AssignType.AUTO_INCREMENT)
        private int id;
        private String userId;
        private String value;
        private String date;
        private String serialNum;
        private String serialRequestNum;
        private String type;

        /**
         * 幼儿园闸机使用
         */
        private String url;

        public DetailsBean() {

        }

        public DetailsBean(String userId, String value, String date, String serialNum, String serialRequestNum, String type) {
            this.userId = userId;
            this.value = value;
            this.date = date;
            this.serialNum = serialNum;
            this.serialRequestNum = serialRequestNum;
            this.type = type;
        }

        /**
         * 仅做区分value不用传
         */
        public DetailsBean(String userId, String url, String date, String serialNum, String serialRequestNum, String type, String value) {
            this.userId = userId;
            this.url = url;
            this.date = date;
            this.serialNum = serialNum;
            this.serialRequestNum = serialRequestNum;
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSerialNum() {
            return serialNum;
        }

        public void setSerialNum(String serialNum) {
            this.serialNum = serialNum;
        }

        public String getSerialRequestNum() {
            return serialRequestNum;
        }

        public void setSerialRequestNum(String serialRequestNum) {
            this.serialRequestNum = serialRequestNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "DetailsBean{" +
                    "id=" + id +
                    ", userId='" + userId + '\'' +
                    ", value='" + value + '\'' +
                    ", date='" + date + '\'' +
                    ", serialNum='" + serialNum + '\'' +
                    ", serialRequestNum='" + serialRequestNum + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AttendAnimal{" +
                "details=" + details +
                ", serialRequestNum='" + serialRequestNum + '\'' +
                ", test='" + test + '\'' +
                ", size=" + size +
                '}';
    }
}
