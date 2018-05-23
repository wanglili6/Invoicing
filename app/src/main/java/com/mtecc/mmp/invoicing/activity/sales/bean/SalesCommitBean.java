package com.mtecc.mmp.invoicing.activity.sales.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wll on 2018/5/22.
 */

public class SalesCommitBean implements Serializable {

    String salesCode = "";//订单号
    String salesShopId = "";//店铺id
    String salesShopName = "";//店铺id
    String salesUserId = "";//审核人
    String salesUserName = "";//审核人
    String salesMerchantId = "";//供货商
    String salesMerchantName = "";//供货商名字
    private List<SelectCommodityBean> mSelectCommodityMap;

    public String getSalesCode() {
        return salesCode;
    }

    public void setSalesCode(String salesCode) {
        this.salesCode = salesCode;
    }

    public String getSalesShopId() {
        return salesShopId;
    }

    public void setSalesShopId(String salesShopId) {
        this.salesShopId = salesShopId;
    }

    public String getSalesShopName() {
        return salesShopName;
    }

    public void setSalesShopName(String salesShopName) {
        this.salesShopName = salesShopName;
    }

    public String getSalesUserId() {
        return salesUserId;
    }

    public void setSalesUserId(String salesUserId) {
        this.salesUserId = salesUserId;
    }

    public String getSalesUserName() {
        return salesUserName;
    }

    public void setSalesUserName(String salesUserName) {
        this.salesUserName = salesUserName;
    }

    public String getSalesMerchantId() {
        return salesMerchantId;
    }

    public void setSalesMerchantId(String salesMerchantId) {
        this.salesMerchantId = salesMerchantId;
    }

    public String getSalesMerchantName() {
        return salesMerchantName;
    }

    public void setSalesMerchantName(String salesMerchantName) {
        this.salesMerchantName = salesMerchantName;
    }

    public List<SelectCommodityBean> getmSelectCommodityMap() {
        return mSelectCommodityMap;
    }

    public void setmSelectCommodityMap(List<SelectCommodityBean> mSelectCommodityMap) {
        this.mSelectCommodityMap = mSelectCommodityMap;
    }
}
