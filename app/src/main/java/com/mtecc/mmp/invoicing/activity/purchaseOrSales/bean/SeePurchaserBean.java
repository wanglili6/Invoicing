package com.mtecc.mmp.invoicing.activity.purchaseOrSales.bean;

import java.util.List;

/**
 * Created by wll on 2018/6/5.
 */

public class SeePurchaserBean {

    private List<DataBean> data;
    private List<AuditBean> audit;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<AuditBean> getAudit() {
        return audit;
    }

    public void setAudit(List<AuditBean> audit) {
        this.audit = audit;
    }

    public static class DataBean {
        /**
         * good : {"proId":15,"remark":"","bzqunit":"1","probzq":"25","userid":{"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"filecardid":{"remark":"","filecardid":116,"state":"1","path":"certification.png","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":16},"parentpath":"201805"},"address":"北京","clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","jjfw":"软件工程","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","address":"北京西城","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"year":118,"timezoneOffset":-480,"day":2,"date":8},"email":"1050507901@qq.com","role":""},"state":"1","createdate":{"nanos":0,"time":1526227200000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":1,"date":14},"proCode":"898881","barcode":"111111","protype":"0","mernameorplace":"667","proName":"可乐","trademark":"可口可乐","meas":"ML","obtype":{"content":"食用植物油","grade":"3","code":"020101","parentCode":{"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"},"isValid":"1"},"updatedate":null,"batchCount":0,"meaunit":"78"}
         * pclist : [{"pbatchid":{"pbatchid":27,"batchdate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"saleprice":"","remark":"","batchnum":"23652","createdate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"batchdateStr":"2018-05-16","proName":"可乐","isdel":"1","proid":{"proId":15,"remark":"","bzqunit":"1","probzq":"25","userid":{"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"北京","filecardid":{"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"},"clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"软件工程","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8},"address":"北京西城","role":""},"state":"1","createdate":{"nanos":0,"time":1526227200000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":1,"date":14},"proCode":"898881","barcode":"111111","protype":"0","mernameorplace":"667","proName":"可乐","trademark":"可口可乐","meas":"ML","obtype":{"content":"食用植物油","grade":"3","code":"020101","parentCode":{"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"},"isValid":"1"},"updatedate":null,"batchCount":0,"meaunit":"78"},"sellprice":"","updatedate":null,"enterprice":"5.6"},"enterqty":"1","goodid":{"proId":15,"remark":"","bzqunit":"1","probzq":"25","userid":{"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"北京","filecardid":{"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"},"clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"软件工程","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8},"address":"北京西城","role":""},"state":"1","createdate":{"nanos":0,"time":1526227200000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":1,"date":14},"proCode":"898881","barcode":"111111","protype":"0","mernameorplace":"667","proName":"可乐","trademark":"可口可乐","meas":"ML","obtype":{"content":"食用植物油","grade":"3","code":"020101","parentCode":{"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"},"isValid":"1"},"updatedate":null,"batchCount":0,"meaunit":"78"},"bhid":89,"hdid":"2018612114001","enterprice":"30"}]
         */

        private GoodBean good;
        private List<PclistBean> pclist;

        public GoodBean getGood() {
            return good;
        }

        public void setGood(GoodBean good) {
            this.good = good;
        }

        public List<PclistBean> getPclist() {
            return pclist;
        }

        public void setPclist(List<PclistBean> pclist) {
            this.pclist = pclist;
        }

        public static class GoodBean {
            /**
             * proId : 15
             * remark :
             * bzqunit : 1
             * probzq : 25
             * userid : {"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"filecardid":{"remark":"","filecardid":116,"state":"1","path":"certification.png","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":16},"parentpath":"201805"},"address":"北京","clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","jjfw":"软件工程","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","address":"北京西城","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"year":118,"timezoneOffset":-480,"day":2,"date":8},"email":"1050507901@qq.com","role":""}
             * state : 1
             * createdate : {"nanos":0,"time":1526227200000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":1,"date":14}
             * proCode : 898881
             * barcode : 111111
             * protype : 0
             * mernameorplace : 667
             * proName : 可乐
             * trademark : 可口可乐
             * meas : ML
             * obtype : {"content":"食用植物油","grade":"3","code":"020101","parentCode":{"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"},"isValid":"1"}
             * updatedate : null
             * batchCount : 0
             * meaunit : 78
             */

            private int proId;
            private String remark;
            private String bzqunit;
            private String probzq;
            private UseridBean userid;
            private String state;
            private CreatedateBean createdate;
            private String proCode;
            private String barcode;
            private String protype;
            private String mernameorplace;
            private String proName;
            private String trademark;
            private String meas;
            private ObtypeBean obtype;
            private Object updatedate;
            private int batchCount;
            private String meaunit;

            public int getProId() {
                return proId;
            }

