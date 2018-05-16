package com.mtecc.mmp.invoicing.activity.manager.comodity.bean;

import java.util.List;

/**
 * Created by wll on 2018/4/19.
 */

public class CommodityBean {
    private Integer proId;
    private String proCode;//商品编码
    private String proName;//商品名称
    private String barcode;//条形码
    private String meas;   //规格
    private String obtype; //商品类别
    private String protype;//商品类型（0散装，1预包装）
    private String trademark;//商品品牌
    private String meaunit;  //单位
    private String mernameorplace;//生产商产地
    private String probzq;  //保质期
    private String bzqunit;  //保质期单位
    private String remark;  //备注
    private String state;   //状态 （0不可用，1可用）

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getMeas() {
        return meas;
    }

    public void setMeas(String meas) {
        this.meas = meas;
    }

    public String getObtype() {
        return obtype;
    }

    public void setObtype(String obtype) {
        this.obtype = obtype;
    }

    public String getProtype() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype = protype;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getMeaunit() {
        return meaunit;
    }

    public void setMeaunit(String meaunit) {
        this.meaunit = meaunit;
    }

    public String getMernameorplace() {
        return mernameorplace;
    }

    public void setMernameorplace(String mernameorplace) {
        this.mernameorplace = mernameorplace;
    }

    public String getProbzq() {
        return probzq;
    }

    public void setProbzq(String probzq) {
        this.probzq = probzq;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBzqunit() {
        return bzqunit;
    }

    public void setBzqunit(String bzqunit) {
        this.bzqunit = bzqunit;
    }
}
