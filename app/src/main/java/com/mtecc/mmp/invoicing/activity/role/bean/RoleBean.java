package com.mtecc.mmp.invoicing.activity.role.bean;

import java.util.List;

/**
 * Created by wll on 2018/4/25.
 */

public class RoleBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * isactive : 可用
         * usergpdesc : 普通营业员
         * usergpid : 23
         * usergpname : 店员
         * cid : 2
         */

        private String isactive;
        private String usergpdesc;
        private int usergpid;
        private String usergpname;
        private int cid;

        public String getIsactive() {
            return isactive;
        }

        public void setIsactive(String isactive) {
            this.isactive = isactive;
        }

        public String getUsergpdesc() {
            return usergpdesc;
        }

        public void setUsergpdesc(String usergpdesc) {
            this.usergpdesc = usergpdesc;
        }

        public int getUsergpid() {
            return usergpid;
        }

        public void setUsergpid(int usergpid) {
            this.usergpid = usergpid;
        }

        public String getUsergpname() {
            return usergpname;
        }

        public void setUsergpname(String usergpname) {
            this.usergpname = usergpname;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }
    }
}
