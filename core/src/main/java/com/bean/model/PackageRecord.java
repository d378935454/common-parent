package com.bean.model;

import java.util.Date;

public class PackageRecord {
    /**
     *
     */
    private Long id;

    /**
     * 包裹提取码
     */
    private String password;

    /**
     * 包裹编号
     */
    private String packageNo;

    /**
     * 云柜格子ID
     */
    private Long canbinetCellId;

    /**
     * 入柜日期
     */
    private Date packageInTime;

    /**
     * 有效日期
     */
    private Date packageValidTime;

    /**
     * 提取日期
     */
    private Date packageOutTime;

    /**
     * 状态（1待取，2自取，3代取）
     */
    private Integer packageStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo == null ? null : packageNo.trim();
    }

    public Long getCanbinetCellId() {
        return canbinetCellId;
    }

    public void setCanbinetCellId(Long canbinetCellId) {
        this.canbinetCellId = canbinetCellId;
    }

    public Date getPackageInTime() {
        return packageInTime;
    }

    public void setPackageInTime(Date packageInTime) {
        this.packageInTime = packageInTime;
    }

    public Date getPackageValidTime() {
        return packageValidTime;
    }

    public void setPackageValidTime(Date packageValidTime) {
        this.packageValidTime = packageValidTime;
    }

    public Date getPackageOutTime() {
        return packageOutTime;
    }

    public void setPackageOutTime(Date packageOutTime) {
        this.packageOutTime = packageOutTime;
    }

    public Integer getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(Integer packageStatus) {
        this.packageStatus = packageStatus;
    }

    private String OrderCode;//订单码
    private String Rfid;//会员id

    public String getRfid() {
        return Rfid;
    }

    public void setRfid(String rfid) {
        Rfid = rfid;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }
}