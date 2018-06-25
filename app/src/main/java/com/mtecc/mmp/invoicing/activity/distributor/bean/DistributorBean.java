package com.mtecc.mmp.invoicing.activity.distributor.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wll on 2018/5/7.
 */

public class DistributorBean implements Serializable {

    private Integer merid;
    private Integer cid;
    private String mername;
    private String entregno;//营业执照号
    private String cardcode;//身份证
    private String contacts;//联系人
    private String contactstel;//联系人电话
    private String mertype;//类型： 1分销商 2进货商
    private String bankcard;//银行卡号
    private String address;//地址
    private String qqnum;//qq
    private String wxnum;//微信
    private String email;//邮箱
    private String isdel;//是否删除
    private String remark;//备注
    private String merscope;//经营范围
    private String isgr;//是否是个人
    private Integer createuser;//创建人//是否是个人
    private List<CardBean> cardBeanList;       //备注
    private Map<String, String> picmap;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Integer createuser) {
        this.createuser = createuser;
    }

    public List<CardBean> getCardBeanList() {
        return cardBeanList;
    }

    public void setCardBeanList(List<CardBean> cardBeanList) {
        this.cardBeanList = cardBeanList;
    }

    public Map<String, String> getPicmap() {
        return picmap;
    }

    public void setPicmap(Map<String, String> picmap) {
        this.picmap = picmap;
    }

    public Integer getMerid() {
        return merid;
    }

    public void setMerid(Integer merid) {
        this.merid = merid;
    }

    public String getMername() {
        return mername;
    }

    public void setMername(String mername) {
        this.mername = mername;
    }

    public String getEntregno() {
        return entregno;
    }

    public void setEntregno(String entregno) {
        this.entregno = entregno;
    }

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactstel() {
        return contactstel;
    }

    public void setContactstel(String contactstel) {
        this.contactstel = contactstel;
    }

    public String getMertype() {
        return mertype;
    }

    public void setMertype(String mertype) {
        this.mertype = mertype;
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQqnum() {
        return qqnum;
    }

    public void setQqnum(String qqnum) {
        this.qqnum = qqnum;
    }

    public String getWxnum() {
        return wxnum;
    }

    public void setWxnum(String wxnum) {
        this.wxnum = wxnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getMerscope() {
        return merscope;
    }

    public void setMerscope(String merscope) {
        this.merscope = merscope;
    }

    public String getIsgr() {
        return isgr;
    }

    public void setIsgr(String isgr) {
        this.isgr = isgr;
    }

    public static class CardBean implements Serializable {
        private Integer procardid;
        private String mercardnum;   //证件编号
        private String mercardtype;  //证件类型
        private String cardtypeName;  //证件类型
        private String enddate;   //有效期至
        private String isdel;        //是否删除（0不可用，1可用）
        private String remark;       //备注
        private String imgUrl;       //图片地址
        private String imgName;       //图片名字

        public String getImgName() {
            return imgName;
        }

        public void setImgName(String imgName) {
            this.imgName = imgName;
        }

        public Integer getProcardid() {
            return procardid;
        }

        public void setProcardid(Integer procardid) {
            this.procardid = procardid;
        }

        public String getMercardnum() {
            return mercardnum;
        }

        public void setMercardnum(String mercardnum) {
            this.mercardnum = mercardnum;
        }

        public String getMercardtype() {
            return mercardtype;
        }

        public void setMercardtype(String mercardtype) {
            this.mercardtype = mercardtype;
        }

        public String getCardtypeName() {
            return cardtypeName;
        }

        public void setCardtypeName(String cardtypeName) {
            this.cardtypeName = cardtypeName;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
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

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
