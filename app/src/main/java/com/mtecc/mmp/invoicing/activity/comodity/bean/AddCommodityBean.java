package com.mtecc.mmp.invoicing.activity.comodity.bean;

/**
 * Created by wll on 2018/4/27.
 */

public class AddCommodityBean {
    String genre = "";//类型
    String name = "";//名字
    String code = "";//编码
    String barcode = "";//条形码
    String type = "";//类别
    String guige = "";//规格
    String danwei = "";//单位
    String baozhiqi = "";//保质期


    String commodityUser = "";//生产商
    String commodityUserAddress = "";//生产商地址
    String commodityUserCode = "";//生产商许可号
    String commodityUserbiao = "";//生产商标

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getBaozhiqi() {
        return baozhiqi;
    }

    public void setBaozhiqi(String baozhiqi) {
        this.baozhiqi = baozhiqi;
    }

    public String getCommodityUser() {
        return commodityUser;
    }

    public void setCommodityUser(String commodityUser) {
        this.commodityUser = commodityUser;
    }

    public String getCommodityUserAddress() {
        return commodityUserAddress;
    }

    public void setCommodityUserAddress(String commodityUserAddress) {
        this.commodityUserAddress = commodityUserAddress;
    }

    public String getCommodityUserCode() {
        return commodityUserCode;
    }

    public void setCommodityUserCode(String commodityUserCode) {
        this.commodityUserCode = commodityUserCode;
    }

    public String getCommodityUserbiao() {
        return commodityUserbiao;
    }

    public void setCommodityUserbiao(String commodityUserbiao) {
        this.commodityUserbiao = commodityUserbiao;
    }
}
