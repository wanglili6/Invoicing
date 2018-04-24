package com.mtecc.mmp.invoicing.activity.shop.bean;

import java.util.List;

/**
 * Created by wll on 2018/4/24.
 */

public class ShopListBean {

    /**
     * limit : 20
     * count : 3
     * data : [{"shopid":1,"mancount":1,"shopstate":"0","shopnum":1,"createdate":"2018-04-18 15:03:12","shopname":"我家主店铺","cid":2,"createman":"李晓敏"},{"shopid":2,"mancount":0,"shopstate":"0","shopnum":2,"createdate":"2018-04-18 15:08:30","shopname":"我家店铺二","cid":2,"createman":"李晓敏"},{"shopid":3,"mancount":0,"shopstate":"0","shopnum":3,"createdate":"2018-04-18 15:09:08","shopname":"我家店铺三","cid":2,"createman":"李晓敏"}]
     * code : 0
     * msg :
     */

    private int limit;
    private int count;
    private String code;
    private String msg;
    private List<DataBean> data;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shopid : 1
         * mancount : 1
         * shopstate : 0
         * shopnum : 1
         * createdate : 2018-04-18 15:03:12
         * shopname : 我家主店铺
         * cid : 2
         * createman : 李晓敏
         */

        private int shopid;
        private int mancount;
        private String shopstate;
        private String shopaddress;
        private int shopnum;
        private String createdate;
        private String shopname;
        private int cid;
        private String createman;

        public String getShopaddress() {
            return shopaddress;
        }

        public void setShopaddress(String shopaddress) {
            this.shopaddress = shopaddress;
        }

        public int getShopid() {
            return shopid;
        }

        public void setShopid(int shopid) {
            this.shopid = shopid;
        }

        public int getMancount() {
            return mancount;
        }

        public void setMancount(int mancount) {
            this.mancount = mancount;
        }

        public String getShopstate() {
            return shopstate;
        }

        public void setShopstate(String shopstate) {
            this.shopstate = shopstate;
        }

        public int getShopnum() {
            return shopnum;
        }

        public void setShopnum(int shopnum) {
            this.shopnum = shopnum;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getCreateman() {
            return createman;
        }

        public void setCreateman(String createman) {
            this.createman = createman;
        }
    }
}
