package com.mtecc.mmp.invoicing.activity.check.bean;

import java.util.List;

/**
 * Created by wll on 2018/5/23.
 */

public class SeeOrdersBean {

    /**
     * mSelectCommodityMap : [{"mSelectMap":[{"batchnum":"12312","enterprice":"12","num":1,"pbatchid":6}],"mSelectMoney":12,"mSelectNum":1,"proId":9,"proName":"白砂糖"},{"mSelectMap":[{"batchnum":"36","enterprice":"25","num":1,"pbatchid":11},{"batchnum":"3","enterprice":"9","num":1,"pbatchid":20},{"batchnum":"777uuu","enterprice":"1","num":6,"pbatchid":23},{"batchnum":"36","enterprice":"5","num":1,"pbatchid":12}],"mSelectMoney":45,"mSelectNum":9,"proId":14,"proName":"牛奶"},{"mSelectMap":[{"batchnum":"","enterprice":"5.89","num":1,"pbatchid":25},{"batchnum":"111","enterprice":"122","num":1,"pbatchid":28}],"mSelectMoney":127.89,"mSelectNum":2,"proId":15,"proName":"可乐"},{"mSelectMap":[{"batchnum":"2569bb","enterprice":"8.9","num":4,"pbatchid":32}],"mSelectMoney":35.6,"mSelectNum":4,"proId":16,"proName":"豆角"},{"mSelectMap":[{"batchnum":"696987412","enterprice":"5","num":3,"pbatchid":21}],"mSelectMoney":15,"mSelectNum":3,"proId":12,"proName":"瓜子"}]
     * purchaseCode : CGJH21527062433118
     * purchaseMerchantId : 1
     * purchaseMerchantName : 中国蒙牛有限公司
     * purchaseShopId : 2
     * purchaseShopName : 我家店铺二
     * purchaseUserId : 56
     * purchaseUserName : 王丽丽
     */

    private String purchaseCode;
    private String purchaseMerchantId;
    private String purchaseMerchantName;
    private String purchaseShopId;
    private String purchaseShopName;
    private String purchaseUserId;
    private String purchaseUserName;
    private List<MSelectCommodityMapBean> mSelectCommodityMap;

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
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

    public List<MSelectCommodityMapBean> getMSelectCommodityMap() {
        return mSelectCommodityMap;
    }

    public void setMSelectCommodityMap(List<MSelectCommodityMapBean> mSelectCommodityMap) {
        this.mSelectCommodityMap = mSelectCommodityMap;
    }

    public static class MSelectCommodityMapBean {
        /**
         * mSelectMap : [{"batchnum":"12312","enterprice":"12","num":1,"pbatchid":6}]
         * mSelectMoney : 12.0
         * mSelectNum : 1
         * proId : 9
         * proName : 白砂糖
         */

        private double mSelectMoney;
        private int mSelectNum;
        private int proId;
        private String proName;
        private String proCode;
        private String meas;
        private String meaunit;
        private List<MSelectMapBean> mSelectMap;

        public double getmSelectMoney() {
            return mSelectMoney;
        }

        public void setmSelectMoney(double mSelectMoney) {
            this.mSelectMoney = mSelectMoney;
        }

        public int getmSelectNum() {
            return mSelectNum;
        }

        public void setmSelectNum(int mSelectNum) {
            this.mSelectNum = mSelectNum;
        }

        public List<MSelectMapBean> getmSelectMap() {
            return mSelectMap;
        }

        public void setmSelectMap(List<MSelectMapBean> mSelectMap) {
            this.mSelectMap = mSelectMap;
        }

        public String getProCode() {
            return proCode;
        }

        public void setProCode(String proCode) {
            this.proCode = proCode;
        }

        public String getMeas() {
            return meas;
        }

        public void setMeas(String meas) {
            this.meas = meas;
        }

        public String getMeaunit() {
            return meaunit;
        }

        public void setMeaunit(String meaunit) {
            this.meaunit = meaunit;
        }

        public double getMSelectMoney() {
            return mSelectMoney;
        }

        public void setMSelectMoney(double mSelectMoney) {
            this.mSelectMoney = mSelectMoney;
        }

        public int getMSelectNum() {
            return mSelectNum;
        }

        public void setMSelectNum(int mSelectNum) {
            this.mSelectNum = mSelectNum;
        }

        public int getProId() {
            return proId;
        }

        public void setProId(int proId) {
            this.proId = proId;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public List<MSelectMapBean> getMSelectMap() {
            return mSelectMap;
        }

        public void setMSelectMap(List<MSelectMapBean> mSelectMap) {
            this.mSelectMap = mSelectMap;
        }

        public static class MSelectMapBean {
            /**
             * batchnum : 12312
             * enterprice : 12
             * num : 1
             * pbatchid : 6
             */

            private String batchnum;
            private String enterprice;
            private int num;
            private int pbatchid;

            public String getBatchnum() {
                return batchnum;
            }

            public void setBatchnum(String batchnum) {
                this.batchnum = batchnum;
            }

            public String getEnterprice() {
                return enterprice;
            }

            public void setEnterprice(String enterprice) {
                this.enterprice = enterprice;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getPbatchid() {
                return pbatchid;
            }

            public void setPbatchid(int pbatchid) {
                this.pbatchid = pbatchid;
            }
        }
    }
}
