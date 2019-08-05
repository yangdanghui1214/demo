package com.ydh.network;

class DeviceLoginModel {

    /**
     * msg : 登录失败,设备未入库
     * code : 0
     *        0对应设备未入库, 1对应设备已经入库但是尚未绑定教室
     * data : {"loginStatus":false,"errCode":0}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * loginStatus : false
         * errCode : 0
         * currentToken:"AJuFFbojqNl1tnoLB8BKjF0OSZ0O2w4hEvwEsrNwA2GXwOyMpq6cQJ4X+4jwnwNw"
         * timeToLiveSeconds:7200
         *  "loginTags": "group"
         */

        private boolean loginStatus;
        private int errCode;
        private String currentToken;
        private long timeToLiveSeconds;
        private String loginTags;

        public boolean isLoginStatus() {
            return loginStatus;
        }

        public void setLoginStatus(boolean loginStatus) {
            this.loginStatus = loginStatus;
        }

        public int getErrCode() {
            return errCode;
        }

        public void setErrCode(int errCode) {
            this.errCode = errCode;
        }

        public String getCurrentToken() {
            return currentToken;
        }

        public void setCurrentToken(String currentToken) {
            this.currentToken = currentToken;
        }

        public long getTimeToLiveSeconds() {
            return timeToLiveSeconds;
        }

        public void setTimeToLiveSeconds(long timeToLiveSeconds) {
            this.timeToLiveSeconds = timeToLiveSeconds;
        }

        public String getLoginTags() {
            return loginTags;
        }

        public void setLoginTags(String loginTags) {
            this.loginTags = loginTags;
        }
    }

    @Override
    public String toString() {
        return "DeviceLoginModel{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
