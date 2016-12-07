package com.bean.model;

public class CanbinetManager {
    /**
     * 
     */
    private Long id;

    /**
     * 管理员姓名
     */
    private String name;

    /**
     * 卡号
     */
    private String cardCode;

    /**
     * 物理卡号
     */
    private String rfidCode;

    /**
     * 权限 超级管理员 0； 一般管理员1； 配货员2
     */
    private Integer role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode == null ? null : cardCode.trim();
    }

    public String getRfidCode() {
        return rfidCode;
    }

    public void setRfidCode(String rfidCode) {
        this.rfidCode = rfidCode == null ? null : rfidCode.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}