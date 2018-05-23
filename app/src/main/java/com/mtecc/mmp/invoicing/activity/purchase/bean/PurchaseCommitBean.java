package com.mtecc.mmp.invoicing.activity.purchase.bean;

import com.mtecc.mmp.invoicing.activity.sales.bean.SelectCommodityBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wll on 2018/5/22.
 */

public class PurchaseCommitBean implements Serializable {

    String purchaseCode = "";//订单号
    String purchaseShopId = "";//店铺id
    String purchaseShopName = "";//店铺id
    String purchaseUserId = "";//审核人
    String purchaseUserName = "";//审核人
    String purchaseMerchantId = "";//供货商
    String purchaseMerchantName = "";//供货商名字
    private List<SelectCommodityBean> mSelectCommodityMap;

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
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
