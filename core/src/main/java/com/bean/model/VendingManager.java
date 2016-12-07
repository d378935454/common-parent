package com.bean.model;

public class VendingManager {
    /**
     * 
     */
    private Long vendingManagerId;

    /**
     * 
     */
    private Long vendingId;

    /**
     * 
     */
    private String managerCode;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String pwd;

    /**
     * 
     */
    private String rfidCode;

    public Long getVendingManagerId() {
        return vendingManagerId;
    }

    public void setVendingManagerId(Long vendingManagerId) {
        this.vendingManagerId = vendingManagerId;
    }

    public Long getVendingId() {
        return vendingId;
    }

    public void setVendingId(Long vendingId) {
        this.vendingId = vendingId;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode == null ? null : managerCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getRfidCode() {
        return rfidCode;
    }

    public void setRfidCode(String rfidCode) {
        this.rfidCode = rfidCode == null ? null : rfidCode.trim();
    }
}