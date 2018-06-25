package com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean;

import java.util.List;

/**
 * Created by wll on 2018/6/11.
 */

public class ReturnListBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * threason : 啊实打实
         * sumprice : 16
         * proname : 豆角
         * sumqty : 2
         * barcode : 585869
         * hdid : 201867151141
         * proid : 16
         * goodsBacth : [{"batchdate":"2018-05-31","thprice":"8","batchnum":"2569bb","thqty":"1"},{"batchdate":"2018-05-31","thprice":"8","batchnum":"2569bb","thqty":"1"}]
         * thdate : 2018-06-08
         * procode :
         */

        private String threason;
        private String sumprice;
        private String proname;
        private String sumqty;
        private String barcode;
        private String hdid;
        private int proid;
        private String thdate;
        private String procode;
        private String mername;
        private List<GoodsBacthBean> goodsBacth;

        public String getSumprice() {
            return sumprice;
        }

        public void setSumprice(String sumprice) {
            this.sumprice = sumprice;
        }

        public String getSumqty() {
            return sumqty;
        }

        public void setSumqty(String sumqty) {
            this.sumqty = sumqty;
        }

        public String getMername() {
            return mername;
        }

        public void setMername(String mername) {
            this.mername = mername;
        }

        public String getThreason() {
            return threason;
        }

        public void setThreason(String threason) {
            this.threason = threason;
        }



        public String getProname() {
            return proname;
        }

        public void setProname(String proname) {
            this.proname = proname;
        }


        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getHdid() {
            return hdid;
        }

        public void setHdid(String hdid) {
            this.hdid = hdid;
        }

        public int getProid() {
            return proid;
        }

        public void setProid(int proid) {
            this.proid = proid;
        }

        public String getThdate() {
            return thdate;
        }

        public void setThdate(String thdate) {
            this.thdate = thdate;
        }

        public String getProcode() {
            return procode;
        }

        public void setProcode(String procode) {
            this.procode = procode;
        }

        public List<GoodsBacthBean> getGoodsBacth() {
            return goodsBacth;
        }

        public void setGoodsBacth(List<GoodsBacthBean> goodsBacth) {
            this.goodsBacth = goodsBacth;
        }

        public static class GoodsBacthBean {
            /**
             * batchdate : 2018-05-31
             * thprice : 8
             * batchnum : 2569bb
             * thqty : 1
             */

            private String batchdate;
            private String thprice;
            private String batchnum;
            private String thqty;
            private String threason;
            private String thdate;

            public String getThreason() {
                return threason;
            }

            public void setThreason(String threason) {
                this.threason = threason;
            }

            public String getThdate() {
                return thdate;
            }

            public void setThdate(String thdate) {
                this.thdate = thdate;
            }

            public String getBatchdate() {
                return batchdate;
            }

            public void setBatchdate(String batchdate) {
                this.batchdate = batchdate;
            }

            public String getThprice() {
                return thprice;
            }

            public void setThprice(String thprice) {
                this.thprice = thprice;
            }

            public String getBatchnum() {
                return batchnum;
            }

            public void setBatchnum(String batchnum) {
                this.batchnum = batchnum;
            }

            public String getThqty() {
                return thqty;
            }

            public void setThqty(String thqty) {
                this.thqty = thqty;
            }
        }
    }
}
