package com.mtecc.mmp.invoicing.activity.comodity.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wll on 2018/4/20.
 */

public class BatchBean implements Serializable {
    String batchstartTimer;//生产日期
    String batchnum;//批号
    String batchlShouji;//零售价
    String batchjHuojia;//进货价
    String batchpfajia;//批发价
    List<BatchPicBean> picList;//批发价

    public String getBatchstartTimer() {
        return batchstartTimer;
    }

    public void setBatchstartTimer(String batchstartTimer) {
        this.batchstartTimer = batchstartTimer;
    }

    public String getBatchnum() {
        return batchnum;
    }

    public void setBatchnum(String batchnum) {
        this.batchnum = batchnum;
    }

    public String getBatchlShouji() {
        return batchlShouji;
    }

    public void setBatchlShouji(String batchlShouji) {
        this.batchlShouji = batchlShouji;
    }

    public String getBatchjHuojia() {
        return batchjHuojia;
    }

    public void setBatchjHuojia(String batchjHuojia) {
        this.batchjHuojia = batchjHuojia;
    }

    public String getBatchpfajia() {
        return batchpfajia;
    }

    public void setBatchpfajia(String batchpfajia) {
        this.batchpfajia = batchpfajia;
    }

    public List<BatchPicBean> getPicList() {
        return picList;
    }

    public void setPicList(List<BatchPicBean> picList) {
        this.picList = picList;
    }





}
