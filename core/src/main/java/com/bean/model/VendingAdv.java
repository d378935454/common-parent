package com.bean.model;

import java.util.Date;

public class VendingAdv {
    /**
     * 
     */
    private Long id;

    /**
     * 内容
     */
    private String conment;

    /**
     * 同步标识
     */
    private Date tokenTime;

    /**
     * 屏幕类型 1小屏 2大屏
     */
    private Byte screenType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConment() {
        return conment;
    }

    public void setConment(String conment) {
        this.conment = conment == null ? null : conment.trim();
    }

    public Date getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Date tokenTime) {
        this.tokenTime = tokenTime;
    }

    public Byte getScreenType() {
        return screenType;
    }

    public void setScreenType(Byte screenType) {
        this.screenType = screenType;
    }
}