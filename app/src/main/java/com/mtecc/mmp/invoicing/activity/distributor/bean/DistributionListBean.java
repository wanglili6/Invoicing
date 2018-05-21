package com.mtecc.mmp.invoicing.activity.distributor.bean;

import java.util.List;

/**
 * Created by wll on 2018/5/8.
 */

public class DistributionListBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * isgr : 0
         * cardcode : 131028199204232012
         * remark :
         * contactstel : 18811123516
         * merscope : 123123
         * createdate : {"nanos":0,"time":1524723231000,"minutes":13,"seconds":51,"hours":14,"month":3,"timezoneOffset":-480,"year":118,"day":4,"date":26}
         * mertype : 1
         * mername : 测试供货商
         * merid : 12
         * bankcard : 620345646549877
         * contacts : 123
         * createuser : 47
         * entregno : 12312123123123123
         * qqnum : 123123123123
         * email : 46546001@qq.com
         * address : 北京西西城区西街口外大街28号院
         * isdel : 1
         * wxnum : 123213123
         * updatedate : null
         */

        private String isgr;
        private String cardcode;
        private String remark;
        private String contactstel;
        private String merscope;
        private CreatedateBean createdate;
        private String mertype;
        private String mername;
        private int merid;
        private String bankcard;
        private String contacts;
        private int createuser;
        private String entregno;
        private String qqnum;
        private String email;
        private String address;
        private String isdel;
        private String wxnum;
        private Object updatedate;

        public String getIsgr() {
            return isgr;
        }

        public void setIsgr(String isgr) {
            this.isgr = isgr;
        }

        public String getCardcode() {
            return cardcode;
        }

        public void setCardcode(String cardcode) {
            this.cardcode = cardcode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getContactstel() {
            return contactstel;
        }

        public void setContactstel(String contactstel) {
            this.contactstel = contactstel;
        }

        public String getMerscope() {
            return merscope;
        }

        public void setMerscope(String merscope) {
            this.merscope = merscope;
        }

        public CreatedateBean getCreatedate() {
            return createdate;
        }

        public void setCreatedate(CreatedateBean createdate) {
            this.createdate = createdate;
        }

        public String getMertype() {
            return mertype;
        }

        public void setMertype(String mertype) {
            this.mertype = mertype;
        }

        public String getMername() {
            return mername;
        }

        public void setMername(String mername) {
            this.mername = mername;
        }

        public int getMerid() {
            return merid;
        }

        public void setMerid(int merid) {
            this.merid = merid;
        }

        public String getBankcard() {
            return bankcard;
        }

        public void setBankcard(String bankcard) {
            this.bankcard = bankcard;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public int getCreateuser() {
            return createuser;
        }

        public void setCreateuser(int createuser) {
            this.createuser = createuser;
        }

        public String getEntregno() {
            return entregno;
        }

        public void setEntregno(String entregno) {
            this.entregno = entregno;
        }

        public String getQqnum() {
            return qqnum;
        }

        public void setQqnum(String qqnum) {
            this.qqnum = qqnum;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIsdel() {
            return isdel;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        public String getWxnum() {
            return wxnum;
        }

        public void setWxnum(String wxnum) {
            this.wxnum = wxnum;
        }

        public Object getUpdatedate() {
            return updatedate;
        }

        public void setUpdatedate(Object updatedate) {
            this.updatedate = updatedate;
        }

        public static class CreatedateBean {
            /**
             * nanos : 0
             * time : 1524723231000
             * minutes : 13
             * seconds : 51
             * hours : 14
             * month : 3
             * timezoneOffset : -480
             * year : 118
             * day : 4
             * date : 26
             */

            private int nanos;
            private long time;
            private int minutes;
            private int seconds;
            private int hours;
            private int month;
            private int timezoneOffset;
            private int year;
            private int day;
            private int date;

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }
        }
    }
}
