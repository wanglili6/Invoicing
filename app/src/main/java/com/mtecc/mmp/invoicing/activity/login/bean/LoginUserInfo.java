package com.mtecc.mmp.invoicing.activity.login.bean;

/**
 * Created by wll on 2018/4/23.
 */

public class LoginUserInfo {

    /**
     * result : 200
     * shop : {"shopid":1,"createdateStr":"2018-04-18","shopstate":"0","shopnum":1,"createdate":{"nanos":0,"time":1524034992000,"minutes":3,"seconds":12,"hours":15,"month":3,"year":118,"timezoneOffset":-480,"day":3,"date":18},"shopname":"我的主店铺","shopaddress":"rt","createman":{"sex":"1","userage":"26","empstate":"0","cardnum":"612724199107190760","userid":47,"creatdateStr":"2018-04-17","shopname":"","cid":{"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","jjfw":"暂无","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏","address":"暂无","creatdate":{"nanos":0,"time":1523959662000,"minutes":7,"seconds":42,"hours":18,"month":3,"year":118,"timezoneOffset":-480,"day":2,"date":17},"email":"1050507901@qq.com","role":""},"cid":{"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","jjfw":"暂无","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"普众科技（北京）有限公司"}}
     * isuseradmin : false
     * user : {"sex":"1","userage":"23","empstate":"0","cardnum":"612724199107192356","userid":56,"creatdateStr":"2018-04-20","shopname":"","cid":{"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"暂无","cid":2,"cname":"普众科技（北京）有限公司"},"logname":"wll","password":"MTExMTEx","telphone":"18810580241","username":"王丽丽","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1524187456000,"minutes":24,"seconds":16,"hours":9,"month":3,"timezoneOffset":-480,"year":118,"day":5,"date":20},"address":"暂无","role":""}
     * ishavemoreshop : 1
     */

    private int result;
    private ShopBean shop;
    private boolean isuseradmin;
    private UserBean user;
    private int ishavemoreshop;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ShopBean getShop() {
        return shop;
    }

    public void setShop(ShopBean shop) {
        this.shop = shop;
    }

    public boolean isIsuseradmin() {
        return isuseradmin;
    }

