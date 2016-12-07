package com.bean.model;

import java.util.Date;

public class CaptchaRecord {
    /**
     * 主键id
     */
    private Long captchaId;

    /**
     * 目标手机号
     */
    private String targetMobile;

    /**
     * 验证码
     */
    private String checkCode;

    /**
     * 验证码创建时间
     */
    private Date createTime;

    /**
     * 验证码操作类型 1 注册
     */
    private Long operateType;

    public Long getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(Long captchaId) {
        this.captchaId = captchaId;
    }

    public String getTargetMobile() {
        return targetMobile;
    }

    public void setTargetMobile(String targetMobile) {
        this.targetMobile = targetMobile == null ? null : targetMobile.trim();
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode == null ? null : checkCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOperateType() {
        return operateType;
    }

    public void setOperateType(Long operateType) {
        this.operateType = operateType;
    }
}