            public void setProId(int proId) {
                this.proId = proId;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getBzqunit() {
                return bzqunit;
            }

            public void setBzqunit(String bzqunit) {
                this.bzqunit = bzqunit;
            }

            public String getProbzq() {
                return probzq;
            }

            public void setProbzq(String probzq) {
                this.probzq = probzq;
            }

            public UseridBean getUserid() {
                return userid;
            }

            public void setUserid(UseridBean userid) {
                this.userid = userid;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public CreatedateBean getCreatedate() {
                return createdate;
            }

            public void setCreatedate(CreatedateBean createdate) {
                this.createdate = createdate;
            }

            public String getProCode() {
                return proCode;
            }

            public void setProCode(String proCode) {
                this.proCode = proCode;
            }

            public String getBarcode() {
                return barcode;
            }

            public void setBarcode(String barcode) {
                this.barcode = barcode;
            }

            public String getProtype() {
                return protype;
            }

            public void setProtype(String protype) {
                this.protype = protype;
            }

            public String getMernameorplace() {
                return mernameorplace;
            }

            public void setMernameorplace(String mernameorplace) {
                this.mernameorplace = mernameorplace;
            }

            public String getProName() {
                return proName;
            }

            public void setProName(String proName) {
                this.proName = proName;
            }

            public String getTrademark() {
                return trademark;
            }

            public void setTrademark(String trademark) {
                this.trademark = trademark;
            }

            public String getMeas() {
                return meas;
            }

            public void setMeas(String meas) {
                this.meas = meas;
            }

            public ObtypeBean getObtype() {
                return obtype;
            }

            public void setObtype(ObtypeBean obtype) {
                this.obtype = obtype;
            }

            public Object getUpdatedate() {
                return updatedate;
            }

            public void setUpdatedate(Object updatedate) {
                this.updatedate = updatedate;
            }

            public int getBatchCount() {
                return batchCount;
            }

            public void setBatchCount(int batchCount) {
                this.batchCount = batchCount;
            }

            public String getMeaunit() {
                return meaunit;
            }

            public void setMeaunit(String meaunit) {
                this.meaunit = meaunit;
            }

            public static class UseridBean {
                /**
                 * sex : 1
                 * userage : 25
                 * empstate : 0
                 * cardnum : 612724199107190760
                 * roleid :
                 * userid : 47
                 * creatdateStr : 2018-05-08
                 * shopname :
                 * cid : {"filecardid":{"remark":"","filecardid":116,"state":"1","path":"certification.png","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":16},"parentpath":"201805"},"address":"北京","clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","jjfw":"软件工程","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"普众科技（北京）有限公司"}
                 * logname : lixm
                 * password : MDAwMDAw
                 * telphone : 18810580240
                 * username : 李晓敏宝宝
                 * address : 北京西城
                 * creatdate : {"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"year":118,"timezoneOffset":-480,"day":2,"date":8}
                 * email : 1050507901@qq.com
                 * role :
                 */

                private String sex;
                private String userage;
                private String empstate;
                private String cardnum;
                private String roleid;
                private int userid;
                private String creatdateStr;
                private String shopname;
                private CidBean cid;
                private String logname;
                private String password;
                private String telphone;
                private String username;
                private String address;
                private CreatdateBean creatdate;
                private String email;
                private String role;

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getUserage() {
                    return userage;
                }

                public void setUserage(String userage) {
                    this.userage = userage;
                }

                public String getEmpstate() {
                    return empstate;
                }

                public void setEmpstate(String empstate) {
                    this.empstate = empstate;
                }

                public String getCardnum() {
                    return cardnum;
                }

                public void setCardnum(String cardnum) {
                    this.cardnum = cardnum;
                }

                public String getRoleid() {
                    return roleid;
                }

                public void setRoleid(String roleid) {
                    this.roleid = roleid;
                }

                public int getUserid() {
                    return userid;
                }

                public void setUserid(int userid) {
                    this.userid = userid;
                }

                public String getCreatdateStr() {
                    return creatdateStr;
                }

                public void setCreatdateStr(String creatdateStr) {
                    this.creatdateStr = creatdateStr;
                }

                public String getShopname() {
                    return shopname;
                }

                public void setShopname(String shopname) {
                    this.shopname = shopname;
                }

                public CidBean getCid() {
                    return cid;
                }

                public void setCid(CidBean cid) {
                    this.cid = cid;
                }

                public String getLogname() {
                    return logname;
                }

                public void setLogname(String logname) {
                    this.logname = logname;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public String getTelphone() {
                    return telphone;
                }

                public void setTelphone(String telphone) {
                    this.telphone = telphone;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public CreatdateBean getCreatdate() {
                    return creatdate;
                }

                public void setCreatdate(CreatdateBean creatdate) {
                    this.creatdate = creatdate;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getRole() {
                    return role;
                }

                public void setRole(String role) {
                    this.role = role;
                }

                public static class CidBean {
                    /**
                     * filecardid : {"remark":"","filecardid":116,"state":"1","path":"certification.png","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":16},"parentpath":"201805"}
                     * address : 北京
                     * clicence : 987654321252525
                     * enddateStr : 2018-04-23
                     * state : 0
                     * qyfr : 刘万荣
                     * jjfw : 软件工程
                     * enddate : {"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23}
                     * cid : 2
                     * cname : 普众科技（北京）有限公司
                     */

                    private FilecardidBean filecardid;
                    private String address;
                    private String clicence;
                    private String enddateStr;
                    private String state;
                    private String qyfr;
                    private String jjfw;
                    private EnddateBean enddate;
                    private int cid;
                    private String cname;

                    public FilecardidBean getFilecardid() {
                        return filecardid;
                    }

                    public void setFilecardid(FilecardidBean filecardid) {
                        this.filecardid = filecardid;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getClicence() {
                        return clicence;
                    }

                    public void setClicence(String clicence) {
                        this.clicence = clicence;
                    }

                    public String getEnddateStr() {
                        return enddateStr;
                    }

                    public void setEnddateStr(String enddateStr) {
                        this.enddateStr = enddateStr;
                    }

                    public String getState() {
                        return state;
                    }

                    public void setState(String state) {
                        this.state = state;
                    }

                    public String getQyfr() {
                        return qyfr;
                    }

                    public void setQyfr(String qyfr) {
                        this.qyfr = qyfr;
                    }

                    public String getJjfw() {
                        return jjfw;
                    }

                    public void setJjfw(String jjfw) {
                        this.jjfw = jjfw;
                    }

                    public EnddateBean getEnddate() {
                        return enddate;
                    }

                    public void setEnddate(EnddateBean enddate) {
                        this.enddate = enddate;
                    }

                    public int getCid() {
                        return cid;
                    }

                    public void setCid(int cid) {
                        this.cid = cid;
                    }

                    public String getCname() {
                        return cname;
                    }

                    public void setCname(String cname) {
                        this.cname = cname;
                    }

                    public static class FilecardidBean {
                        /**
                         * remark :
                         * filecardid : 116
                         * state : 1
                         * path : certification.png
                         * uploaddate : {"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":16}
                         * parentpath : 201805
                         */

                        private String remark;
                        private int filecardid;
                        private String state;
                        private String path;
                        private UploaddateBean uploaddate;
                        private String parentpath;

                        public String getRemark() {
                            return remark;
                        }

                        public void setRemark(String remark) {
                            this.remark = remark;
                        }

                        public int getFilecardid() {
                            return filecardid;
                        }

                        public void setFilecardid(int filecardid) {
                            this.filecardid = filecardid;
                        }

                        public String getState() {
                            return state;
                        }

                        public void setState(String state) {
                            this.state = state;
                        }

                        public String getPath() {
                            return path;
                        }

                        public void setPath(String path) {
                            this.path = path;
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
                             * time : 1526400000000
                             * minutes : 0
                             * seconds : 0
                             * hours : 0
                             * month : 4
                             * year : 118
                             * timezoneOffset : -480
                             * day : 3
                             * date : 16
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

                    public static class EnddateBean {
                        /**
                         * nanos : 0
                         * time : 1524412800000
                         * minutes : 0
                         * seconds : 0
                         * hours : 0
                         * month : 3
                         * year : 118
                         * timezoneOffset : -480
                         * day : 1
                         * date : 23
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

                public static class CreatdateBean {
                    /**
                     * nanos : 0
                     * time : 1525751418000
                     * minutes : 50
                     * seconds : 18
                     * hours : 11
                     * month : 4
                     * year : 118
                     * timezoneOffset : -480
                     * day : 2
                     * date : 8
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

            public static class CreatedateBean {
                /**
                 * nanos : 0
                 * time : 1526227200000
                 * minutes : 0
                 * seconds : 0
                 * hours : 0
                 * month : 4
                 * year : 118
                 * timezoneOffset : -480
                 * day : 1
                 * date : 14
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

            public static class ObtypeBean {
                /**
                 * content : 食用植物油
                 * grade : 3
                 * code : 020101
                 * parentCode : {"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"}
                 * isValid : 1
                 */

                private String content;
                private String grade;
                private String code;
                private ParentCodeBeanX parentCode;
                private String isValid;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getGrade() {
                    return grade;
                }

                public void setGrade(String grade) {
                    this.grade = grade;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public ParentCodeBeanX getParentCode() {
                    return parentCode;
                }

                public void setParentCode(ParentCodeBeanX parentCode) {
                    this.parentCode = parentCode;
                }

                public String getIsValid() {
                    return isValid;
                }

                public void setIsValid(String isValid) {
                    this.isValid = isValid;
                }

                public static class ParentCodeBeanX {
                    /**
                     * content : 食用植物油
                     * grade : 2
                     * code : 0201
                     * parentCode : {"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"}
                     * isValid : 1
                     */

                    private String content;
                    private String grade;
                    private String code;
                    private ParentCodeBean parentCode;
                    private String isValid;

                    public String getContent() {
                        return content;
                    }

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public String getGrade() {
                        return grade;
                    }

                    public void setGrade(String grade) {
                        this.grade = grade;
                    }

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public ParentCodeBean getParentCode() {
                        return parentCode;
                    }

                    public void setParentCode(ParentCodeBean parentCode) {
                        this.parentCode = parentCode;
                    }

                    public String getIsValid() {
                        return isValid;
                    }

                    public void setIsValid(String isValid) {
                        this.isValid = isValid;
                    }

                    public static class ParentCodeBean {
                        /**
                         * content : 食用油、油脂及其制品
                         * grade : 1
                         * code : 02
                         * parentCode : null
                         * isValid : 1
                         */

                        private String content;
                        private String grade;
                        private String code;
                        private Object parentCode;
                        private String isValid;

                        public String getContent() {
                            return content;
                        }

                        public void setContent(String content) {
                            this.content = content;
                        }

                        public String getGrade() {
                            return grade;
                        }

                        public void setGrade(String grade) {
                            this.grade = grade;
                        }

                        public String getCode() {
                            return code;
                        }

                        public void setCode(String code) {
                            this.code = code;
                        }

                        public Object getParentCode() {
                            return parentCode;
                        }

                        public void setParentCode(Object parentCode) {
                            this.parentCode = parentCode;
                        }

                        public String getIsValid() {
                            return isValid;
                        }

                        public void setIsValid(String isValid) {
                            this.isValid = isValid;
                        }
                    }
                }
            }
        }

        public static class PclistBean {
            /**
             * pbatchid : {"pbatchid":27,"batchdate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"saleprice":"","remark":"","batchnum":"23652","createdate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"batchdateStr":"2018-05-16","proName":"可乐","isdel":"1","proid":{"proId":15,"remark":"","bzqunit":"1","probzq":"25","userid":{"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"北京","filecardid":{"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"},"clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"软件工程","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8},"address":"北京西城","role":""},"state":"1","createdate":{"nanos":0,"time":1526227200000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":1,"date":14},"proCode":"898881","barcode":"111111","protype":"0","mernameorplace":"667","proName":"可乐","trademark":"可口可乐","meas":"ML","obtype":{"content":"食用植物油","grade":"3","code":"020101","parentCode":{"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"},"isValid":"1"},"updatedate":null,"batchCount":0,"meaunit":"78"},"sellprice":"","updatedate":null,"enterprice":"5.6"}
             * enterqty : 1
             * goodid : {"proId":15,"remark":"","bzqunit":"1","probzq":"25","userid":{"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"北京","filecardid":{"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"},"clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"软件工程","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8},"address":"北京西城","role":""},"state":"1","createdate":{"nanos":0,"time":1526227200000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":1,"date":14},"proCode":"898881","barcode":"111111","protype":"0","mernameorplace":"667","proName":"可乐","trademark":"可口可乐","meas":"ML","obtype":{"content":"食用植物油","grade":"3","code":"020101","parentCode":{"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"},"isValid":"1"},"updatedate":null,"batchCount":0,"meaunit":"78"}
             * bhid : 89
             * hdid : 2018612114001
             * enterprice : 30
             */

            private PbatchidBean pbatchid;
            private String enterqty;
            private GoodidBean goodid;
            private int bhid;
            private String hdid;
            private String kcsum;
            private String enterprice;

            public String getKcsum() {
                return kcsum;
            }

            public void setKcsum(String kcsum) {
                this.kcsum = kcsum;
            }

            public PbatchidBean getPbatchid() {
                return pbatchid;
            }

            public void setPbatchid(PbatchidBean pbatchid) {
                this.pbatchid = pbatchid;
            }

            public String getEnterqty() {
                return enterqty;
            }

            public void setEnterqty(String enterqty) {
                this.enterqty = enterqty;
            }

            public GoodidBean getGoodid() {
                return goodid;
            }

            public void setGoodid(GoodidBean goodid) {
                this.goodid = goodid;
            }

            public int getBhid() {
                return bhid;
            }

            public void setBhid(int bhid) {
                this.bhid = bhid;
            }

            public String getHdid() {
                return hdid;
            }

            public void setHdid(String hdid) {
                this.hdid = hdid;
            }

            public String getEnterprice() {
                return enterprice;
            }

            public void setEnterprice(String enterprice) {
                this.enterprice = enterprice;
            }

            public static class PbatchidBean {
                /**
                 * pbatchid : 27
                 * batchdate : {"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16}
                 * saleprice :
                 * remark :
                 * batchnum : 23652
                 * createdate : {"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16}
                 * batchdateStr : 2018-05-16
                 * proName : 可乐
                 * isdel : 1
                 * proid : {"proId":15,"remark":"","bzqunit":"1","probzq":"25","userid":{"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"北京","filecardid":{"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"},"clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"软件工程","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8},"address":"北京西城","role":""},"state":"1","createdate":{"nanos":0,"time":1526227200000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":1,"date":14},"proCode":"898881","barcode":"111111","protype":"0","mernameorplace":"667","proName":"可乐","trademark":"可口可乐","meas":"ML","obtype":{"content":"食用植物油","grade":"3","code":"020101","parentCode":{"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"},"isValid":"1"},"updatedate":null,"batchCount":0,"meaunit":"78"}
                 * sellprice :
                 * updatedate : null
                 * enterprice : 5.6
                 */

                private int pbatchid;
                private BatchdateBean batchdate;
                private String saleprice;
                private String remark;
                private String batchnum;
                private CreatedateBeanX createdate;
                private String batchdateStr;
                private String proName;
                private String isdel;
                private ProidBean proid;
                private String sellprice;
                private Object updatedate;
                private String enterprice;

                public int getPbatchid() {
                    return pbatchid;
                }

                public void setPbatchid(int pbatchid) {
                    this.pbatchid = pbatchid;
                }

                public BatchdateBean getBatchdate() {
                    return batchdate;
                }

                public void setBatchdate(BatchdateBean batchdate) {
                    this.batchdate = batchdate;
                }

                public String getSaleprice() {
                    return saleprice;
                }

                public void setSaleprice(String saleprice) {
                    this.saleprice = saleprice;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getBatchnum() {
                    return batchnum;
                }

                public void setBatchnum(String batchnum) {
                    this.batchnum = batchnum;
                }

                public CreatedateBeanX getCreatedate() {
                    return createdate;
                }

                public void setCreatedate(CreatedateBeanX createdate) {
                    this.createdate = createdate;
                }

                public String getBatchdateStr() {
                    return batchdateStr;
                }

                public void setBatchdateStr(String batchdateStr) {
                    this.batchdateStr = batchdateStr;
                }

                public String getProName() {
                    return proName;
                }

                public void setProName(String proName) {
                    this.proName = proName;
                }

                public String getIsdel() {
                    return isdel;
                }

                public void setIsdel(String isdel) {
                    this.isdel = isdel;
                }

                public ProidBean getProid() {
                    return proid;
                }

                public void setProid(ProidBean proid) {
                    this.proid = proid;
                }

                public String getSellprice() {
                    return sellprice;
                }

                public void setSellprice(String sellprice) {
                    this.sellprice = sellprice;
                }

                public Object getUpdatedate() {
                    return updatedate;
                }

                public void setUpdatedate(Object updatedate) {
                    this.updatedate = updatedate;
                }

                public String getEnterprice() {
                    return enterprice;
                }

                public void setEnterprice(String enterprice) {
                    this.enterprice = enterprice;
                }

                public static class BatchdateBean {
                    /**
                     * nanos : 0
                     * time : 1526400000000
                     * minutes : 0
                     * seconds : 0
                     * hours : 0
                     * month : 4
                     * timezoneOffset : -480
                     * year : 118
                     * day : 3
                     * date : 16
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

                public static class CreatedateBeanX {
                    /**
                     * nanos : 0
                     * time : 1526400000000
                     * minutes : 0
                     * seconds : 0
                     * hours : 0
                     * month : 4
                     * timezoneOffset : -480
                     * year : 118
                     * day : 3
                     * date : 16
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

                public static class ProidBean {
                    /**
                     * proId : 15
                     * remark :
                     * bzqunit : 1
                     * probzq : 25
                     * userid : {"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"北京","filecardid":{"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"},"clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"软件工程","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8},"address":"北京西城","role":""}
                     * state : 1
                     * createdate : {"nanos":0,"time":1526227200000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":1,"date":14}
                     * proCode : 898881
                     * barcode : 111111
                     * protype : 0
                     * mernameorplace : 667
                     * proName : 可乐
                     * trademark : 可口可乐
                     * meas : ML
                     * obtype : {"content":"食用植物油","grade":"3","code":"020101","parentCode":{"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"},"isValid":"1"}
                     * updatedate : null
                     * batchCount : 0
                     * meaunit : 78
                     */

                    private int proId;
                    private String remark;
                    private String bzqunit;
                    private String probzq;
                    private UseridBeanX userid;
                    private String state;
                    private CreatedateBeanXX createdate;
                    private String proCode;
                    private String barcode;
                    private String protype;
                    private String mernameorplace;
                    private String proName;
                    private String trademark;
                    private String meas;
                    private ObtypeBeanX obtype;
                    private Object updatedate;
                    private int batchCount;
                    private String meaunit;

                    public int getProId() {
                        return proId;
                    }

                    public void setProId(int proId) {
                        this.proId = proId;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
                    }

                    public String getBzqunit() {
                        return bzqunit;
                    }

                    public void setBzqunit(String bzqunit) {
                        this.bzqunit = bzqunit;
                    }

                    public String getProbzq() {
                        return probzq;
                    }

                    public void setProbzq(String probzq) {
                        this.probzq = probzq;
                    }

                    public UseridBeanX getUserid() {
                        return userid;
                    }

                    public void setUserid(UseridBeanX userid) {
                        this.userid = userid;
                    }

                    public String getState() {
                        return state;
                    }

                    public void setState(String state) {
                        this.state = state;
                    }

                    public CreatedateBeanXX getCreatedate() {
                        return createdate;
                    }

                    public void setCreatedate(CreatedateBeanXX createdate) {
                        this.createdate = createdate;
                    }

                    public String getProCode() {
                        return proCode;
                    }

                    public void setProCode(String proCode) {
                        this.proCode = proCode;
                    }

                    public String getBarcode() {
                        return barcode;
                    }

                    public void setBarcode(String barcode) {
                        this.barcode = barcode;
                    }

                    public String getProtype() {
                        return protype;
                    }

                    public void setProtype(String protype) {
                        this.protype = protype;
                    }

                    public String getMernameorplace() {
                        return mernameorplace;
                    }

                    public void setMernameorplace(String mernameorplace) {
                        this.mernameorplace = mernameorplace;
                    }

                    public String getProName() {
                        return proName;
                    }

                    public void setProName(String proName) {
                        this.proName = proName;
                    }

                    public String getTrademark() {
                        return trademark;
                    }

                    public void setTrademark(String trademark) {
                        this.trademark = trademark;
                    }

                    public String getMeas() {
                        return meas;
                    }

                    public void setMeas(String meas) {
                        this.meas = meas;
                    }

                    public ObtypeBeanX getObtype() {
                        return obtype;
                    }

                    public void setObtype(ObtypeBeanX obtype) {
                        this.obtype = obtype;
                    }

                    public Object getUpdatedate() {
                        return updatedate;
                    }

                    public void setUpdatedate(Object updatedate) {
                        this.updatedate = updatedate;
                    }

                    public int getBatchCount() {
                        return batchCount;
                    }

                    public void setBatchCount(int batchCount) {
                        this.batchCount = batchCount;
                    }

                    public String getMeaunit() {
                        return meaunit;
                    }

                    public void setMeaunit(String meaunit) {
                        this.meaunit = meaunit;
                    }

                    public static class UseridBeanX {
                        /**
                         * sex : 1
                         * userage : 25
                         * empstate : 0
                         * cardnum : 612724199107190760
                         * roleid :
                         * userid : 47
                         * creatdateStr : 2018-05-08
                         * shopname :
                         * cid : {"address":"北京","filecardid":{"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"},"clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"软件工程","cid":2,"cname":"普众科技（北京）有限公司"}
                         * logname : lixm
                         * password : MDAwMDAw
                         * telphone : 18810580240
                         * username : 李晓敏宝宝
                         * email : 1050507901@qq.com
                         * creatdate : {"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8}
                         * address : 北京西城
                         * role :
                         */

                        private String sex;
                        private String userage;
                        private String empstate;
                        private String cardnum;
                        private String roleid;
                        private int userid;
                        private String creatdateStr;
                        private String shopname;
                        private CidBeanX cid;
                        private String logname;
                        private String password;
                        private String telphone;
                        private String username;
                        private String email;
                        private CreatdateBeanX creatdate;
                        private String address;
                        private String role;

                        public String getSex() {
                            return sex;
                        }

                        public void setSex(String sex) {
                            this.sex = sex;
                        }

                        public String getUserage() {
                            return userage;
                        }

                        public void setUserage(String userage) {
                            this.userage = userage;
                        }

                        public String getEmpstate() {
                            return empstate;
                        }

                        public void setEmpstate(String empstate) {
                            this.empstate = empstate;
                        }

                        public String getCardnum() {
                            return cardnum;
                        }

                        public void setCardnum(String cardnum) {
                            this.cardnum = cardnum;
                        }

                        public String getRoleid() {
                            return roleid;
                        }

                        public void setRoleid(String roleid) {
                            this.roleid = roleid;
                        }

                        public int getUserid() {
                            return userid;
                        }

                        public void setUserid(int userid) {
                            this.userid = userid;
                        }

                        public String getCreatdateStr() {
                            return creatdateStr;
                        }

                        public void setCreatdateStr(String creatdateStr) {
                            this.creatdateStr = creatdateStr;
                        }

                        public String getShopname() {
                            return shopname;
                        }

                        public void setShopname(String shopname) {
                            this.shopname = shopname;
                        }

                        public CidBeanX getCid() {
                            return cid;
                        }

                        public void setCid(CidBeanX cid) {
                            this.cid = cid;
                        }

                        public String getLogname() {
                            return logname;
                        }

                        public void setLogname(String logname) {
                            this.logname = logname;
                        }

                        public String getPassword() {
                            return password;
                        }

                        public void setPassword(String password) {
                            this.password = password;
                        }

                        public String getTelphone() {
                            return telphone;
                        }

                        public void setTelphone(String telphone) {
                            this.telphone = telphone;
                        }

                        public String getUsername() {
                            return username;
                        }

                        public void setUsername(String username) {
                            this.username = username;
                        }

                        public String getEmail() {
                            return email;
                        }

                        public void setEmail(String email) {
                            this.email = email;
                        }

                        public CreatdateBeanX getCreatdate() {
                            return creatdate;
                        }

                        public void setCreatdate(CreatdateBeanX creatdate) {
                            this.creatdate = creatdate;
                        }

                        public String getAddress() {
                            return address;
                        }

                        public void setAddress(String address) {
                            this.address = address;
                        }

                        public String getRole() {
                            return role;
                        }

                        public void setRole(String role) {
                            this.role = role;
                        }

                        public static class CidBeanX {
                            /**
                             * address : 北京
                             * filecardid : {"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"}
                             * clicence : 987654321252525
                             * enddateStr : 2018-04-23
                             * state : 0
                             * qyfr : 刘万荣
                             * enddate : {"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23}
                             * jjfw : 软件工程
                             * cid : 2
                             * cname : 普众科技（北京）有限公司
                             */

                            private String address;
                            private FilecardidBeanX filecardid;
                            private String clicence;
                            private String enddateStr;
                            private String state;
                            private String qyfr;
                            private EnddateBeanX enddate;
                            private String jjfw;
                            private int cid;
                            private String cname;

                            public String getAddress() {
                                return address;
                            }

                            public void setAddress(String address) {
                                this.address = address;
                            }

                            public FilecardidBeanX getFilecardid() {
                                return filecardid;
                            }

                            public void setFilecardid(FilecardidBeanX filecardid) {
                                this.filecardid = filecardid;
                            }

                            public String getClicence() {
                                return clicence;
                            }

                            public void setClicence(String clicence) {
                                this.clicence = clicence;
                            }

                            public String getEnddateStr() {
                                return enddateStr;
                            }

                            public void setEnddateStr(String enddateStr) {
                                this.enddateStr = enddateStr;
                            }

                            public String getState() {
                                return state;
                            }

                            public void setState(String state) {
                                this.state = state;
                            }

                            public String getQyfr() {
                                return qyfr;
                            }

                            public void setQyfr(String qyfr) {
                                this.qyfr = qyfr;
                            }

                            public EnddateBeanX getEnddate() {
                                return enddate;
                            }

                            public void setEnddate(EnddateBeanX enddate) {
                                this.enddate = enddate;
                            }

                            public String getJjfw() {
                                return jjfw;
                            }

                            public void setJjfw(String jjfw) {
                                this.jjfw = jjfw;
                            }

                            public int getCid() {
                                return cid;
                            }

                            public void setCid(int cid) {
                                this.cid = cid;
                            }

                            public String getCname() {
                                return cname;
                            }

                            public void setCname(String cname) {
                                this.cname = cname;
                            }

                            public static class FilecardidBeanX {
                                /**
                                 * filecardid : 116
                                 * remark :
                                 * path : certification.png
                                 * state : 1
                                 * uploaddate : {"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16}
                                 * parentpath : 201805
                                 */

                                private int filecardid;
                                private String remark;
                                private String path;
                                private String state;
                                private UploaddateBeanX uploaddate;
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

                                public UploaddateBeanX getUploaddate() {
                                    return uploaddate;
                                }

                                public void setUploaddate(UploaddateBeanX uploaddate) {
                                    this.uploaddate = uploaddate;
                                }

                                public String getParentpath() {
                                    return parentpath;
                                }

                                public void setParentpath(String parentpath) {
                                    this.parentpath = parentpath;
                                }

                                public static class UploaddateBeanX {
                                    /**
                                     * nanos : 0
                                     * time : 1526400000000
                                     * minutes : 0
                                     * seconds : 0
                                     * hours : 0
                                     * month : 4
                                     * timezoneOffset : -480
                                     * year : 118
                                     * day : 3
                                     * date : 16
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

                            public static class EnddateBeanX {
                                /**
                                 * nanos : 0
                                 * time : 1524412800000
                                 * minutes : 0
                                 * seconds : 0
                                 * hours : 0
                                 * month : 3
                                 * timezoneOffset : -480
                                 * year : 118
                                 * day : 1
                                 * date : 23
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

                        public static class CreatdateBeanX {
                            /**
                             * nanos : 0
                             * time : 1525751418000
                             * minutes : 50
                             * seconds : 18
                             * hours : 11
                             * month : 4
                             * timezoneOffset : -480
                             * year : 118
                             * day : 2
                             * date : 8
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

                    public static class CreatedateBeanXX {
                        /**
                         * nanos : 0
                         * time : 1526227200000
                         * minutes : 0
                         * seconds : 0
                         * hours : 0
                         * month : 4
                         * timezoneOffset : -480
                         * year : 118
                         * day : 1
                         * date : 14
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

                    public static class ObtypeBeanX {
                        /**
                         * content : 食用植物油
                         * grade : 3
                         * code : 020101
                         * parentCode : {"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"}
                         * isValid : 1
                         */

                        private String content;
                        private String grade;
                        private String code;
                        private ParentCodeBeanXXX parentCode;
                        private String isValid;

                        public String getContent() {
                            return content;
                        }

                        public void setContent(String content) {
                            this.content = content;
                        }

                        public String getGrade() {
                            return grade;
                        }

                        public void setGrade(String grade) {
                            this.grade = grade;
                        }

                        public String getCode() {
                            return code;
                        }

                        public void setCode(String code) {
                            this.code = code;
                        }

                        public ParentCodeBeanXXX getParentCode() {
                            return parentCode;
                        }

                        public void setParentCode(ParentCodeBeanXXX parentCode) {
                            this.parentCode = parentCode;
                        }

                        public String getIsValid() {
                            return isValid;
                        }

                        public void setIsValid(String isValid) {
                            this.isValid = isValid;
                        }

                        public static class ParentCodeBeanXXX {
                            /**
                             * content : 食用植物油
                             * grade : 2
                             * code : 0201
                             * parentCode : {"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"}
                             * isValid : 1
                             */

                            private String content;
                            private String grade;
                            private String code;
                            private ParentCodeBeanXX parentCode;
                            private String isValid;

                            public String getContent() {
                                return content;
                            }

                            public void setContent(String content) {
                                this.content = content;
                            }

                            public String getGrade() {
                                return grade;
                            }

                            public void setGrade(String grade) {
                                this.grade = grade;
                            }

                            public String getCode() {
                                return code;
                            }

                            public void setCode(String code) {
                                this.code = code;
                            }

                            public ParentCodeBeanXX getParentCode() {
                                return parentCode;
                            }

                            public void setParentCode(ParentCodeBeanXX parentCode) {
                                this.parentCode = parentCode;
                            }

                            public String getIsValid() {
                                return isValid;
                            }

                            public void setIsValid(String isValid) {
                                this.isValid = isValid;
                            }

                            public static class ParentCodeBeanXX {
                                /**
                                 * content : 食用油、油脂及其制品
                                 * grade : 1
                                 * code : 02
                                 * parentCode : null
                                 * isValid : 1
                                 */

                                private String content;
                                private String grade;
                                private String code;
                                private Object parentCode;
                                private String isValid;

                                public String getContent() {
                                    return content;
                                }

                                public void setContent(String content) {
                                    this.content = content;
                                }

                                public String getGrade() {
                                    return grade;
                                }

                                public void setGrade(String grade) {
                                    this.grade = grade;
                                }

                                public String getCode() {
                                    return code;
                                }

                                public void setCode(String code) {
                                    this.code = code;
                                }

                                public Object getParentCode() {
                                    return parentCode;
                                }

                                public void setParentCode(Object parentCode) {
                                    this.parentCode = parentCode;
                                }

                                public String getIsValid() {
                                    return isValid;
                                }

                                public void setIsValid(String isValid) {
                                    this.isValid = isValid;
                                }
                            }
                        }
                    }
                }
            }

            public static class GoodidBean {
                /**
                 * proId : 15
                 * remark :
                 * bzqunit : 1
                 * probzq : 25
                 * userid : {"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"北京","filecardid":{"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"},"clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"软件工程","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8},"address":"北京西城","role":""}
                 * state : 1
                 * createdate : {"nanos":0,"time":1526227200000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":1,"date":14}
                 * proCode : 898881
                 * barcode : 111111
                 * protype : 0
                 * mernameorplace : 667
                 * proName : 可乐
                 * trademark : 可口可乐
                 * meas : ML
                 * obtype : {"content":"食用植物油","grade":"3","code":"020101","parentCode":{"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"},"isValid":"1"}
                 * updatedate : null
                 * batchCount : 0
                 * meaunit : 78
                 */

                private int proId;
                private String remark;
                private String bzqunit;
                private String probzq;
                private UseridBeanXX userid;
                private String state;
                private CreatedateBeanXXX createdate;
                private String proCode;
                private String barcode;
                private String protype;
                private String mernameorplace;
                private String proName;
                private String trademark;
                private String meas;
                private ObtypeBeanXX obtype;
                private Object updatedate;
                private int batchCount;
                private String meaunit;

                public int getProId() {
                    return proId;
                }

                public void setProId(int proId) {
                    this.proId = proId;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getBzqunit() {
                    return bzqunit;
                }

                public void setBzqunit(String bzqunit) {
                    this.bzqunit = bzqunit;
                }

                public String getProbzq() {
                    return probzq;
                }

                public void setProbzq(String probzq) {
                    this.probzq = probzq;
                }

                public UseridBeanXX getUserid() {
                    return userid;
                }

                public void setUserid(UseridBeanXX userid) {
                    this.userid = userid;
                }

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public CreatedateBeanXXX getCreatedate() {
                    return createdate;
                }

                public void setCreatedate(CreatedateBeanXXX createdate) {
                    this.createdate = createdate;
                }

                public String getProCode() {
                    return proCode;
                }

                public void setProCode(String proCode) {
                    this.proCode = proCode;
                }

                public String getBarcode() {
                    return barcode;
                }

                public void setBarcode(String barcode) {
                    this.barcode = barcode;
                }

                public String getProtype() {
                    return protype;
                }

                public void setProtype(String protype) {
                    this.protype = protype;
                }

                public String getMernameorplace() {
                    return mernameorplace;
                }

                public void setMernameorplace(String mernameorplace) {
                    this.mernameorplace = mernameorplace;
                }

                public String getProName() {
                    return proName;
                }

                public void setProName(String proName) {
                    this.proName = proName;
                }

                public String getTrademark() {
                    return trademark;
                }

                public void setTrademark(String trademark) {
                    this.trademark = trademark;
                }

                public String getMeas() {
                    return meas;
                }

                public void setMeas(String meas) {
                    this.meas = meas;
                }

                public ObtypeBeanXX getObtype() {
                    return obtype;
                }

                public void setObtype(ObtypeBeanXX obtype) {
                    this.obtype = obtype;
                }

                public Object getUpdatedate() {
                    return updatedate;
                }

                public void setUpdatedate(Object updatedate) {
                    this.updatedate = updatedate;
                }

                public int getBatchCount() {
                    return batchCount;
                }

                public void setBatchCount(int batchCount) {
                    this.batchCount = batchCount;
                }

                public String getMeaunit() {
                    return meaunit;
                }

                public void setMeaunit(String meaunit) {
                    this.meaunit = meaunit;
                }

                public static class UseridBeanXX {
                    /**
                     * sex : 1
                     * userage : 25
                     * empstate : 0
                     * cardnum : 612724199107190760
                     * roleid :
                     * userid : 47
                     * creatdateStr : 2018-05-08
                     * shopname :
                     * cid : {"address":"北京","filecardid":{"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"},"clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"软件工程","cid":2,"cname":"普众科技（北京）有限公司"}
                     * logname : lixm
                     * password : MDAwMDAw
                     * telphone : 18810580240
                     * username : 李晓敏宝宝
                     * email : 1050507901@qq.com
                     * creatdate : {"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8}
                     * address : 北京西城
                     * role :
                     */

                    private String sex;
                    private String userage;
                    private String empstate;
                    private String cardnum;
                    private String roleid;
                    private int userid;
                    private String creatdateStr;
                    private String shopname;
                    private CidBeanXX cid;
                    private String logname;
                    private String password;
                    private String telphone;
                    private String username;
                    private String email;
                    private CreatdateBeanXX creatdate;
                    private String address;
                    private String role;

                    public String getSex() {
                        return sex;
                    }

                    public void setSex(String sex) {
                        this.sex = sex;
                    }

                    public String getUserage() {
                        return userage;
                    }

                    public void setUserage(String userage) {
                        this.userage = userage;
                    }

                    public String getEmpstate() {
                        return empstate;
                    }

                    public void setEmpstate(String empstate) {
                        this.empstate = empstate;
                    }

                    public String getCardnum() {
                        return cardnum;
                    }

                    public void setCardnum(String cardnum) {
                        this.cardnum = cardnum;
                    }

                    public String getRoleid() {
                        return roleid;
                    }

                    public void setRoleid(String roleid) {
                        this.roleid = roleid;
                    }

                    public int getUserid() {
                        return userid;
                    }

                    public void setUserid(int userid) {
                        this.userid = userid;
                    }

                    public String getCreatdateStr() {
                        return creatdateStr;
                    }

                    public void setCreatdateStr(String creatdateStr) {
                        this.creatdateStr = creatdateStr;
                    }

                    public String getShopname() {
                        return shopname;
                    }

                    public void setShopname(String shopname) {
                        this.shopname = shopname;
                    }

                    public CidBeanXX getCid() {
                        return cid;
                    }

                    public void setCid(CidBeanXX cid) {
                        this.cid = cid;
                    }

                    public String getLogname() {
                        return logname;
                    }

                    public void setLogname(String logname) {
                        this.logname = logname;
                    }

                    public String getPassword() {
                        return password;
                    }

                    public void setPassword(String password) {
                        this.password = password;
                    }

                    public String getTelphone() {
                        return telphone;
                    }

                    public void setTelphone(String telphone) {
                        this.telphone = telphone;
                    }

                    public String getUsername() {
                        return username;
                    }

                    public void setUsername(String username) {
                        this.username = username;
                    }

                    public String getEmail() {
                        return email;
                    }

                    public void setEmail(String email) {
                        this.email = email;
                    }

                    public CreatdateBeanXX getCreatdate() {
                        return creatdate;
                    }

                    public void setCreatdate(CreatdateBeanXX creatdate) {
                        this.creatdate = creatdate;
                    }

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getRole() {
                        return role;
                    }

                    public void setRole(String role) {
                        this.role = role;
                    }

                    public static class CidBeanXX {
                        /**
                         * address : 北京
                         * filecardid : {"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"}
                         * clicence : 987654321252525
                         * enddateStr : 2018-04-23
                         * state : 0
                         * qyfr : 刘万荣
                         * enddate : {"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23}
                         * jjfw : 软件工程
                         * cid : 2
                         * cname : 普众科技（北京）有限公司
                         */

                        private String address;
                        private FilecardidBeanXX filecardid;
                        private String clicence;
                        private String enddateStr;
                        private String state;
                        private String qyfr;
                        private EnddateBeanXX enddate;
                        private String jjfw;
                        private int cid;
                        private String cname;

                        public String getAddress() {
                            return address;
                        }

                        public void setAddress(String address) {
                            this.address = address;
                        }

                        public FilecardidBeanXX getFilecardid() {
                            return filecardid;
                        }

                        public void setFilecardid(FilecardidBeanXX filecardid) {
                            this.filecardid = filecardid;
                        }

                        public String getClicence() {
                            return clicence;
                        }

                        public void setClicence(String clicence) {
                            this.clicence = clicence;
                        }

                        public String getEnddateStr() {
                            return enddateStr;
                        }

                        public void setEnddateStr(String enddateStr) {
                            this.enddateStr = enddateStr;
                        }

                        public String getState() {
                            return state;
                        }

                        public void setState(String state) {
                            this.state = state;
                        }

                        public String getQyfr() {
                            return qyfr;
                        }

                        public void setQyfr(String qyfr) {
                            this.qyfr = qyfr;
                        }

                        public EnddateBeanXX getEnddate() {
                            return enddate;
                        }

                        public void setEnddate(EnddateBeanXX enddate) {
                            this.enddate = enddate;
                        }

                        public String getJjfw() {
                            return jjfw;
                        }

                        public void setJjfw(String jjfw) {
                            this.jjfw = jjfw;
                        }

                        public int getCid() {
                            return cid;
                        }

                        public void setCid(int cid) {
                            this.cid = cid;
                        }

                        public String getCname() {
                            return cname;
                        }

                        public void setCname(String cname) {
                            this.cname = cname;
                        }

                        public static class FilecardidBeanXX {
                            /**
                             * filecardid : 116
                             * remark :
                             * path : certification.png
                             * state : 1
                             * uploaddate : {"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16}
                             * parentpath : 201805
                             */

                            private int filecardid;
                            private String remark;
                            private String path;
                            private String state;
                            private UploaddateBeanXX uploaddate;
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

                            public UploaddateBeanXX getUploaddate() {
                                return uploaddate;
                            }

                            public void setUploaddate(UploaddateBeanXX uploaddate) {
                                this.uploaddate = uploaddate;
                            }

                            public String getParentpath() {
                                return parentpath;
                            }

                            public void setParentpath(String parentpath) {
                                this.parentpath = parentpath;
                            }

                            public static class UploaddateBeanXX {
                                /**
                                 * nanos : 0
                                 * time : 1526400000000
                                 * minutes : 0
                                 * seconds : 0
                                 * hours : 0
                                 * month : 4
                                 * timezoneOffset : -480
                                 * year : 118
                                 * day : 3
                                 * date : 16
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

                        public static class EnddateBeanXX {
                            /**
                             * nanos : 0
                             * time : 1524412800000
                             * minutes : 0
                             * seconds : 0
                             * hours : 0
                             * month : 3
                             * timezoneOffset : -480
                             * year : 118
                             * day : 1
                             * date : 23
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

                    public static class CreatdateBeanXX {
                        /**
                         * nanos : 0
                         * time : 1525751418000
                         * minutes : 50
                         * seconds : 18
                         * hours : 11
                         * month : 4
                         * timezoneOffset : -480
                         * year : 118
                         * day : 2
                         * date : 8
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

                public static class CreatedateBeanXXX {
                    /**
                     * nanos : 0
                     * time : 1526227200000
                     * minutes : 0
                     * seconds : 0
                     * hours : 0
                     * month : 4
                     * timezoneOffset : -480
                     * year : 118
                     * day : 1
                     * date : 14
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

                public static class ObtypeBeanXX {
                    /**
                     * content : 食用植物油
                     * grade : 3
                     * code : 020101
                     * parentCode : {"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"}
                     * isValid : 1
                     */

                    private String content;
                    private String grade;
                    private String code;
                    private ParentCodeBeanXXXXX parentCode;
                    private String isValid;

                    public String getContent() {
                        return content;
                    }

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public String getGrade() {
                        return grade;
                    }

                    public void setGrade(String grade) {
                        this.grade = grade;
                    }

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public ParentCodeBeanXXXXX getParentCode() {
                        return parentCode;
                    }

                    public void setParentCode(ParentCodeBeanXXXXX parentCode) {
                        this.parentCode = parentCode;
                    }

                    public String getIsValid() {
                        return isValid;
                    }

                    public void setIsValid(String isValid) {
                        this.isValid = isValid;
                    }

                    public static class ParentCodeBeanXXXXX {
                        /**
                         * content : 食用植物油
                         * grade : 2
                         * code : 0201
                         * parentCode : {"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"}
                         * isValid : 1
                         */

                        private String content;
                        private String grade;
                        private String code;
                        private ParentCodeBeanXXXX parentCode;
                        private String isValid;

                        public String getContent() {
                            return content;
                        }

                        public void setContent(String content) {
                            this.content = content;
                        }

                        public String getGrade() {
                            return grade;
                        }

                        public void setGrade(String grade) {
                            this.grade = grade;
                        }

                        public String getCode() {
                            return code;
                        }

                        public void setCode(String code) {
                            this.code = code;
                        }

                        public ParentCodeBeanXXXX getParentCode() {
                            return parentCode;
                        }

                        public void setParentCode(ParentCodeBeanXXXX parentCode) {
                            this.parentCode = parentCode;
                        }

                        public String getIsValid() {
                            return isValid;
                        }

                        public void setIsValid(String isValid) {
                            this.isValid = isValid;
                        }

                        public static class ParentCodeBeanXXXX {
                            /**
                             * content : 食用油、油脂及其制品
                             * grade : 1
                             * code : 02
                             * parentCode : null
                             * isValid : 1
                             */

                            private String content;
                            private String grade;
                            private String code;
                            private Object parentCode;
                            private String isValid;

                            public String getContent() {
                                return content;
                            }

                            public void setContent(String content) {
                                this.content = content;
                            }

                            public String getGrade() {
                                return grade;
                            }

                            public void setGrade(String grade) {
                                this.grade = grade;
                            }

                            public String getCode() {
                                return code;
                            }

                            public void setCode(String code) {
                                this.code = code;
                            }

                            public Object getParentCode() {
                                return parentCode;
                            }

                            public void setParentCode(Object parentCode) {
                                this.parentCode = parentCode;
                            }

                            public String getIsValid() {
                                return isValid;
                            }

                            public void setIsValid(String isValid) {
                                this.isValid = isValid;
                            }
                        }
                    }
                }
            }
        }
    }

    public static class AuditBean {
        /**
         * auditmess : 动图
         * time : {"nanos":0,"time":1528774839000,"minutes":40,"seconds":39,"hours":11,"month":5,"timezoneOffset":-480,"year":118,"day":2,"date":12}
         * auditman : {"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"北京","filecardid":{"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"},"clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"软件工程","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8},"address":"北京西城","role":""}
         * auditid : 9
         * timeStr : 2018-06-12 11:40:39
         * auditresult : 0
         * hdid : 2018612114001
         */

        private String auditmess;
        private TimeBean time;
        private AuditmanBean auditman;
        private int auditid;
        private String timeStr;
        private String auditresult;
        private String hdid;

        public String getAuditmess() {
            return auditmess;
        }

        public void setAuditmess(String auditmess) {
            this.auditmess = auditmess;
        }

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public AuditmanBean getAuditman() {
            return auditman;
        }

        public void setAuditman(AuditmanBean auditman) {
            this.auditman = auditman;
        }

        public int getAuditid() {
            return auditid;
        }

        public void setAuditid(int auditid) {
            this.auditid = auditid;
        }

        public String getTimeStr() {
            return timeStr;
        }

        public void setTimeStr(String timeStr) {
            this.timeStr = timeStr;
        }

        public String getAuditresult() {
            return auditresult;
        }

        public void setAuditresult(String auditresult) {
            this.auditresult = auditresult;
        }

        public String getHdid() {
            return hdid;
        }

        public void setHdid(String hdid) {
            this.hdid = hdid;
        }

        public static class TimeBean {
            /**
             * nanos : 0
             * time : 1528774839000
             * minutes : 40
             * seconds : 39
             * hours : 11
             * month : 5
             * timezoneOffset : -480
             * year : 118
             * day : 2
             * date : 12
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

        public static class AuditmanBean {
            /**
             * sex : 1
             * userage : 25
             * empstate : 0
             * cardnum : 612724199107190760
             * roleid :
             * userid : 47
             * creatdateStr : 2018-05-08
             * shopname :
             * cid : {"address":"北京","filecardid":{"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"},"clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"软件工程","cid":2,"cname":"普众科技（北京）有限公司"}
             * logname : lixm
             * password : MDAwMDAw
             * telphone : 18810580240
             * username : 李晓敏宝宝
             * email : 1050507901@qq.com
             * creatdate : {"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8}
             * address : 北京西城
             * role :
             */

            private String sex;
            private String userage;
            private String empstate;
            private String cardnum;
            private String roleid;
            private int userid;
            private String creatdateStr;
            private String shopname;
            private CidBeanXXX cid;
            private String logname;
            private String password;
            private String telphone;
            private String username;
            private String email;
            private CreatdateBeanXXX creatdate;
            private String address;
            private String role;

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getUserage() {
                return userage;
            }

            public void setUserage(String userage) {
                this.userage = userage;
            }

            public String getEmpstate() {
                return empstate;
            }

            public void setEmpstate(String empstate) {
                this.empstate = empstate;
            }

            public String getCardnum() {
                return cardnum;
            }

            public void setCardnum(String cardnum) {
                this.cardnum = cardnum;
            }

            public String getRoleid() {
                return roleid;
            }

            public void setRoleid(String roleid) {
                this.roleid = roleid;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getCreatdateStr() {
                return creatdateStr;
            }

            public void setCreatdateStr(String creatdateStr) {
                this.creatdateStr = creatdateStr;
            }

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public CidBeanXXX getCid() {
                return cid;
            }

            public void setCid(CidBeanXXX cid) {
                this.cid = cid;
            }

            public String getLogname() {
                return logname;
            }

            public void setLogname(String logname) {
                this.logname = logname;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getTelphone() {
                return telphone;
            }

            public void setTelphone(String telphone) {
                this.telphone = telphone;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public CreatdateBeanXXX getCreatdate() {
                return creatdate;
            }

            public void setCreatdate(CreatdateBeanXXX creatdate) {
                this.creatdate = creatdate;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public static class CidBeanXXX {
                /**
                 * address : 北京
                 * filecardid : {"filecardid":116,"remark":"","path":"certification.png","state":"1","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16},"parentpath":"201805"}
                 * clicence : 987654321252525
                 * enddateStr : 2018-04-23
                 * state : 0
                 * qyfr : 刘万荣
                 * enddate : {"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23}
                 * jjfw : 软件工程
                 * cid : 2
                 * cname : 普众科技（北京）有限公司
                 */

                private String address;
                private FilecardidBeanXXX filecardid;
                private String clicence;
                private String enddateStr;
                private String state;
                private String qyfr;
                private EnddateBeanXXX enddate;
                private String jjfw;
                private int cid;
                private String cname;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public FilecardidBeanXXX getFilecardid() {
                    return filecardid;
                }

                public void setFilecardid(FilecardidBeanXXX filecardid) {
                    this.filecardid = filecardid;
                }

                public String getClicence() {
                    return clicence;
                }

                public void setClicence(String clicence) {
                    this.clicence = clicence;
                }

                public String getEnddateStr() {
                    return enddateStr;
                }

                public void setEnddateStr(String enddateStr) {
                    this.enddateStr = enddateStr;
                }

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public String getQyfr() {
                    return qyfr;
                }

                public void setQyfr(String qyfr) {
                    this.qyfr = qyfr;
                }

                public EnddateBeanXXX getEnddate() {
                    return enddate;
                }

                public void setEnddate(EnddateBeanXXX enddate) {
                    this.enddate = enddate;
                }

                public String getJjfw() {
                    return jjfw;
                }

                public void setJjfw(String jjfw) {
                    this.jjfw = jjfw;
                }

                public int getCid() {
                    return cid;
                }

                public void setCid(int cid) {
                    this.cid = cid;
                }

                public String getCname() {
                    return cname;
                }

                public void setCname(String cname) {
                    this.cname = cname;
                }

                public static class FilecardidBeanXXX {
                    /**
                     * filecardid : 116
                     * remark :
                     * path : certification.png
                     * state : 1
                     * uploaddate : {"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":16}
                     * parentpath : 201805
                     */

                    private int filecardid;
                    private String remark;
                    private String path;
                    private String state;
                    private UploaddateBeanXXX uploaddate;
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

                    public UploaddateBeanXXX getUploaddate() {
                        return uploaddate;
                    }

                    public void setUploaddate(UploaddateBeanXXX uploaddate) {
                        this.uploaddate = uploaddate;
                    }

                    public String getParentpath() {
                        return parentpath;
                    }

                    public void setParentpath(String parentpath) {
                        this.parentpath = parentpath;
                    }

                    public static class UploaddateBeanXXX {
                        /**
                         * nanos : 0
                         * time : 1526400000000
                         * minutes : 0
                         * seconds : 0
                         * hours : 0
                         * month : 4
                         * timezoneOffset : -480
                         * year : 118
                         * day : 3
                         * date : 16
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

                public static class EnddateBeanXXX {
                    /**
                     * nanos : 0
                     * time : 1524412800000
                     * minutes : 0
                     * seconds : 0
                     * hours : 0
                     * month : 3
                     * timezoneOffset : -480
                     * year : 118
                     * day : 1
                     * date : 23
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

            public static class CreatdateBeanXXX {
                /**
                 * nanos : 0
                 * time : 1525751418000
                 * minutes : 50
                 * seconds : 18
                 * hours : 11
                 * month : 4
                 * timezoneOffset : -480
                 * year : 118
                 * day : 2
                 * date : 8
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
