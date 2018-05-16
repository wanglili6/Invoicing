package com.mtecc.mmp.invoicing.activity.approve;

/**
 * Created by wll on 2018/5/16.
 */

public class CompanyBean {
    private Integer cid;
    private String cname;
    private String clicence;//营业执照号
    private String jjfw;//经营范围
    private String qyfr;//企业法人
    private String address;//经营地址
    private String enddate;//有效期至
    private String state;//0表正常，其他表不正常
    private String filecardname;//照片名

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getClicence() {
        return clicence;
    }

    public void setClicence(String clicence) {
        this.clicence = clicence;
    }

    public String getJjfw() {
        return jjfw;
    }

    public void setJjfw(String jjfw) {
        this.jjfw = jjfw;
    }

    public String getQyfr() {
        return qyfr;
    }

    public void setQyfr(String qyfr) {
        this.qyfr = qyfr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFilecardname() {
        return filecardname;
    }

    public void setFilecardname(String filecardname) {
        this.filecardname = filecardname;
    }
}
