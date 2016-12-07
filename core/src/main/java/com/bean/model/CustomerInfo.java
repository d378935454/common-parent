package com.bean.model;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerInfo {
    /**
     * 
     */
    private Long infoId;

    /**
     * 会员ID
     */
    private Long customerId;

    /**
     * 真实姓名
     */
    private String infoRealname;

    /**
     * 身份证号码
     */
    private String infoCardid;

    /**
     * 性别(0保密1男2女)
     */
    private String infoGender;

    /**
     * 等级编号
     */
    private Long pointLevelId;

    /**
     * 会员等级名称
     */
    private String pointLevelName;

    /**
     * 会员截止日期
     */
    private Date pointLevelEndtime;

    /**
     * 生日
     */
    private String infoBirthday;

    /**
     * 注册IP
     */
    private String infoRegip;

    /**
     * 省份
     */
    private String infoProvince;

    /**
     * 城市
     */
    private String infoCity;

    /**
     * 区县
     */
    private String infoCounty;

    /**
     * 街道
     */
    private String infostreet;

    /**
     * 详细地址
     */
    private String infoAddress;

    /**
     * 婚姻状况(0保密1已婚2未婚) 
     */
    private String infoMarital;

    /**
     * 月收入
     */
    private String infoSalary;

    /**
     * 兴趣爱好
     */
    private String infoInterest;

    /**
     * 会员邮箱
     */
    private String infoEmail;

    /**
     * 会员手机
     */
    private String infoMobile;

    /**
     * 账户余额
     */
    private BigDecimal balanceSum;

    /**
     * 会员总积分
     */
    private Integer infoPointSum;

    /**
     * 会员类型
     */
    private Integer infoType;

    /**
     * 注册时间
     */
    private Date infoRegisterTime;

    /**
     * 修改时间 
     */
    private Date modifiedTime;

    /**
     * 删除标记(0否 1是)
     */
    private String delFlag;

    /**
     * 固定电话
     */
    private String infoPhone;

    /**
     * 邮编
     */
    private String infoZip;

    /**
     * 删除时间 
     */
    private Date delTime;

    /**
     * 
     */
    private String infoQq;

    /**
     * 
     */
    private String wangwangno;

    /**
     * 职业
     */
    private String infoProfession;

    /**
     * 教育程度
     */
    private String infoEducation;

    /**
     * 问题提示
     */
    private Integer questionId;

    /**
     * 提示答案
     */
    private String answer;

    /**
     * 1、网上自注册 2、手机 3、售卖机 4、地推
     */
    private String regMode;

    /**
     * 售卖机编号
     */
    private Long vendingId;

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getInfoRealname() {
        return infoRealname;
    }

    public void setInfoRealname(String infoRealname) {
        this.infoRealname = infoRealname == null ? null : infoRealname.trim();
    }

    public String getInfoCardid() {
        return infoCardid;
    }

    public void setInfoCardid(String infoCardid) {
        this.infoCardid = infoCardid == null ? null : infoCardid.trim();
    }

    public String getInfoGender() {
        return infoGender;
    }

    public void setInfoGender(String infoGender) {
        this.infoGender = infoGender == null ? null : infoGender.trim();
    }

    public Long getPointLevelId() {
        return pointLevelId;
    }

    public void setPointLevelId(Long pointLevelId) {
        this.pointLevelId = pointLevelId;
    }

    public String getPointLevelName() {
        return pointLevelName;
    }

    public void setPointLevelName(String pointLevelName) {
        this.pointLevelName = pointLevelName == null ? null : pointLevelName.trim();
    }

    public Date getPointLevelEndtime() {
        return pointLevelEndtime;
    }

    public void setPointLevelEndtime(Date pointLevelEndtime) {
        this.pointLevelEndtime = pointLevelEndtime;
    }

    public String getInfoBirthday() {
        return infoBirthday;
    }

    public void setInfoBirthday(String infoBirthday) {
        this.infoBirthday = infoBirthday == null ? null : infoBirthday.trim();
    }

    public String getInfoRegip() {
        return infoRegip;
    }

    public void setInfoRegip(String infoRegip) {
        this.infoRegip = infoRegip == null ? null : infoRegip.trim();
    }

    public String getInfoProvince() {
        return infoProvince;
    }

    public void setInfoProvince(String infoProvince) {
        this.infoProvince = infoProvince == null ? null : infoProvince.trim();
    }

    public String getInfoCity() {
        return infoCity;
    }

    public void setInfoCity(String infoCity) {
        this.infoCity = infoCity == null ? null : infoCity.trim();
    }

    public String getInfoCounty() {
        return infoCounty;
    }

    public void setInfoCounty(String infoCounty) {
        this.infoCounty = infoCounty == null ? null : infoCounty.trim();
    }

    public String getInfostreet() {
        return infostreet;
    }

    public void setInfostreet(String infostreet) {
        this.infostreet = infostreet == null ? null : infostreet.trim();
    }

    public String getInfoAddress() {
        return infoAddress;
    }

    public void setInfoAddress(String infoAddress) {
        this.infoAddress = infoAddress == null ? null : infoAddress.trim();
    }

    public String getInfoMarital() {
        return infoMarital;
    }

    public void setInfoMarital(String infoMarital) {
        this.infoMarital = infoMarital == null ? null : infoMarital.trim();
    }

    public String getInfoSalary() {
        return infoSalary;
    }

    public void setInfoSalary(String infoSalary) {
        this.infoSalary = infoSalary == null ? null : infoSalary.trim();
    }

    public String getInfoInterest() {
        return infoInterest;
    }

    public void setInfoInterest(String infoInterest) {
        this.infoInterest = infoInterest == null ? null : infoInterest.trim();
    }

    public String getInfoEmail() {
        return infoEmail;
    }

    public void setInfoEmail(String infoEmail) {
        this.infoEmail = infoEmail == null ? null : infoEmail.trim();
    }

    public String getInfoMobile() {
        return infoMobile;
    }

    public void setInfoMobile(String infoMobile) {
        this.infoMobile = infoMobile == null ? null : infoMobile.trim();
    }

    public BigDecimal getBalanceSum() {
        return balanceSum;
    }

    public void setBalanceSum(BigDecimal balanceSum) {
        this.balanceSum = balanceSum;
    }

    public Integer getInfoPointSum() {
        return infoPointSum;
    }

    public void setInfoPointSum(Integer infoPointSum) {
        this.infoPointSum = infoPointSum;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public Date getInfoRegisterTime() {
        return infoRegisterTime;
    }

    public void setInfoRegisterTime(Date infoRegisterTime) {
        this.infoRegisterTime = infoRegisterTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getInfoPhone() {
        return infoPhone;
    }

    public void setInfoPhone(String infoPhone) {
        this.infoPhone = infoPhone == null ? null : infoPhone.trim();
    }

    public String getInfoZip() {
        return infoZip;
    }

    public void setInfoZip(String infoZip) {
        this.infoZip = infoZip == null ? null : infoZip.trim();
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getInfoQq() {
        return infoQq;
    }

    public void setInfoQq(String infoQq) {
        this.infoQq = infoQq == null ? null : infoQq.trim();
    }

    public String getWangwangno() {
        return wangwangno;
    }

    public void setWangwangno(String wangwangno) {
        this.wangwangno = wangwangno == null ? null : wangwangno.trim();
    }

    public String getInfoProfession() {
        return infoProfession;
    }

    public void setInfoProfession(String infoProfession) {
        this.infoProfession = infoProfession == null ? null : infoProfession.trim();
    }

    public String getInfoEducation() {
        return infoEducation;
    }

    public void setInfoEducation(String infoEducation) {
        this.infoEducation = infoEducation == null ? null : infoEducation.trim();
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getRegMode() {
        return regMode;
    }

    public void setRegMode(String regMode) {
        this.regMode = regMode == null ? null : regMode.trim();
    }

    public Long getVendingId() {
        return vendingId;
    }

    public void setVendingId(Long vendingId) {
        this.vendingId = vendingId;
    }
}