    public void setIsuseradmin(boolean isuseradmin) {
        this.isuseradmin = isuseradmin;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getIshavemoreshop() {
        return ishavemoreshop;
    }

    public void setIshavemoreshop(int ishavemoreshop) {
        this.ishavemoreshop = ishavemoreshop;
    }

    public static class ShopBean {
        /**
         * shopid : 1
         * createdateStr : 2018-04-18
         * shopstate : 0
         * shopnum : 1
         * createdate : {"nanos":0,"time":1524034992000,"minutes":3,"seconds":12,"hours":15,"month":3,"year":118,"timezoneOffset":-480,"day":3,"date":18}
         * shopname : 我的主店铺
         * shopaddress : rt
         * createman : {"sex":"1","userage":"26","empstate":"0","cardnum":"612724199107190760","userid":47,"creatdateStr":"2018-04-17","shopname":"","cid":{"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","jjfw":"暂无","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"普众科技（北京）有限公司"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏","address":"暂无","creatdate":{"nanos":0,"time":1523959662000,"minutes":7,"seconds":42,"hours":18,"month":3,"year":118,"timezoneOffset":-480,"day":2,"date":17},"email":"1050507901@qq.com","role":""}
         * cid : {"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","jjfw":"暂无","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"普众科技（北京）有限公司"}
         */

        private int shopid;
        private String createdateStr;
        private String shopstate;
        private int shopnum;
        private CreatedateBean createdate;
        private String shopname;
        private String shopaddress;
        private CreatemanBean createman;
        private CidBeanX cid;

        public int getShopid() {
            return shopid;
        }

        public void setShopid(int shopid) {
            this.shopid = shopid;
        }

        public String getCreatedateStr() {
            return createdateStr;
        }

        public void setCreatedateStr(String createdateStr) {
            this.createdateStr = createdateStr;
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

        public CreatedateBean getCreatedate() {
            return createdate;
        }

        public void setCreatedate(CreatedateBean createdate) {
            this.createdate = createdate;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getShopaddress() {
            return shopaddress;
        }

        public void setShopaddress(String shopaddress) {
            this.shopaddress = shopaddress;
        }

        public CreatemanBean getCreateman() {
            return createman;
        }

        public void setCreateman(CreatemanBean createman) {
            this.createman = createman;
        }

        public CidBeanX getCid() {
            return cid;
        }

        public void setCid(CidBeanX cid) {
            this.cid = cid;
        }

        public static class CreatedateBean {
            /**
             * nanos : 0
             * time : 1524034992000
             * minutes : 3
             * seconds : 12
             * hours : 15
             * month : 3
             * year : 118
             * timezoneOffset : -480
             * day : 3
             * date : 18
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

        public static class CreatemanBean {
            /**
             * sex : 1
             * userage : 26
             * empstate : 0
             * cardnum : 612724199107190760
             * userid : 47
             * creatdateStr : 2018-04-17
             * shopname :
             * cid : {"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","jjfw":"暂无","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"普众科技（北京）有限公司"}
             * logname : lixm
             * password : MDAwMDAw
             * telphone : 18810580240
             * username : 李晓敏
             * address : 暂无
             * creatdate : {"nanos":0,"time":1523959662000,"minutes":7,"seconds":42,"hours":18,"month":3,"year":118,"timezoneOffset":-480,"day":2,"date":17}
             * email : 1050507901@qq.com
             * role :
             */

            private String sex;
            private String userage;
            private String empstate;
            private String cardnum;
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
                 * address : 北京
                 * clicence : 987654321
                 * enddateStr : 2018-04-23
                 * state : 0
                 * qyfr : 刘万荣
                 * jjfw : 暂无
                 * enddate : {"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23}
                 * cid : 2
                 * cname : 普众科技（北京）有限公司
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
                 * time : 1523959662000
                 * minutes : 7
                 * seconds : 42
                 * hours : 18
                 * month : 3
                 * year : 118
                 * timezoneOffset : -480
                 * day : 2
                 * date : 17
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

        public static class CidBeanX {
            /**
             * address : 北京
             * clicence : 987654321
             * enddateStr : 2018-04-23
             * state : 0
             * qyfr : 刘万荣
             * jjfw : 暂无
             * enddate : {"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23}
             * cid : 2
             * cname : 普众科技（北京）有限公司
             */

            private String address;
            private String clicence;
            private String enddateStr;
            private String state;
            private String qyfr;
            private String jjfw;
            private EnddateBeanX enddate;
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

            public EnddateBeanX getEnddate() {
                return enddate;
            }

            public void setEnddate(EnddateBeanX enddate) {
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

            public static class EnddateBeanX {
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
    }

    public static class UserBean {
        /**
         * sex : 1
         * userage : 23
         * empstate : 0
         * cardnum : 612724199107192356
         * userid : 56
         * creatdateStr : 2018-04-20
         * shopname :
         * cid : {"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"暂无","cid":2,"cname":"普众科技（北京）有限公司"}
         * logname : wll
         * password : MTExMTEx
         * telphone : 18810580241
         * username : 王丽丽
         * email : 1050507901@qq.com
         * creatdate : {"nanos":0,"time":1524187456000,"minutes":24,"seconds":16,"hours":9,"month":3,"timezoneOffset":-480,"year":118,"day":5,"date":20}
         * address : 暂无
         * role :
         */

        private String sex;
        private String userage;
        private String empstate;
        private String cardnum;
        private int userid;
        private String creatdateStr;
        private String shopname;
        private CidBeanXX cid;
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

        public static class CidBeanXX {
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

        public static class CreatdateBeanX {
            /**
             * nanos : 0
             * time : 1524187456000
             * minutes : 24
             * seconds : 16
             * hours : 9
             * month : 3
             * timezoneOffset : -480
             * year : 118
             * day : 5
             * date : 20
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
