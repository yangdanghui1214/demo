package com.ydh.network.model;

/**
 * @author haru
 * @date 2017/9/14
 * 登录实体类
 */

public class LoginModel {


    /**
     * loginStatus : false
     * errCode : 0
     * currentToken:"AJuFFbojqNl1tnoLB8BKjF0OSZ0O2w4hEvwEsrNwA2GXwOyMpq6cQJ4X+4jwnwNw"
     * timeToLiveSeconds:7200
     * "loginTags": "group"
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
