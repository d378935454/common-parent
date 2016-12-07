package com.bean.model;

import java.util.Date;

public class Vendor {
    /**
     * 
     */
    private Long id;

    /**
     * 编号
     */
    private String vendorNum;

    /**
     * 名称
     */
    private String vendorName;

    /**
     * 售卖机型号id
     */
    private String fkVendorType;

    /**
     * 货道总数
     */
    private Long fkCdVendorTemplate;

    /**
     * 是否已删除
     */
    private Integer vendorDelFlag;

    /**
     * 状态
     */
    private String vendorStatus;

    /**
     * 地址
     */
    private Long fkVendorAddr1;

    /**
     * 
     */
    private Long fkVendorAddr2;

    /**
     * 
     */
    private Long fkVendorAddr3;

    /**
     * 方位
     */
    private String vendorPosition;

    /**
     * 信息更新时间
     */
    private Date updateTime;

    /**
     * 经度
     */
    private String vendorLongitude;

    /**
     * 纬度
     */
    private String vendorLatitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorNum() {
        return vendorNum;
    }

    public void setVendorNum(String vendorNum) {
        this.vendorNum = vendorNum == null ? null : vendorNum.trim();
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName == null ? null : vendorName.trim();
    }

    public String getFkVendorType() {
        return fkVendorType;
    }

    public void setFkVendorType(String fkVendorType) {
        this.fkVendorType = fkVendorType == null ? null : fkVendorType.trim();
    }

    public Long getFkCdVendorTemplate() {
        return fkCdVendorTemplate;
    }

    public void setFkCdVendorTemplate(Long fkCdVendorTemplate) {
        this.fkCdVendorTemplate = fkCdVendorTemplate;
    }

    public Integer getVendorDelFlag() {
        return vendorDelFlag;
    }

    public void setVendorDelFlag(Integer vendorDelFlag) {
        this.vendorDelFlag = vendorDelFlag;
    }

    public String getVendorStatus() {
        return vendorStatus;
    }

    public void setVendorStatus(String vendorStatus) {
        this.vendorStatus = vendorStatus == null ? null : vendorStatus.trim();
    }

    public Long getFkVendorAddr1() {
        return fkVendorAddr1;
    }

    public void setFkVendorAddr1(Long fkVendorAddr1) {
        this.fkVendorAddr1 = fkVendorAddr1;
    }

    public Long getFkVendorAddr2() {
        return fkVendorAddr2;
    }

    public void setFkVendorAddr2(Long fkVendorAddr2) {
        this.fkVendorAddr2 = fkVendorAddr2;
    }

    public Long getFkVendorAddr3() {
        return fkVendorAddr3;
    }

    public void setFkVendorAddr3(Long fkVendorAddr3) {
        this.fkVendorAddr3 = fkVendorAddr3;
    }

    public String getVendorPosition() {
        return vendorPosition;
    }

    public void setVendorPosition(String vendorPosition) {
        this.vendorPosition = vendorPosition == null ? null : vendorPosition.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getVendorLongitude() {
        return vendorLongitude;
    }

    public void setVendorLongitude(String vendorLongitude) {
        this.vendorLongitude = vendorLongitude == null ? null : vendorLongitude.trim();
    }

    public String getVendorLatitude() {
        return vendorLatitude;
    }

    public void setVendorLatitude(String vendorLatitude) {
        this.vendorLatitude = vendorLatitude == null ? null : vendorLatitude.trim();
    }
}