package com.mtecc.mmp.invoicing.activity.check.bean;

/**
 * Created by wll on 2018/6/12.
 */

public class AuditBean {
    private Integer auditid;
    private String hdid;
    private Integer auditman;
    private String auditresult;
    private String time;
    private String auditmess;
    private String timeStr;

    public Integer getAuditid() {
        return auditid;
    }

    public void setAuditid(Integer auditid) {
        this.auditid = auditid;
    }

    public String getHdid() {
        return hdid;
    }

    public void setHdid(String hdid) {
        this.hdid = hdid;
    }

    public Integer getAuditman() {
        return auditman;
    }

    public void setAuditman(Integer auditman) {
        this.auditman = auditman;
    }

    public String getAuditresult() {
        return auditresult;
    }

    public void setAuditresult(String auditresult) {
        this.auditresult = auditresult;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuditmess() {
        return auditmess;
    }

    public void setAuditmess(String auditmess) {
        this.auditmess = auditmess;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }
}


