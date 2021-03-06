package com.mtecc.mmp.invoicing.activity.employee.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wll on 2018/4/24.
 */

public class EmployeeListBean implements Serializable {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * sex : 0
         * userage : 20
         * empstate : 0
         * cardnum : 612724199107195693
         * userid : 57
         * creatdateStr : 2018-04-19
         * cid : {"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"","qyfr":"刘万荣","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":1,"date":23},"jjfw":"暂无","cid":2,"cname":"普众科技（北京）有限公司"}
         * logname : zx
         * password : MDAwMDAw
         * telphone : 19910580240
         * username : 轴心
         * email : 17127877@bjtu.edu.cn
         * creatdate : {"nanos":0,"time":1524126502000,"minutes":28,"seconds":22,"hours":16,"month":3,"timezoneOffset":-480,"year":118,"day":4,"date":19}
         * address : 暂无
         * role : 店长
         */

        private String sex;
        private String userage;
        private String empstate;
        private String cardnum;
        private int userid;
        private String creatdateStr;
        private CidBean cid;
        private String logname;
        private String shopname;
        private String password;
        private String telphone;
        private String username;
        private String email;
        private CreatdateBean creatdate;
        private String address;
        private String role;
        private String roleid;

        public String getRoleid() {
            return roleid;
        }

        public void setRoleid(String roleid) {
            this.roleid = roleid;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

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

        public static class CidBean implements Serializable {
            /**
             * address : 北京
             * clicence : 987654321
             * enddateStr : 2018-04-23
             * state :
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

            public static class EnddateBean implements Serializable {
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

        public static class CreatdateBean implements Serializable {
            /**
             * nanos : 0
             * time : 1524126502000
             * minutes : 28
             * seconds : 22
             * hours : 16
             * month : 3
             * timezoneOffset : -480
             * year : 118
             * day : 4
             * date : 19
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
