package com.mtecc.mmp.invoicing.base;

/**
 * Created by wll on 2018/4/18.
 * 常量类
 */

public class InvoicingConstants {
    //基础地址
    public static final String BASE_URL = "http://192.168.1.114:8080/SSMSAPP";
    //图片地址
    public static final String IMAGEURL = "http://192.168.1.144:8080/image/";
    //登陆
    public static final String LOGIN_URL = "/app/login/toMain.htm";
    //选择店铺
    public static final String SELECT_SHOP_URL = "/app/chooseshop.htm";
    //验证重复注册用户名
    public static final String ValidateLogName_URL = "/app/login/validateLogName.htm";
    //注册
    public static final String register_URL = "/app/login/Regist.htm";
    //修改企业信息
    public static final String update_URL = "/app/changeCompanyInfo.htm";
    //修改个人信息
    public static final String CangePersonInfo_URL = "/app/changePersonInfo.htm";
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
    //店铺添加员工
    public static final String bindMan_URL = "/app/shopmanage/bindMan.htm";
    //店铺删除员工
    public static final String unbindMan_URL = "/app/shopmanage/unbindMan.htm";
    //获取角色列表
    public static final String manlistforRole_URL = "/app/shopmanage/manlistforRole.htm";
    //分配角色
    public static final String bindRole_URL = "/app/shopmanage/bindRole.htm";

    //商品列表
    public static final String GoogSlistfortable_URL = "/app/goods/listfortable.htm";
    //商品添加
    public static final String AddGoogs_URL = "/app/goods/Add.htm";
    //商品快速查询
    public static final String searchGood_URL = "/app/goods/searchGood.htm";
    //商品查看
    public static final String lookGood_URL = "/app/goods/look.htm";
    //商品編輯
    public static final String EditGood_URL = "/app/goods/Edit.htm";
    //商品删除
    public static final String deleteGood_URL = "/app/goods/delete.htm";
    //商品类别
    public static final String toAdd_URL = "/app/goods/toAdd.htm";
    //批次列表
    public static final String batchList_URL = "/app/goodbatch/batchList.htm";
    //证件类型信息
    public static final String getDic_URL = "/app/goodbatch/getDic.htm";
    //添加批次
    public static final String addBatch_URL = "/app/goodbatch/addBatch.htm";
    //修改批次
    public static final String editBatch_URL = "/app/goodbatch/editBatch.htm";
    //删除
    public static final String deleteBatch_URL = "/app/goodbatch/deleteBatch.htm";
    //查看
    public static final String toEditBatch_URL = "/app/goodbatch/toEditBatch.htm";

    //分销商_经销商列表
    public static final String buyers_URL = "/app/buyers/listfortable.htm";
    //删除
    public static final String buyersdelete_URL = "/app/buyers/delete.htm";
    //查看
    public static final String buyerstoEdit_URL = "/app/buyers/toEdit.htm";
    //编辑
    public static final String buyersEdit_URL = "/app/buyers/Edit.htm";
    //增加
    public static final String buyersAdd_URL = "/app/buyers/Add.htm";

    //认证企业信息
    public static final String goCertificate_URL = "/app/certification/goCertificate.htm";
    //查看企业信息
    public static final String toLookCompany_URL = "/app/certification/toLook.htm";
    public static final String EditCompany_URL = "/app/certification/Edit.htm";


    //公司员工列表
    public static final String employee_listfortable_URL = "/app/employee/listfortable.htm";
    //获取公司下面所有可用员工
    public static final String manlistforcompany_URL = "/app/shopmanage/manlistforcompany.htm";
    //角色列表
    public static final String roleList_URL = "/app/rolemanage/listfortable.htm";
    //添加角色
    public static final String roleAdd_URL = "/app/rolemanage/Add.htm";
    public static final String roleEdit_URL = "/app/rolemanage/Edit.htm";
    public static final String roleDelete_URL = "/app/rolemanage/delete.htm";

