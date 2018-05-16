package com.mtecc.mmp.invoicing.activity.manager.comodity.bean;

import java.util.List;

/**
 * Created by wll on 2018/5/8.
 */

public class GoodsTypeBean  {


    private List<BzqBean> bzq;
    private List<SplbListBean> splbList;

    public List<BzqBean> getBzq() {
        return bzq;
    }

    public void setBzq(List<BzqBean> bzq) {
        this.bzq = bzq;
    }

    public List<SplbListBean> getSplbList() {
        return splbList;
    }

    public void setSplbList(List<SplbListBean> splbList) {
        this.splbList = splbList;
    }

    public static class BzqBean {
        public BzqBean(String BUSINNAME, int BUSINID) {
            this.BUSINNAME = BUSINNAME;
            this.BUSINID = BUSINID;
        }

        /**
         * dicid : 51
         * busintypename : 保质期单位
         * BUSINNAME : 天
         * status : 0
         * busintypeid : BZQDW
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

    public static class SplbListBean {
        public SplbListBean(String content, String code) {
            this.content = content;
            this.code = code;
        }

        /**
         * content : 粮食加工品
         * grade : 1
         * code : 01
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
