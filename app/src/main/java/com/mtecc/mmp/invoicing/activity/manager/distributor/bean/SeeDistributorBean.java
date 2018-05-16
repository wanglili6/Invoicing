package com.mtecc.mmp.invoicing.activity.manager.distributor.bean;

import java.util.List;

/**
 * Created by wll on 2018/5/14.
 */

public class SeeDistributorBean {

    /**
     * diclist : [{"dicid":48,"busintypename":"进销商证件照类型","BUSINNAME":"食品生产许可证","status":"0","busintypeid":"CompanyLience","BUSINID":1},{"dicid":49,"busintypename":"进销商证件照类型","BUSINNAME":"食品生产经营证","status":"0","busintypeid":"CompanyLience","BUSINID":2},{"dicid":50,"busintypename":"进销商证件照类型","BUSINNAME":"食品生产流通证","status":"0","busintypeid":"CompanyLience","BUSINID":3}]
     * data : [{"mercardnum":"566","mercardtype":"2","filecardid":{"filecardid":87,"remark":"","path":"247566.png","state":"1","uploaddate":{"nanos":0,"time":1526313600000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":15},"parentpath":"201805"},"enddateStr":"2018-05-15","createdate":null,"isdel":"1","mercardid":36,"enddate":{"nanos":0,"time":1526313600000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":15},"merid":{"isgr":"0","cardcode":"","remark":"","contactstel":"77777","merscope":"","createdate":{"nanos":0,"time":1526351670000,"minutes":34,"seconds":30,"hours":10,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":15},"mertype":"1","mername":"ceshi","merid":28,"bankcard":"1234","contacts":"再一次心跳加速","createuser":47,"entregno":"123","qqnum":"","email":"","address":"","isdel":"1","wxnum":"","updatedate":null}}]
     * chant : {"isgr":"0","cardcode":"","remark":"","contactstel":"77777","merscope":"","createdate":{"nanos":0,"time":1526351670000,"minutes":34,"seconds":30,"hours":10,"month":4,"year":118,"timezoneOffset":-480,"day":2,"date":15},"mertype":"1","mername":"ceshi","merid":28,"bankcard":"1234","contacts":"再一次心跳加速","createuser":47,"qqnum":"","entregno":"123","address":"","email":"","isdel":"1","wxnum":"","updatedate":null}
     */

    private ChantBean chant;
    private List<DiclistBean> diclist;
    private List<DataBean> data;

    public ChantBean getChant() {
        return chant;
    }

    public void setChant(ChantBean chant) {
        this.chant = chant;
    }

    public List<DiclistBean> getDiclist() {
        return diclist;
    }

