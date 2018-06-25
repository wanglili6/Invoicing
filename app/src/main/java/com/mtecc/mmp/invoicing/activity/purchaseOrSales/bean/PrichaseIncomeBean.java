package com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wll on 2018/6/5.
 */

public class PrichaseIncomeBean implements Serializable{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * hddate : 2018-06-05 15:46:34
         * hdtitle : 不聊啦啊
         * state : 1
         * sum : 406.30
         * hdid : 201865346336
         * shopname : 我的店铺三
         * mername : 中国蒙牛有限公司
         */

        private String hddate;
        private String hdtitle;
        private String state;//1 已完成  0 未完成  2 待审核  3 不通过
        private String sum;
        private String hdid;
        private String shopid;
        private String shopname;
        private String mername;
        private String merid;

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getMerid() {
            return merid;
        }

        public void setMerid(String merid) {
            this.merid = merid;
        }

        public String getHddate() {
            return hddate;
        }

        public void setHddate(String hddate) {
            this.hddate = hddate;
        }

        public String getHdtitle() {
            return hdtitle;
        }

        public void setHdtitle(String hdtitle) {
            this.hdtitle = hdtitle;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public String getHdid() {
            return hdid;
        }

        public void setHdid(String hdid) {
            this.hdid = hdid;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getMername() {
            return mername;
        }

        public void setMername(String mername) {
            this.mername = mername;
        }
    }
}
