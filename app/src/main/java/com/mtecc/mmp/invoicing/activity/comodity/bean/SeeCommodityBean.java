package com.mtecc.mmp.invoicing.activity.comodity.bean;

/**
 * Created by wll on 2018/5/10.
 */

public class SeeCommodityBean {

    /**
     * data : {"proId":14,"remark":"","bzqunit":"2","probzq":"6","userid":{"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"??","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"???","jjfw":"??","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"????????????"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"?????","address":"????","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"year":118,"timezoneOffset":-480,"day":2,"date":8},"email":"1050507901@qq.com","role":""},"state":"1","createdate":{"nanos":0,"time":1525795200000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":9},"proCode":"","barcode":"36","protype":"1","mernameorplace":"3","proName":"??","trademark":"36","meas":"???","obtype":{"content":"???","grade":"1","code":"05","parentCode":null,"isValid":"1"},"updatedate":null,"batchCount":0,"meaunit":"36"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * proId : 14
         * remark :
         * bzqunit : 2
         * probzq : 6
         * userid : {"sex":"1","userage":"25","empstate":"0","cardnum":"612724199107190760","roleid":"","userid":47,"creatdateStr":"2018-05-08","shopname":"","cid":{"address":"??","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"???","jjfw":"??","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"????????????"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"?????","address":"????","creatdate":{"nanos":0,"time":1525751418000,"minutes":50,"seconds":18,"hours":11,"month":4,"year":118,"timezoneOffset":-480,"day":2,"date":8},"email":"1050507901@qq.com","role":""}
         * state : 1
         * createdate : {"nanos":0,"time":1525795200000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":9}
         * proCode :
         * barcode : 36
         * protype : 1
         * mernameorplace : 3
         * proName : ??
         * trademark : 36
         * meas : ???
         * obtype : {"content":"???","grade":"1","code":"05","parentCode":null,"isValid":"1"}
         * updatedate : null
         * batchCount : 0
         * meaunit : 36
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
             * cid : {"address":"??","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"???","jjfw":"??","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"????????????"}
             * logname : lixm
             * password : MDAwMDAw
             * telphone : 18810580240
             * username : ?????
             * address : ????
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
                 * address : ??
                 * clicence : 987654321
                 * enddateStr : 2018-04-23
                 * state : 0
                 * qyfr : ???
                 * jjfw : ??
                 * enddate : {"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23}
                 * cid : 2
                 * cname : ????????????
                 */

                private String address;
                private String clicence;
                private String enddateStr;
                private String state;
                private String qyfr;
                private String jjfw;
                private EnddateBean enddate;
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
             * time : 1525795200000
             * minutes : 0
             * seconds : 0
             * hours : 0
             * month : 4
             * year : 118
             * timezoneOffset : -480
             * day : 3
             * date : 9
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
             * content : 焙炒咖啡
             * grade : 3
             * code : 210101
             * parentCode : {"content":"焙炒咖啡","grade":"2","code":"2101","parentCode":{"content":"食糖","grade":"1","code":"21","parentCode":null,"isValid":"1"},"isValid":"1"}
             * isValid : 1
             */

            private String content;
            private String grade;
            private String code;
            private CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX parentCode;
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

            public CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX getParentCode() {
                return parentCode;
            }

            public void setParentCode(CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX parentCode) {
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
                 * content : 焙炒咖啡
                 * grade : 2
                 * code : 2101
                 * parentCode : {"content":"食糖","grade":"1","code":"21","parentCode":null,"isValid":"1"}
                 * isValid : 1
                 */

                private String content;
                private String grade;
                private String code;
                private CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX.ParentCodeBean parentCode;
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

                public CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX.ParentCodeBean getParentCode() {
                    return parentCode;
                }

                public void setParentCode(CommodityExistedBean.DataBean.ObtypeBean.ParentCodeBeanX.ParentCodeBean parentCode) {
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
                     * content : 食糖
                     * grade : 1
                     * code : 21
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
