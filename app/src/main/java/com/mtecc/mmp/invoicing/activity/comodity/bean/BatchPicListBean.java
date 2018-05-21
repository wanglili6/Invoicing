package com.mtecc.mmp.invoicing.activity.comodity.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wll on 2018/5/7.
 */

public class BatchPicListBean implements Serializable {
    private Integer pbatchid;
    private String batchdate;      //批次
    private String batchnum;     //批号
    private String sellprice;    //零售价
    private String enterprice;   //进货价
    private String saleprice;    //批发价
    private String isdel;        //是否删除（0不可用，1可用）
    private String remark;       //备注
    private List<CardBean> cardBeanList;       //备注
    private Map<String,String> picmap;

    public List<CardBean> getCardBeanList() {
        return cardBeanList;
    }

    public void setCardBeanList(List<CardBean> cardBeanList) {
        this.cardBeanList = cardBeanList;
    }

    public Map<String, String> getpicmap() {
        return picmap;
    }

    public void setpicmap(Map<String, String> picmap) {
        this.picmap = picmap;
    }

    public Integer getPbatchid() {
        return pbatchid;
    }

    public void setPbatchid(Integer pbatchid) {
        this.pbatchid = pbatchid;
    }

    public String getBatchdate() {
        return batchdate;
    }

    public void setBatchdate(String batchdate) {
        this.batchdate = batchdate;
    }

    public String getBatchnum() {
        return batchnum;
    }

    public void setBatchnum(String batchnum) {
        this.batchnum = batchnum;
    }

    public String getSellprice() {
        return sellprice;
    }

    public void setSellprice(String sellprice) {
        this.sellprice = sellprice;
    }

    public String getEnterprice() {
        return enterprice;
    }

    public void setEnterprice(String enterprice) {
        this.enterprice = enterprice;
    }

    public String getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<CardBean> getCardBeanlist() {
        return cardBeanList;
    }

    public void setCardBeanlist(List<CardBean> cardBeanList) {
        this.cardBeanList = cardBeanList;
    }

    public static class CardBean implements Serializable {
        private Integer procardid;
        private String cardnum;   //证件编号
        private String cardtype;  //证件类型
        private String cardtypeName;  //证件类型
        private String charddate;   //有效期至
        private String isdel;        //是否删除（0不可用，1可用）
        private String remark;       //备注
        private String imgUrl;       //图片地址
              //图片地址

        public String getCardtypeName() {
            return cardtypeName;
        }

        public void setCardtypeName(String cardtypeName) {
            this.cardtypeName = cardtypeName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public Integer getProcardid() {
            return procardid;
        }

        public void setProcardid(Integer procardid) {
            this.procardid = procardid;
        }

        public String getCardnum() {
            return cardnum;
        }

        public void setCardnum(String cardnum) {
            this.cardnum = cardnum;
        }

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public String getCharddate() {
            return charddate;
        }

        public void setCharddate(String charddate) {
            this.charddate = charddate;
        }

        public String getIsdel() {
            return isdel;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
