package com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean;

import java.io.Serializable;

/**
 * Created by wll on 2018/5/18.
 */

public class SelectBatchBean implements Serializable {
    private String batchdate;      //批次
    private String batchnum;     //批号
    private String sellprice;    //零售价
    private String enterprice;   //进货价
    private String saleprice;    //批发价
    private String isdel;        //是否删除（0不可用，1可用）
    private String remark;       //备注
    private String kcsum;       //剩余库存
    private String cgNum;       //剩余库存
    private int  bhid;       //
    private int num;
    private int sid;

    public String getCgNum() {
        return cgNum;
    }

    public void setCgNum(String cgNum) {
        this.cgNum = cgNum;
    }

    public int getBhid() {
        return bhid;
    }

    public void setBhid(int bhid) {
        this.bhid = bhid;
    }

    private int pbatchid;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getKcsum() {
        return kcsum;
    }

    public void setKcsum(String kcsum) {
        this.kcsum = kcsum;
    }

    public int getPbatchid() {
        return pbatchid;
    }

    public String getBatchdate() {
        return batchdate;
    }

    public void setBatchdate(String batchdate) {
        this.batchdate = batchdate;
    }

    public String getSellprice() {
        return sellprice;
    }

    public void setSellprice(String sellprice) {
        this.sellprice = sellprice;
    }

    public String getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getBatchnum() {
        return batchnum;
    }

    public void setBatchnum(String batchnum) {
        this.batchnum = batchnum;
    }


    public void setPbatchid(int pbatchid) {
        this.pbatchid = pbatchid;
    }

    public String getEnterprice() {
        return enterprice;
    }

    public void setEnterprice(String enterprice) {
        this.enterprice = enterprice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
