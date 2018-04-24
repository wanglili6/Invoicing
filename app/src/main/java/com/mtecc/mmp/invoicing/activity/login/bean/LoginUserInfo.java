package com.mtecc.mmp.invoicing.activity.login.bean;

/**
 * Created by wll on 2018/4/23.
 */

public class LoginUserInfo {

    /**
     * result : 200
     * user : {"sex":"1","userage":"26","empstate":"0","cardnum":"612724199107190760","userid":47,"creatdateStr":"2018-04-17","cid":{"address":"暂无","clicence":"66546546","state":"0","qyfr":"李晓敏","enddate":{"nanos":0,"time":1523894400000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":2,"date":17},"jjfw":"暂无","cid":2,"cname":"普众科技"},"logname":"lixm","password":"MDAwMDAw","telphone":"18810580240","username":"李晓敏","email":"1050507901@qq.com","creatdate":{"nanos":0,"time":1523959662000,"minutes":7,"seconds":42,"hours":18,"month":3,"timezoneOffset":-480,"year":118,"day":2,"date":17},"address":"暂无","role":""}
     */

    private int result;
    private UserBean user;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * sex : 1
         * userage : 26
         * empstate : 0
         * cardnum : 612724199107190760
         * userid : 47
         * creatdateStr : 2018-04-17
         * cid : {"address":"暂无","clicence":"66546546","state":"0","qyfr":"李晓敏","enddate":{"nanos":0,"time":1523894400000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":2,"date":17},"jjfw":"暂无","cid":2,"cname":"普众科技"}
         * logname : lixm
         * password : MDAwMDAw
         * telphone : 18810580240
         * username : 李晓敏
         * email : 1050507901@qq.com
         * creatdate : {"nanos":0,"time":1523959662000,"minutes":7,"seconds":42,"hours":18,"month":3,"timezoneOffset":-480,"year":118,"day":2,"date":17}
         * address : 暂无
         * role :
         */

        private String sex;
        private String userage;
        private String empstate;
        private String cardnum;
        private int userid;
        private String creatdateStr;
        private CidBean cid;
        private String logname;
        private String password;
        private String telphone;
        private String username;
        private String email;
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
             * address : 暂无
             * clicence : 66546546//
             * state : 0
             * qyfr : 李晓敏
             * enddate : {"nanos":0,"time":1523894400000,"minutes":0,"seconds":0,"hours":0,"month":3,"timezoneOffset":-480,"year":118,"day":2,"date":17}
             * jjfw : 暂无
             * cid : 2
             * cname : 普众科技
             */

            private String address;
            private String clicence;
            private String state;
            private String qyfr;
            private String enddateStr;
            private String jjfw;
            private int cid;
            private String cname;

            public String getEnddateStr() {
                return enddateStr;
            }

            public void setEnddateStr(String enddateStr) {
                this.enddateStr = enddateStr;
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


        }


    }
}
