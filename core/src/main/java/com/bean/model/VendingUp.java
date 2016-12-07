package com.bean.model;

import java.util.Date;

public class VendingUp {
    /**
     * vending_up_id
     */
    private Long vendingUpId;

    /**
     * 货道id
     */
    private Long trackId;

    /**
     * 货品id
     */
    private Long productId;

    /**
     * 货品数量
     */
    private Long productNum;

    /**
     * 操作日期
     */
    private Date operatorDate;

    /**
     * 管理员id
     */
    private Long managerId;

    public Long getVendingUpId() {
        return vendingUpId;
    }

    public void setVendingUpId(Long vendingUpId) {
        this.vendingUpId = vendingUpId;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
    private  String trackCode;//货道码

    public String getTrackCode() {
        return trackCode;
    }

    public void setTrackCode(String trackCode) {
        this.trackCode = trackCode;
    }
}