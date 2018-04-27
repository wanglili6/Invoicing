package com.mtecc.mmp.invoicing.activity.role.bean;

/**
 * Created by wll on 2018/4/26.
 */

public class RoleAddBean {
    private int usergpid;
    private String usergpname;//角色名称
    private String usergpdesc;//角色描述
    private int  cid;//所属公司
    private String isactive;//是否可用

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

    public String getUsergpdesc() {
        return usergpdesc;
    }

    public void setUsergpdesc(String usergpdesc) {
        this.usergpdesc = usergpdesc;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }
}
