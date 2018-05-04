package com.mtecc.mmp.invoicing.activity.comodity.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wll on 2018/5/3.
 */

public  class BatchPicBean implements Serializable {
    String batchcarType;//证件类型
    String batchcode;//证件号
    String batchtimer;//有效期至
    List<String> imgUrl;//证件地址

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
