package com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean;

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
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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
