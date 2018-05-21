package com.mtecc.mmp.invoicing.activity.purchase.bean;

import com.mtecc.mmp.invoicing.activity.shop.bean.ShopListBean;

import java.util.List;

/**
 * Created by wll on 2018/5/16.
 */

public class PurchaseListBean {
    private Integer id;
    private String type;
    private String name;
    private String code;
    private String timer;
    private String money;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }
}
