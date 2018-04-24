package com.mtecc.mmp.invoicing.activity.setting.bean;

/**
 * Created by wll on 2018/4/23.
 */

public class CompanyInfoBean {
    private String cname;//企业名称
    private String clicence;//营业执照号
    private String jjfw;//经营范围
    private String qyfr;//企业法人
    private String c_address;//经营地址
    private int cid;
    private String enddateStr;//有效期至
    private String state;//状态

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setClicence(String clicence) {
        this.clicence = clicence;
    }

    public void setJjfw(String jjfw) {
        this.jjfw = jjfw;
    }

    public void setQyfr(String qyfr) {
        this.qyfr = qyfr;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public void setEnddateStr(String enddateStr) {
        this.enddateStr = enddateStr;
    }

    public String getCname() {
        return cname;
    }

    public String getClicence() {
        return clicence;
    }

    public String getJjfw() {
        return jjfw;
    }

    public String getQyfr() {
        return qyfr;
    }

    public String getC_address() {
        return c_address;
    }

    public String getEnddateStr() {
        return enddateStr;
    }
}