    public void setDiclist(List<DiclistBean> diclist) {
        this.diclist = diclist;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ChantBean {
        /**
         * isgr : 0
         * cardcode :
         * remark :
         * contactstel : 77777
         * merscope :
         * createdate : {"nanos":0,"time":1526351670000,"minutes":34,"seconds":30,"hours":10,"month":4,"year":118,"timezoneOffset":-480,"day":2,"date":15}
         * mertype : 1
         * mername : ceshi
         * merid : 28
         * bankcard : 1234
         * contacts : 再一次心跳加速
         * createuser : 47
         * qqnum :
         * entregno : 123
         * address :
         * email :
         * isdel : 1
         * wxnum :
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
        private String qqnum;
        private String entregno;
        private String address;
        private String email;
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

        public String getQqnum() {
            return qqnum;
        }

        public void setQqnum(String qqnum) {
            this.qqnum = qqnum;
        }

        public String getEntregno() {
            return entregno;
        }

        public void setEntregno(String entregno) {
            this.entregno = entregno;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
             * time : 1526351670000
             * minutes : 34
             * seconds : 30
             * hours : 10
             * month : 4
             * year : 118
             * timezoneOffset : -480
             * day : 2
             * date : 15
             */

            private int nanos;
            private long time;
            private int minutes;
            private int seconds;
            private int hours;
            private int month;
            private int year;
            private int timezoneOffset;
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

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
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

    public static class DiclistBean {
        /**
         * dicid : 48
         * busintypename : 进销商证件照类型
         * BUSINNAME : 食品生产许可证
         * status : 0
         * busintypeid : CompanyLience
         * BUSINID : 1
         */

        private int dicid;
        private String busintypename;
        private String BUSINNAME;
        private String status;
        private String busintypeid;
        private int BUSINID;

        public int getDicid() {
            return dicid;
        }

        public void setDicid(int dicid) {
            this.dicid = dicid;
        }

        public String getBusintypename() {
            return busintypename;
        }

        public void setBusintypename(String busintypename) {
            this.busintypename = busintypename;
        }

        public String getBUSINNAME() {
            return BUSINNAME;
        }

        public void setBUSINNAME(String BUSINNAME) {
            this.BUSINNAME = BUSINNAME;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBusintypeid() {
            return busintypeid;
        }

        public void setBusintypeid(String busintypeid) {
            this.busintypeid = busintypeid;
        }

        public int getBUSINID() {
            return BUSINID;
        }

        public void setBUSINID(int BUSINID) {
            this.BUSINID = BUSINID;
        }
    }

    public static class DataBean {
        /**
         * mercardnum : 566
         * mercardtype : 2
         * filecardid : {"filecardid":87,"remark":"","path":"247566.png","state":"1","uploaddate":{"nanos":0,"time":1526313600000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":15},"parentpath":"201805"}
         * enddateStr : 2018-05-15
         * createdate : null
         * isdel : 1
         * mercardid : 36
         * enddate : {"nanos":0,"time":1526313600000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":15}
         * merid : {"isgr":"0","cardcode":"","remark":"","contactstel":"77777","merscope":"","createdate":{"nanos":0,"time":1526351670000,"minutes":34,"seconds":30,"hours":10,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":15},"mertype":"1","mername":"ceshi","merid":28,"bankcard":"1234","contacts":"再一次心跳加速","createuser":47,"entregno":"123","qqnum":"","email":"","address":"","isdel":"1","wxnum":"","updatedate":null}
         */

        private String mercardnum;
        private String mercardtype;
        private FilecardidBean filecardid;
        private String enddateStr;
        private Object createdate;
        private String isdel;
        private int mercardid;
        private EnddateBean enddate;
        private MeridBean merid;

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

        public FilecardidBean getFilecardid() {
            return filecardid;
        }

        public void setFilecardid(FilecardidBean filecardid) {
            this.filecardid = filecardid;
        }

        public String getEnddateStr() {
            return enddateStr;
        }

        public void setEnddateStr(String enddateStr) {
            this.enddateStr = enddateStr;
        }

        public Object getCreatedate() {
            return createdate;
        }

        public void setCreatedate(Object createdate) {
            this.createdate = createdate;
        }

        public String getIsdel() {
            return isdel;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        public int getMercardid() {
            return mercardid;
        }

        public void setMercardid(int mercardid) {
            this.mercardid = mercardid;
        }

        public EnddateBean getEnddate() {
            return enddate;
        }

        public void setEnddate(EnddateBean enddate) {
            this.enddate = enddate;
        }

        public MeridBean getMerid() {
            return merid;
        }

        public void setMerid(MeridBean merid) {
            this.merid = merid;
        }

        public static class FilecardidBean {
            /**
             * filecardid : 87
             * remark :
             * path : 247566.png
             * state : 1
             * uploaddate : {"nanos":0,"time":1526313600000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":15}
             * parentpath : 201805
             */

            private int filecardid;
            private String remark;
            private String path;
            private String state;
            private UploaddateBean uploaddate;
            private String parentpath;

            public int getFilecardid() {
                return filecardid;
            }

            public void setFilecardid(int filecardid) {
                this.filecardid = filecardid;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public UploaddateBean getUploaddate() {
                return uploaddate;
            }

            public void setUploaddate(UploaddateBean uploaddate) {
                this.uploaddate = uploaddate;
            }

            public String getParentpath() {
                return parentpath;
            }

            public void setParentpath(String parentpath) {
                this.parentpath = parentpath;
            }

            public static class UploaddateBean {
                /**
                 * nanos : 0
                 * time : 1526313600000
                 * minutes : 0
                 * seconds : 0
                 * hours : 0
                 * month : 4
                 * timezoneOffset : -480
                 * year : 118
                 * day : 2
                 * date : 15
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

        public static class EnddateBean {
            /**
             * nanos : 0
             * time : 1526313600000
             * minutes : 0
             * seconds : 0
             * hours : 0
             * month : 4
             * timezoneOffset : -480
             * year : 118
             * day : 2
             * date : 15
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

        public static class MeridBean {
            /**
             * isgr : 0
             * cardcode :
             * remark :
             * contactstel : 77777
             * merscope :
             * createdate : {"nanos":0,"time":1526351670000,"minutes":34,"seconds":30,"hours":10,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":15}
             * mertype : 1
             * mername : ceshi
             * merid : 28
             * bankcard : 1234
             * contacts : 再一次心跳加速
             * createuser : 47
             * entregno : 123
             * qqnum :
             * email :
             * address :
             * isdel : 1
             * wxnum :
             * updatedate : null
             */

            private String isgr;
            private String cardcode;
            private String remark;
            private String contactstel;
            private String merscope;
            private CreatedateBeanX createdate;
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

            public CreatedateBeanX getCreatedate() {
                return createdate;
            }

            public void setCreatedate(CreatedateBeanX createdate) {
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

            public static class CreatedateBeanX {
                /**
                 * nanos : 0
                 * time : 1526351670000
                 * minutes : 34
                 * seconds : 30
                 * hours : 10
                 * month : 4
                 * timezoneOffset : -480
                 * year : 118
                 * day : 2
                 * date : 15
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
}
