package com.mtecc.mmp.invoicing.activity.comodity.bean;

import java.util.List;

/**
 * Created by wll on 2018/5/10.
 */

public class DicBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
}
