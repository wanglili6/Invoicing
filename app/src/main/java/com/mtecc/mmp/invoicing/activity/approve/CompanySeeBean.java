package com.mtecc.mmp.invoicing.activity.approve;

/**
 * Created by wll on 2018/5/16.
 */

public class CompanySeeBean {


    /**
     * data : {"filecardid":{"remark":"","filecardid":112,"state":"1","path":"普众科技（北京）有限公司.png","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":16},"parentpath":"201805"},"address":"北京","clicence":"987654321","enddateStr":"2018-04-23","state":"0","qyfr":"刘万荣","jjfw":"","enddate":{"nanos":0,"time":1524412800000,"minutes":0,"seconds":0,"hours":0,"month":3,"year":118,"timezoneOffset":-480,"day":1,"date":23},"cid":2,"cname":"普众科技（北京）有限公司"}
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
         * filecardid : {"remark":"","filecardid":112,"state":"1","path":"普众科技（北京）有限公司.png","uploaddate":{"nanos":0,"time":1526400000000,"minutes":0,"seconds":0,"hours":0,"month":4,"year":118,"timezoneOffset":-480,"day":3,"date":16},"parentpath":"201805"}
         * address : 北京
         * clicence : 987654321
         * enddateStr : 2018-04-23
         * state : 0
         * qyfr : 刘万荣
         * jjfw :
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
             * filecardid : 112
             * state : 1
             * path : 普众科技（北京）有限公司.png
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
}
