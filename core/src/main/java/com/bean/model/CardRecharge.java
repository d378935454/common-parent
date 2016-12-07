package com.bean.model;

import java.math.BigDecimal;

public class CardRecharge {
    /**
     * 充值记录ID
     */
    private Long rechargeId;

    /**
     * 充值订单
     */
    private String orderNum;

    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount;

    /**
     * 赠送金额
     */
    private BigDecimal presentAmount;

    /**
     * 充值类型 1、现金 2、微信 3、支付宝
     */
    private String rechargeType;

    /**
     * 充值方式 1、网上自注册 2、手机 3、售卖机 4、地推
     */
    private String rechargeWay;

    /**
     * 售卖机ID
     */
    private Long vendingId;

    /**
     * 地推人员ID
     */
    private Long marketerId;

    /**
     * 付款标志 0 未付款 1 成功付款
     */
    private String payStatus;

    public Long getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Long rechargeId) {
        this.rechargeId = rechargeId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getPresentAmount() {
        return presentAmount;
    }

    public void setPresentAmount(BigDecimal presentAmount) {
        this.presentAmount = presentAmount;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType == null ? null : rechargeType.trim();
    }

    public String getRechargeWay() {
        return rechargeWay;
    }

    public void setRechargeWay(String rechargeWay) {
        this.rechargeWay = rechargeWay == null ? null : rechargeWay.trim();
    }

    public Long getVendingId() {
        return vendingId;
    }

    public void setVendingId(Long vendingId) {
        this.vendingId = vendingId;
    }

    public Long getMarketerId() {
        return marketerId;
    }

    public void setMarketerId(Long marketerId) {
        this.marketerId = marketerId;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }
}