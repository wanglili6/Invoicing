package com.mtecc.mmp.invoicing.base;

/**
 * Created by wll on 2018/4/18.
 * 常量类
 */

public class InvoicingConstants {
    //基础地址
    public static final String BASE_URL = "http://192.168.1.144:8080/SSMSAPP";
    //登陆
    public static final String LOGIN_URL = "/app/login/toMain.htm";
    //验证重复注册用户名
    public static final String ValidateLogName_URL = "/app/login/validateLogName.htm";
    //注册
    public static final String register_URL = "/app/changeCompanyInfo.htm";
    //修改企业信息
    public static final String update_URL = "/app/changeCompanyInfo.htm";
    //修改密码
    public static final String changePwd_URL = "/app/changePwd.htm";
    //店铺列表
    public static final String shoplistfortable_URL = "/app/shopmanage/listfortable.htm";
    //添加店铺
    public static final String shopAdd_URL = "/app/shopmanage/Add.htm";
    //编辑店铺
    public static final String shopEdit_URL = "/app/shopmanage/Edit.htm";
    //删除店铺
    public static final String shopDel_URL = "/app/shopmanage/delete.htm";
    //店铺员工列表
    public static final String manlistfortable_URL = "/app/shopmanage/manlistfortable.htm";

    //公司员工列表
    public static final String employee_listfortable_URL = "/app/employee/listfortable.htm";


    //用户信息
    public static final String USER_NAME = "USER_NAME";//用户名
    public static final String USER_PWD = "USER_PWD";//用户密码
    public static final String USER_ID = "USER_ID";//用户id
    public static final String USER_CRESTER_TIMER_STR = "USER_CRESTER_TIMER_STR";//注册时间
    public static final String USER_TEL_PHONE = "USER_TEL_PHONE";//手机号
    public static final String USER_LOGNAME = "USER_LOGNAME";//登陆名
    public static final String USER_EMAIL = "USER_EMAIL";//邮箱
    public static final String USER_ADDRESS = "USER_ADDRESS";//地址
    public static final String USER_ROLE = "USER_ROLE";//角色
    public static final String USER_SEX = "USER_SEX";//性别
    public static final String USER_AGE = "USER_AGE";//年龄
    public static final String USER_CARDNUM = "USER_CARDNUM";//身份证号

    public static final String QY_NAME = "QY_NAME";//企业名称
    public static final String QY_ID = "QY_ID";//企业id
    public static final String QY_STATUS = "QY_STATUS";//企业状态
    public static final String QY_FAREN = "QY_FAREN";//企业法人
    public static final String QY_END_DATA = "QY_END_DATA";//有效期至
    public static final String QY_CODE = "QY_CODE";//营业执照号
    public static final String QY_JJFW = "QY_JJFW";//经营范围
    public static final String QY_ADDRESS = "QY_ADDRESS";//地址


    //跳转基础信息界面的type
    public static final String BASE_INFO_TYPE = "baseInfoType";
    public static final String regis = "regis";//regis--注册;amend--修改
    public static final String regisName = "regisName";//regis--注册;amend--修改
    public static final String regisPWD = "regisPWD";//regis--注册;amend--修改
    public static final String amend = "amend";//regis--注册;amend--修改

    //跳转员工列表的type
    public static final String Employee_List_TYPE = "Employee_List_TYPE";
    public static final String SHOP_Employee = "SHOP_Employee";//shopEmployee--店铺员工
    public static final String EmployeeAdd = "EmployeeAdd";//edit--编辑

    // 跳转添加员工界面的type
    public static final String EMPLOYEE_TYPE = "employeeType";
    public static final String EMPLOYEE_ADD = "employeeadd";//add--添加
    public static final String EMPLOYEE_EDIT = "employeeedit";//edit--编辑

}