    //添加员工
    public static final String employeeAdd_URL = "/app/employee/Add.htm";
    //编辑员工
    public static final String employeeEdit_URL = "/app/employee/Edit.htm";
    //删除
    public static final String employeedelete_URL = "/app/employee/delete.htm";


    //用户信息
    public static final String USER_NAME = "USER_NAME";//用户名
    public static final String isuseradmin = "isuseradmin";//用户名
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


    public static final String SHOP_ID = "SHOP_ID";//企业id


    //跳转基础信息界面的type
    public static final String BASE_INFO_TYPE = "baseInfoType";
    public static final String regis = "regis";//regis--注册;amend--修改
    public static final String regisName = "regisName";//regis--注册;amend--修改
    public static final String regisPWD = "regisPWD";//regis--注册;amend--修改
    public static final String amend = "amend";//regis--注册;amend--修改

    //跳转员工列表的type
    public static final String Employee_List_TYPE = "Employee_List_TYPE";
    public static final String SHOP_Employee = "SHOP_Employee";//shopEmployee--店铺员工
    public static final String companyEmployeeAdd = "companyEmployeeAdd";//公司员工列表

    // 跳转角色列表的type
    public static final String role_TYPE = "role_TYPE";
    public static final String role_select = "role_select";//选择角色
    public static final String role_see = "role_see";//查看角色


    public static final String shopEmployeeSelect = "shopEmployeeSelect";//店铺所有员工
    public static final String shopId = "shopId";//店铺员工id
    public static final String selectuserid = "selectuserid";//店铺员工id

    // 跳转添加员工界面的type
    public static final String EMPLOYEE_TYPE = "employeeType";
    public static final String EMPLOYEE_userId = "EMPLOYEE_userId";//编辑员工id
    public static final String EMPLOYEE_ADD = "employeeadd";//add--添加
    public static final String EMPLOYEE_EDIT = "employeeedit";//edit--编辑


    // 跳转添加商品界面的type
    public static final String COMMODITY_TYPE = "COMMODITY_TYPE";
    public static final String COMMODITY_Id = "COMMODITY_Id";//编辑商品id
    public static final String COMMODITY_ADD = "COMMODITY_ADD";//add--添加
    public static final String COMMODITY_EDIT = "COMMODITY_EDIT";//edit--编辑

    //选择批次
    public static final String select_Batch = "select_Batch";//edit--编辑
    public static final String select_Commiodty = "select_Commiodty";//edit--编辑
    public static final String select_Batch_money = "select_Batch_money";//edit--编辑
    public static final String select_Batch_num = "select_Batch_num";//edit--编辑


    // 跳转添加商品界面的type
    public static final String BATCH_TYPE = "BATCH_TYPE";
    public static final String BATCH_Add_list = "BATCH_Add_list";
    public static final String BATCH_Id = "BATCH_Id";//编辑商品id
    public static final String BATCH_ADD = "BATCH_ADD";//add--添加
    public static final String BATCH_Edit = "BATCH_Edit";//编辑

    //证件
    public static final String DOUCUMENT_TYPE = "DOUCUMENT_TYPE";//类型
    public static final String DOUCUMENT_ADD = "DOUCUMENT_ADD";//添加
    public static final String DOUCUMENT_EDIT = "DOUCUMENT_EDIT";//编辑


    public static final String Merchants_Distributor_type = "Merchants_Distributor_type";//分销商的id
    //分销商
    public static final String Distributor_ID = "Distributor_ID";//分销商的id
    public static final String Distributor_mertype = "1";//分销商的id
    public static final String Distributor_TYPE = "Distributor_TYPE";//类型
    public static final String Distributor_ADD = "Distributor_ADD";//添加


    public static final String Distributor_EDIT = "Distributor_EDIT";//编辑
    public static final String Merchants_ID = "Merchants_ID";//分销商的id
    public static final String Merchants_TYPE = "Merchants_TYPE";//类型
    public static final String Merchants_ADD = "Merchants_ADD";//添加
    public static final String Merchants_EDIT = "Merchants_EDIT";//编辑

    //进货商
    public static final String Merchants_mertype = "2";//进货商的type


}
