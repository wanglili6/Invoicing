package com.mtecc.mmp.invoicing.activity.shop.bean;

/**
 * Created by wll on 2018/4/24.
 */

public class ShopAddBean {
    private String shopname;//店铺名称
    private String shopnum;//店铺编号
    private String address;
    private String shopstate;
    private int shopid;

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getShopstate() {
        return shopstate;
    }

    public void setShopstate(String shopstate) {
        this.shopstate = shopstate;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopnum() {
        return shopnum;
    }

    public void setShopnum(String shopnum) {
        this.shopnum = shopnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
