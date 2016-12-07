package com.bean.model;

import java.math.BigDecimal;
import java.util.Date;

public class CardRecord {
    /**
     * 储值记录ID
     */
    private Long recordId;

    /**
     * 储值卡ID
     */
    private Long cardId;

    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 时间
     */
    private Date recordDate;

    /**
     * 0 充值  1消费类型
     */
    private String recordType;

    /**
     * 消费/充值订单ID
     */
    private Long orderId;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType == null ? null : recordType.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}