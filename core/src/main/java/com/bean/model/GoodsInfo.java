package com.bean.model;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsInfo {
    /**
     * 主键ID
     */
    private Long goodsInfoId;

    /**
     * 货品图片
     */
    private String goodsInfoImgId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 货号
     */
    private String goodsInfoItemNo;

    /**
     * 货品名称
     */
    private String goodsInfoName;

    /**
     * 货品副标题
     */
    private String goodsInfoSubtitle;

    /**
     * 上架  0:下架   1:上架   2:未采集   3:线下
     */
    private String goodsInfoAdded;

    /**
     * 上架时间
     */
    private Date goodsInfoAddedTime;

    /**
     * 下架时间
     */
    private Date goodsInfoUnaddedTime;

    /**
     * 库存
     */
    private Long goodsInfoStock;

    /**
     * 销售价格
     */
    private BigDecimal goodsInfoPreferPrice;

    /**
     * 成本价
     */
    private BigDecimal goodsInfoCostPrice;

    /**
     * 市场价
     */
    private BigDecimal goodsInfoMarketPrice;

    /**
     * 重量
     */
    private BigDecimal goodsInfoWeight;

    /**
     * 创建人名称
     */
    private String goodsInfoCreateName;

    /**
     * 创建时间
     */
    private Date goodsInfoCreateTime;

    /**
     * 修改人名称
     */
    private String goodsInfoModifiedName;

    /**
     * 修改时间
     */
    private Date goodsInfoModifiedTime;

    /**
     * 删除人名称
     */
    private String goodsInfoDelName;

    /**
     * 删除时间
     */
    private Date goodsInfoDelTime;

    /**
     * 是否删除
     */
    private String goodsInfoDelflag;

    /**
     * 第三方店铺ID
     */
    private Long thirdId;

    /**
     * 第三方店铺名称
     */
    private String thirdName;

    /**
     * 0:不是第三方商品  1:第三方商品 2:B2B商品
     */
    private String isThird;

    /**
     * 商品ISBN
     */
    private String goodsInfoIsbn;

    /**
     * 是否在列表页显示
     */
    private String showList;

    /**
     * 
     */
    private String showMobile;

    /**
     * 
     */
    private String isCustomerDiscount;

    /**
     * 审核状态 0 未审核 1 审核中 2审核失败
     */
    private String auditStatus;

    /**
     * 1卖家包邮 0,买家自费
     */
    private String ismailbay;

    /**
     * 审核拒绝原因
     */
    private String refuseReason;

    /**
     * 售卖机价格
     */
    private BigDecimal vendingPrice;

    /**
     * 配送频率
     */
    private Long frequency;

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public String getGoodsInfoImgId() {
        return goodsInfoImgId;
    }

    public void setGoodsInfoImgId(String goodsInfoImgId) {
        this.goodsInfoImgId = goodsInfoImgId == null ? null : goodsInfoImgId.trim();
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo == null ? null : goodsInfoItemNo.trim();
    }

    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName == null ? null : goodsInfoName.trim();
    }

    public String getGoodsInfoSubtitle() {
        return goodsInfoSubtitle;
    }

    public void setGoodsInfoSubtitle(String goodsInfoSubtitle) {
        this.goodsInfoSubtitle = goodsInfoSubtitle == null ? null : goodsInfoSubtitle.trim();
    }

    public String getGoodsInfoAdded() {
        return goodsInfoAdded;
    }

    public void setGoodsInfoAdded(String goodsInfoAdded) {
        this.goodsInfoAdded = goodsInfoAdded == null ? null : goodsInfoAdded.trim();
    }

    public Date getGoodsInfoAddedTime() {
        return goodsInfoAddedTime;
    }

    public void setGoodsInfoAddedTime(Date goodsInfoAddedTime) {
        this.goodsInfoAddedTime = goodsInfoAddedTime;
    }

    public Date getGoodsInfoUnaddedTime() {
        return goodsInfoUnaddedTime;
    }

    public void setGoodsInfoUnaddedTime(Date goodsInfoUnaddedTime) {
        this.goodsInfoUnaddedTime = goodsInfoUnaddedTime;
    }

    public Long getGoodsInfoStock() {
        return goodsInfoStock;
    }

    public void setGoodsInfoStock(Long goodsInfoStock) {
        this.goodsInfoStock = goodsInfoStock;
    }

    public BigDecimal getGoodsInfoPreferPrice() {
        return goodsInfoPreferPrice;
    }

    public void setGoodsInfoPreferPrice(BigDecimal goodsInfoPreferPrice) {
        this.goodsInfoPreferPrice = goodsInfoPreferPrice;
    }

    public BigDecimal getGoodsInfoCostPrice() {
        return goodsInfoCostPrice;
    }

    public void setGoodsInfoCostPrice(BigDecimal goodsInfoCostPrice) {
        this.goodsInfoCostPrice = goodsInfoCostPrice;
    }

    public BigDecimal getGoodsInfoMarketPrice() {
        return goodsInfoMarketPrice;
    }

    public void setGoodsInfoMarketPrice(BigDecimal goodsInfoMarketPrice) {
        this.goodsInfoMarketPrice = goodsInfoMarketPrice;
    }

    public BigDecimal getGoodsInfoWeight() {
        return goodsInfoWeight;
    }

    public void setGoodsInfoWeight(BigDecimal goodsInfoWeight) {
        this.goodsInfoWeight = goodsInfoWeight;
    }

    public String getGoodsInfoCreateName() {
        return goodsInfoCreateName;
    }

    public void setGoodsInfoCreateName(String goodsInfoCreateName) {
        this.goodsInfoCreateName = goodsInfoCreateName == null ? null : goodsInfoCreateName.trim();
    }

    public Date getGoodsInfoCreateTime() {
        return goodsInfoCreateTime;
    }

    public void setGoodsInfoCreateTime(Date goodsInfoCreateTime) {
        this.goodsInfoCreateTime = goodsInfoCreateTime;
    }

    public String getGoodsInfoModifiedName() {
        return goodsInfoModifiedName;
    }

    public void setGoodsInfoModifiedName(String goodsInfoModifiedName) {
        this.goodsInfoModifiedName = goodsInfoModifiedName == null ? null : goodsInfoModifiedName.trim();
    }

    public Date getGoodsInfoModifiedTime() {
        return goodsInfoModifiedTime;
    }

    public void setGoodsInfoModifiedTime(Date goodsInfoModifiedTime) {
        this.goodsInfoModifiedTime = goodsInfoModifiedTime;
    }

    public String getGoodsInfoDelName() {
        return goodsInfoDelName;
    }

    public void setGoodsInfoDelName(String goodsInfoDelName) {
        this.goodsInfoDelName = goodsInfoDelName == null ? null : goodsInfoDelName.trim();
    }

    public Date getGoodsInfoDelTime() {
        return goodsInfoDelTime;
    }

    public void setGoodsInfoDelTime(Date goodsInfoDelTime) {
        this.goodsInfoDelTime = goodsInfoDelTime;
    }

    public String getGoodsInfoDelflag() {
        return goodsInfoDelflag;
    }

    public void setGoodsInfoDelflag(String goodsInfoDelflag) {
        this.goodsInfoDelflag = goodsInfoDelflag == null ? null : goodsInfoDelflag.trim();
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName == null ? null : thirdName.trim();
    }

    public String getIsThird() {
        return isThird;
    }

    public void setIsThird(String isThird) {
        this.isThird = isThird == null ? null : isThird.trim();
    }

    public String getGoodsInfoIsbn() {
        return goodsInfoIsbn;
    }

    public void setGoodsInfoIsbn(String goodsInfoIsbn) {
        this.goodsInfoIsbn = goodsInfoIsbn == null ? null : goodsInfoIsbn.trim();
    }

    public String getShowList() {
        return showList;
    }

    public void setShowList(String showList) {
        this.showList = showList == null ? null : showList.trim();
    }

    public String getShowMobile() {
        return showMobile;
    }

    public void setShowMobile(String showMobile) {
        this.showMobile = showMobile == null ? null : showMobile.trim();
    }

    public String getIsCustomerDiscount() {
        return isCustomerDiscount;
    }

    public void setIsCustomerDiscount(String isCustomerDiscount) {
        this.isCustomerDiscount = isCustomerDiscount == null ? null : isCustomerDiscount.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getIsmailbay() {
        return ismailbay;
    }

    public void setIsmailbay(String ismailbay) {
        this.ismailbay = ismailbay == null ? null : ismailbay.trim();
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason == null ? null : refuseReason.trim();
    }

    public BigDecimal getVendingPrice() {
        return vendingPrice;
    }

    public void setVendingPrice(BigDecimal vendingPrice) {
        this.vendingPrice = vendingPrice;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }
}