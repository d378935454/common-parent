package com.bean.model;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerCard {
    /**
     * 储值卡id
     */
    private Long cardId;

    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 物理卡号
     */
    private String rfidCode;

    /**
     * 卡号
     */
    private String cardCode;

    /**
     * 密码
     */
    private String cardPwd;

    /**
     * 卡余额
     */
    private BigDecimal cardBalance;

    /**
     * 发卡日期
     */
    private Date issueDate;

    /**
     * 发卡人
     */
    private String issueOperator;

    /**
     * 地推人员ID
     */
    private Long marketerId;

    /**
     * 0 :售菜机  1 :地推  2: 客服
     */
    private String issueMode;

    /**
     * 0:正常 1:锁定 2:挂失
     */
    private String cardStatus;

    /**
     * 0 正常 1 无效
     */
    private String delFlag;

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

    public String getRfidCode() {
        return rfidCode;
    }

    public void setRfidCode(String rfidCode) {
        this.rfidCode = rfidCode == null ? null : rfidCode.trim();
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode == null ? null : cardCode.trim();
    }

    public String getCardPwd() {
        return cardPwd;
    }

    public void setCardPwd(String cardPwd) {
        this.cardPwd = cardPwd == null ? null : cardPwd.trim();
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueOperator() {
        return issueOperator;
    }

    public void setIssueOperator(String issueOperator) {
        this.issueOperator = issueOperator == null ? null : issueOperator.trim();
    }

    public Long getMarketerId() {
        return marketerId;
    }

    public void setMarketerId(Long marketerId) {
        this.marketerId = marketerId;
    }

    public String getIssueMode() {
        return issueMode;
    }

    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode == null ? null : issueMode.trim();
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus == null ? null : cardStatus.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}