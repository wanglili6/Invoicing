package com.mtecc.mmp.invoicing.activity.comodity.bean;

import java.util.List;

/**
 * Created by wll on 2018/5/11.
 */

public class SeeBatchMsgBean {
    /**
     * cardList : []
     * batch : {"pbatchid":25,"batchdate":{"nanos":0,"time":1527609600000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":30},"saleprice":"","remark":"","batchnum":"","createdate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":16},"batchdateStr":"2018-05-30","proName":"可乐","isdel":"1","proid":{"proId":15,"remark":"","bzqunit":"1","probzq":"25","userid":{"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"filecardid":{"remark":"","filecardid":116,"state":"1","path":"certification.png","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":16},"parentpath":"201805"},"address":"北京","clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","jjfw":"软件工程","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","address":"北京西城","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"year":118,"timezoneOffset":-480,"day":2,"date":8},"email":"1050507901@qq.com","role":""},"state":"1","createdate":{"nanos":0,"time":1526227200000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":1,"date":14},"proCode":"898881","barcode":"111111","protype":"0","mernameorplace":"667","proName":"可乐","trademark":"可口可乐","meas":"ML","obtype":{"content":"食用植物油","grade":"3","code":"020101","parentCode":{"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"},"isValid":"1"},"updatedate":null,"batchCount":0,"meaunit":"78"},"enterprice":"5.89","sellprice":"","updatedate":null}
     * diclist : [{"dicid":48,"busintypename":"进销商证件照类型","BUSINNAME":"食品生产许可证","status":"0","busintypeid":"CompanyLience","BUSINID":1},{"dicid":49,"busintypename":"进销商证件照类型","BUSINNAME":"食品生产经营证","status":"0","busintypeid":"CompanyLience","BUSINID":2},{"dicid":50,"busintypename":"进销商证件照类型","BUSINNAME":"食品生产流通证","status":"0","busintypeid":"CompanyLience","BUSINID":3}]
     */

    private BatchBean batch;

    private List<CardListBean> cardList;
    private List<DiclistBean> diclist;

