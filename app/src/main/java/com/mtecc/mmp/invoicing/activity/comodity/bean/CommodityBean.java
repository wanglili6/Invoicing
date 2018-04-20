package com.mtecc.mmp.invoicing.activity.comodity.bean;

import java.util.List;

/**
 * Created by wll on 2018/4/19.
 */

public class CommodityBean {
    String title;
    List<String> nameList;

    public String getTitle() {
        return title;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }
}
