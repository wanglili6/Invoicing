package com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean;

import java.util.List;

/**
 * Created by wll on 2018/6/8.
 */

public class SelectCommodityBean {
    private List<SelectBatchBean> mSelectMap;
    private double mSelectMoney;
    private int mSelectNum;
    private String remark;
    private String probzq;
    private String state;
    private String proCode;
    private String barCode;
    private String protype;
    private String bzqunit;
    private String mernameorplace;
    private String trademark;
    private String meas;
    private String meaunit;
    private String batchCount;
    private String proName;
    private int proId;
    private int sid;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public List<SelectBatchBean> getmSelectMap() {
        return mSelectMap;
    }

    public void setmSelectMap(List<SelectBatchBean> mSelectMap) {
        this.mSelectMap = mSelectMap;
    }

    public double getmSelectMoney() {
        return mSelectMoney;
    }

    public void setmSelectMoney(double mSelectMoney) {
        this.mSelectMoney = mSelectMoney;
    }

    public int getmSelectNum() {
        return mSelectNum;
    }

    public void setmSelectNum(int mSelectNum) {
        this.mSelectNum = mSelectNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProbzq() {
        return probzq;
    }

    public void setProbzq(String probzq) {
        this.probzq = probzq;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }



    public String getProtype() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype = protype;
    }

    public String getBzqunit() {
        return bzqunit;
    }

    public void setBzqunit(String bzqunit) {
        this.bzqunit = bzqunit;
    }

    public String getMernameorplace() {
        return mernameorplace;
    }

    public void setMernameorplace(String mernameorplace) {
        this.mernameorplace = mernameorplace;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getMeas() {
        return meas;
    }

    public void setMeas(String meas) {
        this.meas = meas;
    }

    public String getMeaunit() {
        return meaunit;
    }

    public void setMeaunit(String meaunit) {
        this.meaunit = meaunit;
    }

    public String getBatchCount() {
        return batchCount;
    }

    public void setBatchCount(String batchCount) {
        this.batchCount = batchCount;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }
}
