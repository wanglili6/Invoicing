package com.mtecc.mmp.invoicing.activity.purchase.bean;

import java.io.Serializable;

/**
 * Created by wll on 2018/5/18.
 */

public class SelectBatchBean implements Serializable {
    private int pbatchid;
    private String enterprice;
    private String batchnum;
    private int num;

    public String getBatchnum() {
        return batchnum;
    }

    public void setBatchnum(String batchnum) {
        this.batchnum = batchnum;
    }

    public int getPbatchid() {
        return pbatchid;
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
