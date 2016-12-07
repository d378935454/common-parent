package com.bean.model;

import java.math.BigDecimal;
import java.util.Date;

public class Vending {
    /**
     *
     */
    private Long vendingId;

    /**
     * 售卖机编号
     */
    private String vendingCode;

    /**
     * 售卖机名称
     */
    private String vendingName;

    /**
     * 所在village
     */
    private Long villageId;

    /**
     * 货道数
     */
    private Long trackNum;

    /**
     * 状态 0 正常 1 异常
     */
    private String vendingStatus;

    public Long getVendingId() {
        return vendingId;
    }

    public void setVendingId(Long vendingId) {
        this.vendingId = vendingId;
    }

    public String getVendingCode() {
        return vendingCode;
    }

    public void setVendingCode(String vendingCode) {
        this.vendingCode = vendingCode == null ? null : vendingCode.trim();
    }

    public String getVendingName() {
        return vendingName;
    }

    public void setVendingName(String vendingName) {
        this.vendingName = vendingName == null ? null : vendingName.trim();
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public Long getTrackNum() {
        return trackNum;
    }

    public void setTrackNum(Long trackNum) {
        this.trackNum = trackNum;
    }

    public String getVendingStatus() {
        return vendingStatus;
    }

    public void setVendingStatus(String vendingStatus) {
        this.vendingStatus = vendingStatus == null ? null : vendingStatus.trim();
    }

    private String trackCode;//货道码
    private String productId;//产品Id
    private String goodsInfoName;//头名
    private String specValueRemark;//脚名
    private BigDecimal goodsInfoPreferPrice;//售价
    private Long productNum;//库存
    private String goodsInfoImgId;//图片url
    private Date updateTime;//修改时间

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTrackCode() {
        return trackCode;
    }

    public void setTrackCode(String trackCode) {
        this.trackCode = trackCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }

    public String getSpecValueRemark() {
        return specValueRemark;
    }

    public void setSpecValueRemark(String specValueRemark) {
        this.specValueRemark = specValueRemark;
    }

    public BigDecimal getGoodsInfoPreferPrice() {
        return goodsInfoPreferPrice;
    }

    public void setGoodsInfoPreferPrice(BigDecimal goodsInfoPreferPrice) {
        this.goodsInfoPreferPrice = goodsInfoPreferPrice;
    }

    public String getGoodsInfoImgId() {
        return goodsInfoImgId;
    }

    public void setGoodsInfoImgId(String goodsInfoImgId) {
        this.goodsInfoImgId = goodsInfoImgId;
    }

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }
}