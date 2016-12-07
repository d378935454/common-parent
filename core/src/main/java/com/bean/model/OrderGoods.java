package com.bean.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderGoods {
    /**
     * 订单货品ID
     */
    private Long orderGoodsId;

    /**
     * 订单Id
     */
    private Long orderId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 评论编号
     */
    private Long commentId;

    /**
     * 货品ID
     */
    private Long goodsInfoId;

    /**
     * 货品个数
     */
    private Long goodsInfoNum;

    /**
     * 货品原始价格
     */
    private BigDecimal goodsInfoOldPrice;

    /**
     * 货品交易价格
     */
    private BigDecimal goodsInfoPrice;

    /**
     * 货品优惠金额
     */
    private BigDecimal goodsCouponPrice;

    /**
     * 小计金额
     */
    private BigDecimal goodsInfoSumPrice;

    /**
     * 是否有赠品
     */
    private String haveGiftStatus;

    /**
     * 是否有优惠券
     */
    private String haveCouponStatus;

    /**
     * 商品促销ID
     */
    private Long goodsMarketingId;

    /**
     * 购买时间
     */
    private Date buyTime;

    /**
     * 是否删除
     */
    private String delFlag;

    /**
     * 是否评价  1评价 0未评价
     */
    private String evaluateFlag;

    /**
     * 是否退单
     */
    private String backFlag;

    /**
     * 退单号
     */
    private String backOrderCode;

    /**
     * 换单号
     */
    private String barterOrderCode;

    /**
     * 晒单id
     */
    private Long shareId;

    /**
     * 是否已晒单。0未晒，1已晒
     */
    private String shareFlag;

    /**
     * 活动促销id
     */
    private Long goodsActiveMarketingid;

    /**
     * 
     */
    private Long distinctId;

    public Long getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Long getGoodsInfoNum() {
        return goodsInfoNum;
    }

    public void setGoodsInfoNum(Long goodsInfoNum) {
        this.goodsInfoNum = goodsInfoNum;
    }

    public BigDecimal getGoodsInfoOldPrice() {
        return goodsInfoOldPrice;
    }

    public void setGoodsInfoOldPrice(BigDecimal goodsInfoOldPrice) {
        this.goodsInfoOldPrice = goodsInfoOldPrice;
    }

    public BigDecimal getGoodsInfoPrice() {
        return goodsInfoPrice;
    }

    public void setGoodsInfoPrice(BigDecimal goodsInfoPrice) {
        this.goodsInfoPrice = goodsInfoPrice;
    }

    public BigDecimal getGoodsCouponPrice() {
        return goodsCouponPrice;
    }

    public void setGoodsCouponPrice(BigDecimal goodsCouponPrice) {
        this.goodsCouponPrice = goodsCouponPrice;
    }

    public BigDecimal getGoodsInfoSumPrice() {
        return goodsInfoSumPrice;
    }

    public void setGoodsInfoSumPrice(BigDecimal goodsInfoSumPrice) {
        this.goodsInfoSumPrice = goodsInfoSumPrice;
    }

    public String getHaveGiftStatus() {
        return haveGiftStatus;
    }

    public void setHaveGiftStatus(String haveGiftStatus) {
        this.haveGiftStatus = haveGiftStatus == null ? null : haveGiftStatus.trim();
    }

    public String getHaveCouponStatus() {
        return haveCouponStatus;
    }

    public void setHaveCouponStatus(String haveCouponStatus) {
        this.haveCouponStatus = haveCouponStatus == null ? null : haveCouponStatus.trim();
    }

    public Long getGoodsMarketingId() {
        return goodsMarketingId;
    }

    public void setGoodsMarketingId(Long goodsMarketingId) {
        this.goodsMarketingId = goodsMarketingId;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getEvaluateFlag() {
        return evaluateFlag;
    }

    public void setEvaluateFlag(String evaluateFlag) {
        this.evaluateFlag = evaluateFlag == null ? null : evaluateFlag.trim();
    }

    public String getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag == null ? null : backFlag.trim();
    }

    public String getBackOrderCode() {
        return backOrderCode;
    }

    public void setBackOrderCode(String backOrderCode) {
        this.backOrderCode = backOrderCode == null ? null : backOrderCode.trim();
    }

    public String getBarterOrderCode() {
        return barterOrderCode;
    }

    public void setBarterOrderCode(String barterOrderCode) {
        this.barterOrderCode = barterOrderCode == null ? null : barterOrderCode.trim();
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public String getShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(String shareFlag) {
        this.shareFlag = shareFlag == null ? null : shareFlag.trim();
    }

    public Long getGoodsActiveMarketingid() {
        return goodsActiveMarketingid;
    }

    public void setGoodsActiveMarketingid(Long goodsActiveMarketingid) {
        this.goodsActiveMarketingid = goodsActiveMarketingid;
    }

    public Long getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(Long distinctId) {
        this.distinctId = distinctId;
    }
}