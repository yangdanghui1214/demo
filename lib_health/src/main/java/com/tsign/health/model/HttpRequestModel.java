package com.tsign.health.model;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * @author xhj
 * @date 2018/8/16
 */
@Table("HttpRequestModel")
public class HttpRequestModel {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public int id;
    public String requestStartTime;
    public String requestData;
    public String requestStatus;
    public String requestMsgOrCode;
    public String serialRequestNum;
    public int mCount;
    public long mDelCount;

    public HttpRequestModel() {
    }
}