    public List<CardListBean> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardListBean> cardList) {
        this.cardList = cardList;
    }

    public List<DiclistBean> getDiclist() {
        return diclist;
    }

    public void setDiclist(List<DiclistBean> diclist) {
        this.diclist = diclist;
    }

    public static class CardListBean {
        /**
         * cardtype : 3
         * pbatchid : {"pbatchid":16,"batchdate":{"nanos":0,"time":1525968000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":5,"date":11},"saleprice":"22","remark":"","batchnum":"123","createdate":{"nanos":0,"time":1525968000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":5,"date":11},"batchdateStr":"2018-05-11","proName":"瓜子","isdel":"1","proid":{"proId":12,"remark":"","bzqunit":"1","probzq":"30","userid":{"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"暂无","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8},"address":"北京西城","role":""},"state":"1","createdate":{"nanos":0,"time":1525795200000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":9},"proCode":"","barcode":"147","protype":"1","mernameorplace":"147255","proName":"瓜子","trademark":"沙土","meas":"11","obtype":{"content":"方便食品","grade":"2","code":"0701","parentCode":{"content":"方便食品","grade":"1","code":"07","parentCode":null,"isValid":"1"},"isValid":"1"},"updatedate":null,"batchCount":0,"meaunit":"袋"},"sellprice":"25","updatedate":null,"enterprice":"23"}
         * charddate : {"nanos":0,"time":1525968000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":5,"date":11}
         * charddateStr : 2018-05-11
         * cardnum : 6969
         * filecardid : {"filecardid":53,"remark":"","path":"6969.png","state":"1","uploaddate":{"nanos":0,"time":1525968000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":5,"date":11},"parentpath":"201805"}
         * remark :
         * createdate : null
         * isdel :
         * procardid : 25
         * updatedate : null
         */

        private String cardtype;
        private PbatchidBean pbatchid;
        private CharddateBean charddate;
        private String charddateStr;
        private String cardnum;
        private FilecardidBean filecardid;
        private String remark;
        private Object createdate;
        private String isdel;
        private int procardid;
        private Object updatedate;

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public PbatchidBean getPbatchid() {
            return pbatchid;
        }

        public void setPbatchid(PbatchidBean pbatchid) {
            this.pbatchid = pbatchid;
        }

        public CharddateBean getCharddate() {
            return charddate;
        }

        public void setCharddate(CharddateBean charddate) {
            this.charddate = charddate;
        }

        public String getCharddateStr() {
            return charddateStr;
        }

        public void setCharddateStr(String charddateStr) {
            this.charddateStr = charddateStr;
        }

        public String getCardnum() {
            return cardnum;
        }

        public void setCardnum(String cardnum) {
            this.cardnum = cardnum;
        }

        public FilecardidBean getFilecardid() {
            return filecardid;
        }

        public void setFilecardid(FilecardidBean filecardid) {
            this.filecardid = filecardid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public int getProcardid() {
            return procardid;
        }

        public void setProcardid(int procardid) {
            this.procardid = procardid;
        }

        public Object getUpdatedate() {
            return updatedate;
        }

        public void setUpdatedate(Object updatedate) {
            this.updatedate = updatedate;
        }

        public static class PbatchidBean {
            /**
             * pbatchid : 16
             * batchdate : {"nanos":0,"time":1525968000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":5,"date":11}
             * saleprice : 22
             * remark :
             * batchnum : 123
             * createdate : {"nanos":0,"time":1525968000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":5,"date":11}
             * batchdateStr : 2018-05-11
             * proName : 瓜子
             * isdel : 1
             * proid : {"proId":12,"remark":"","bzqunit":"1","probzq":"30","userid":{"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"暂无","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8},"address":"北京西城","role":""},"state":"1","createdate":{"nanos":0,"time":1525795200000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":9},"proCode":"","barcode":"147","protype":"1","mernameorplace":"147255","proName":"瓜子","trademark":"沙土","meas":"11","obtype":{"content":"方便食品","grade":"2","code":"0701","parentCode":{"content":"方便食品","grade":"1","code":"07","parentCode":null,"isValid":"1"},"isValid":"1"},"updatedate":null,"batchCount":0,"meaunit":"袋"}
             * sellprice : 25
             * updatedate : null
             * enterprice : 23
             */

            private int pbatchid;
            private BatchdateBean batchdate;
            private String saleprice;
            private String remark;
            private String batchnum;
            private CreatedateBean createdate;
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

            public CreatedateBean getCreatedate() {
                return createdate;
            }

            public void setCreatedate(CreatedateBean createdate) {
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
                 * time : 1525968000000
                 * minutes : 0
                 * seconds : 0
                 * hours : 0
                 * month : 4
                 * timezoneOffset : -480
                 * year : 118
                 * day : 5
                 * date : 11
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

            public static class CreatedateBean {
                /**
                 * nanos : 0
                 * time : 1525968000000
                 * minutes : 0
                 * seconds : 0
                 * hours : 0
                 * month : 4
                 * timezoneOffset : -480
                 * year : 118
                 * day : 5
                 * date : 11
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
                 * proId : 12
                 * remark :
                 * bzqunit : 1
                 * probzq : 30
                 * userid : {"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"暂无","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"timezoneOffset":-480,"year":118,"day":2,"date":8},"address":"北京西城","role":""}
                 * state : 1
                 * createdate : {"nanos":0,"time":1525795200000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":3,"date":9}
                 * proCode :
                 * barcode : 147
                 * protype : 1
                 * mernameorplace : 147255
                 * proName : 瓜子
                 * trademark : 沙土
                 * meas : 11
                 * obtype : {"content":"方便食品","grade":"2","code":"0701","parentCode":{"content":"方便食品","grade":"1","code":"07","parentCode":null,"isValid":"1"},"isValid":"1"}
                 * updatedate : null
                 * batchCount : 0
                 * meaunit : 袋
                 */

                private int proId;
                private String remark;
                private String bzqunit;
                private String probzq;
                private UseridBean userid;
                private String state;
                private CreatedateBeanX createdate;
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

                public CreatedateBeanX getCreatedate() {
                    return createdate;
                }

                public void setCreatedate(CreatedateBeanX createdate) {
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
                     * cid : {"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"暂无","cid":2,"cname":"普众科技（北京）有限公司"}
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
                    private CidBean cid;
                    private String logname;
                    private String password;
                    private String telphone;
                    private String username;
                    private String email;
                    private CreatdateBean creatdate;
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

                    public String getEmail() {
                        return email;
                    }

                    public void setEmail(String email) {
                        this.email = email;
                    }

                    public CreatdateBean getCreatdate() {
                        return creatdate;
                    }

                    public void setCreatdate(CreatdateBean creatdate) {
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

                    public static class CidBean {
                        /**
                         * address : 北京
                         * clicence : 987654321
                         * enddateStr : 2018-04-23
                         * state : 0
                         * qyfr : 刘万荣
                         * enddate : {"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23}
                         * jjfw : 暂无
                         * cid : 2
                         * cname : 普众科技（北京）有限公司
                         */

                        private String address;
                        private String clicence;
                        private String enddateStr;
                        private String state;
                        private String qyfr;
                        private EnddateBean enddate;
                        private String jjfw;
                        private int cid;
                        private String cname;

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

                        public EnddateBean getEnddate() {
                            return enddate;
                        }

                        public void setEnddate(EnddateBean enddate) {
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

                        public static class EnddateBean {
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

                    public static class CreatdateBean {
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

                public static class CreatedateBeanX {
                    /**
                     * nanos : 0
                     * time : 1525795200000
                     * minutes : 0
                     * seconds : 0
                     * hours : 0
                     * month : 4
                     * timezoneOffset : -480
                     * year : 118
                     * day : 3
                     * date : 9
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

                public static class ObtypeBean {
                    /**
                     * content : 方便食品
                     * grade : 2
                     * code : 0701
                     * parentCode : {"content":"方便食品","grade":"1","code":"07","parentCode":null,"isValid":"1"}
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
                         * content : 方便食品
                         * grade : 1
                         * code : 07
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

        public static class CharddateBean {
            /**
             * nanos : 0
             * time : 1525968000000
             * minutes : 0
             * seconds : 0
             * hours : 0
             * month : 4
             * timezoneOffset : -480
             * year : 118
             * day : 5
             * date : 11
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

        public static class FilecardidBean {
            /**
             * filecardid : 53
             * remark :
             * path : 6969.png
             * state : 1
             * uploaddate : {"nanos":0,"time":1525968000000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":118,"day":5,"date":11}
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
                 * time : 1525968000000
                 * minutes : 0
                 * seconds : 0
                 * hours : 0
                 * month : 4
                 * timezoneOffset : -480
                 * year : 118
                 * day : 5
                 * date : 11
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

    public BatchBean getBatch() {
        return batch;
    }

    public void setBatch(BatchBean batch) {
        this.batch = batch;
    }


    public static class BatchBean {
        /**
         * pbatchid : 25
         * batchdate : {"nanos":0,"time":1527609600000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":30}
         * saleprice :
         * remark :
         * batchnum :
         * createdate : {"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":16}
         * batchdateStr : 2018-05-30
         * proName : 可乐
         * isdel : 1
         * proid : {"proId":15,"remark":"","bzqunit":"1","probzq":"25","userid":{"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"filecardid":{"remark":"","filecardid":116,"state":"1","path":"certification.png","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":16},"parentpath":"201805"},"address":"北京","clicence":"987654321252525","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","jjfw":"软件工程","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏宝宝","address":"北京西城","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"year":118,"timezoneOffset":-480,"day":2,"date":8},"email":"1050507901@qq.com","role":""},"state":"1","createdate":{"nanos":0,"time":1526227200000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":1,"date":14},"proCode":"898881","barcode":"111111","protype":"0","mernameorplace":"667","proName":"可乐","trademark":"可口可乐","meas":"ML","obtype":{"content":"食用植物油","grade":"3","code":"020101","parentCode":{"content":"食用植物油","grade":"2","code":"0201","parentCode":{"content":"食用油、油脂及其制品","grade":"1","code":"02","parentCode":null,"isValid":"1"},"isValid":"1"},"isValid":"1"},"updatedate":null,"batchCount":0,"meaunit":"78"}
         * enterprice : 5.89
         * sellprice :
         * updatedate : null
         */

        private int pbatchid;
        private BatchdateBean batchdate;
        private String saleprice;
        private String remark;
        private String batchnum;
        private CreatedateBean createdate;
        private String batchdateStr;
        private String proName;
        private String isdel;
        private ProidBean proid;
        private String enterprice;
        private String sellprice;
        private Object updatedate;

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

        public CreatedateBean getCreatedate() {
            return createdate;
        }

        public void setCreatedate(CreatedateBean createdate) {
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

        public String getEnterprice() {
            return enterprice;
        }

        public void setEnterprice(String enterprice) {
            this.enterprice = enterprice;
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

        public static class BatchdateBean {
            /**
             * nanos : 0
             * time : 1527609600000
             * minutes : 0
             * seconds : 0
             * hours : 0
             * month : 4
             * year : 118
             * timezoneOffset : -480
             * day : 3
             * date : 30
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

        public static class CreatedateBean {
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

        public static class ProidBean {
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
            private CreatedateBeanX createdate;
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

            public CreatedateBeanX getCreatedate() {
                return createdate;
            }

            public void setCreatedate(CreatedateBeanX createdate) {
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

            public static class CreatedateBeanX {
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
    }


}
