package com.mtecc.mmp.invoicing.activity.sales.bean;

import com.mtecc.mmp.invoicing.activity.purchase.bean.SelectBatchBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wll on 2018/5/22.
 */

public class SelectCommodityBean implements Serializable {
    private int proId;
    private String proName;
    private double mSelectMoney;
    private int mSelectNum;
    private String proCode;
    private String meas;
    private String meaunit;
    private List<SelectBatchBean> mSelectMap;

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
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

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
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

    public List<SelectBatchBean> getmSelectMap() {
        return mSelectMap;
    }

    public void setmSelectMap(List<SelectBatchBean> mSelectMap) {
        this.mSelectMap = mSelectMap;
    }
}
