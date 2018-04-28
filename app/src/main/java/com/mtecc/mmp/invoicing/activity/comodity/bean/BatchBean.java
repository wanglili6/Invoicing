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
    String batchcarType;//证件类型
    String batchcode;//证件号
    String batchtimer;//有效期至
    List<String> imgUrl;//证件地址

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

    public String getBatchcarType() {
        return batchcarType;
    }

    public void setBatchcarType(String batchcarType) {
        this.batchcarType = batchcarType;
    }

    public String getBatchcode() {
        return batchcode;
    }

    public void setBatchcode(String batchcode) {
        this.batchcode = batchcode;
    }

    public String getBatchtimer() {
        return batchtimer;
    }

    public void setBatchtimer(String batchtimer) {
        this.batchtimer = batchtimer;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }
}
