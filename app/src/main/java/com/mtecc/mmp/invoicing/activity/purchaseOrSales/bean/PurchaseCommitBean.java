package com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by wll on 2018/5/22.
 */

public class PurchaseCommitBean implements Serializable {

    String purchaseShopId;//店铺id
    String purchaseShopName;//店铺id
    String purchaseUserId;//采购人
    String purchaseUserName;//采购人
    String purchaseMerchantId;//供货商
    String purchaseMerchantName;//供货商名字
    String purchaseHdtitle;//货单描述
    String incomeShopId;//店铺id
    String incomeShopName;//店铺id
    String outShopId;//店铺id
    String outShopName;//店铺id
    String outReason;//描述
    String bhid;//删除的时候用
    String hdid;//货单id
    String purchaseState;// 1系统未开启  2 开启审核
    String purchaseHdtype;// 1进货  2 销货

    public void setHdid(String hdid) {
        this.hdid = hdid;
    }

    public String getHdid() {
        return hdid;
    }

    public String getPurchaseHdtype() {
        return purchaseHdtype;
    }

    public void setPurchaseHdtype(String purchaseHdtype) {
        this.purchaseHdtype = purchaseHdtype;
    }



    public String getBhid() {
        return bhid;
    }

    public void setBhid(String bhid) {
        this.bhid = bhid;
    }

    public String getPurchaseState() {
        return purchaseState;
    }

    public void setPurchaseState(String purchaseState) {
        this.purchaseState = purchaseState;
    }

    public String getOutReason() {
        return outReason;
    }

    public void setOutReason(String outReason) {
        this.outReason = outReason;
    }

    public String getIncomeShopId() {
        return incomeShopId;
    }

    public void setIncomeShopId(String incomeShopId) {
        this.incomeShopId = incomeShopId;
    }

    public String getIncomeShopName() {
        return incomeShopName;
    }

    public void setIncomeShopName(String incomeShopName) {
        this.incomeShopName = incomeShopName;
    }

    public String getOutShopId() {
        return outShopId;
    }

    public void setOutShopId(String outShopId) {
        this.outShopId = outShopId;
    }

    public String getOutShopName() {
        return outShopName;
    }

    public void setOutShopName(String outShopName) {
        this.outShopName = outShopName;
    }

    private List<SelectCommodityBean> mSelectCommodityMap;

    public String getPurchaseHdtitle() {
        return purchaseHdtitle;
    }

    public void setPurchaseHdtitle(String purchaseHdtitle) {
        this.purchaseHdtitle = purchaseHdtitle;
    }

    public String getPurchaseShopId() {
        return purchaseShopId;
    }

    public void setPurchaseShopId(String purchaseShopId) {
        this.purchaseShopId = purchaseShopId;
    }

    public String getPurchaseShopName() {
        return purchaseShopName;
    }

    public void setPurchaseShopName(String purchaseShopName) {
        this.purchaseShopName = purchaseShopName;
    }

    public String getPurchaseUserId() {
        return purchaseUserId;
    }

    public void setPurchaseUserId(String purchaseUserId) {
        this.purchaseUserId = purchaseUserId;
    }

    public String getPurchaseUserName() {
        return purchaseUserName;
    }

    public void setPurchaseUserName(String purchaseUserName) {
        this.purchaseUserName = purchaseUserName;
    }

    public String getPurchaseMerchantId() {
        return purchaseMerchantId;
    }

    public void setPurchaseMerchantId(String purchaseMerchantId) {
        this.purchaseMerchantId = purchaseMerchantId;
    }

    public String getPurchaseMerchantName() {
        return purchaseMerchantName;
    }

    public void setPurchaseMerchantName(String purchaseMerchantName) {
        this.purchaseMerchantName = purchaseMerchantName;
    }

    public List<SelectCommodityBean> getmSelectCommodityMap() {
        return mSelectCommodityMap;
    }

    public void setmSelectCommodityMap(List<SelectCommodityBean> mSelectCommodityMap) {
        this.mSelectCommodityMap = mSelectCommodityMap;
    }
}
