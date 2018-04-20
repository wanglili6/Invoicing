package com.mtecc.mmp.invoicing.activity.comodity.bean;

import java.util.List;

/**
 * Created by wll on 2018/4/20.
 */

public class BatchBean {
    String batchName;
    List<String> imgUrl;

    public String getBatchName() {
        return batchName;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }
}
