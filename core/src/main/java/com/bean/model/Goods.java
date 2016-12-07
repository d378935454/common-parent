package com.bean.model;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    /**
     * 商品ID 
     */
    private Long goodsId;

    /**
     * 商品分类ID
     */
    private Long catId;

    /**
     * 类型ID
     */
    private Long typeId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品副标题 
     */
    private String goodsSubtitle;

    /**
     * 商品编号
     */
    private String goodsNo;

    /**
     * 商品关键词
     */
    private String goodsKeywords;

    /**
     * 品牌
     */
    private Long brandId;

    /**
     * 商品简介
     */
    private String goodsBrief;

    /**
     * 立即上架
     */
    private String goodsAdded;

    /**
     * 上架时间
     */
    private Date goodsUptime;

    /**
     * 销售价
     */
    private BigDecimal goodsPrice;

    /**
     * 商品推荐
     */
    private String goodsRecom;

    /**
     * 商品图片
     */
    private String goodsImg;

    /**
     * 商品评分 
     */
    private BigDecimal goodsScore;

    /**
     * 计价单位 
     */
    private String goodsDeno;

    /**
     * SEO title
     */
    private String goodsSeoTitle;

    /**
     * SEO keyword
     */
    private String goodsSeoKeyword;

    /**
     * SEO Desc
     */
    private String goodsSeoDesc;

    /**
     * 是否促销 
     */
    private String goodsProm;

    /**
     * 无库存也可销售
     */
    private String goodsInfoInstocksell;

    /**
     * 是否允许使用优惠劵
     */
    private String goodsUsecoupon;

    /**
     * 创建人名称
     */
    private String goodsCreateName;

    /**
     * 创建时间
     */
    private Date goodsCreateTime;

    /**
     * 修改人名称
     */
    private String goodsModifiedName;

    /**
     * 修改时间
     */
    private Date goodsModifiedTime;

    /**
     * 删除人名称
     */
    private String goodsDelName;

    /**
     * 删除时间
     */
    private Date goodsDelTime;

    /**
     * 是否删除
     */
    private String goodsDelflag;

    /**
     * 商品所属的商家id
     */
    private Long goodsBelo;

    /**
     * 商家的名称
     */
    private String goodsBeloName;

    /**
     * 1:第三方商家商品  2:B2B商品 0:后台Boss商品 4:第四方服务点
     */
    private String isThird;

    /**
     * 
     */
    private Long thirdCateId;

    /**
     * 第三方审核是否开启 0不开启 1开启
     */
    private String isThirdAuditUsed;

    /**
     * 审核状态(0未审核、审核通过  1审核中 2审核不通过 3需审核)
     */
    private String auditStatus;

    /**
     * 审核拒绝原因
     */
    private String refuseReason;

    /**
     * 详细介绍 
     */
    private String goodsDetailDesc;

    /**
     * 手机版详细介绍
     */
    private String mobileDesc;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsSubtitle() {
        return goodsSubtitle;
    }

    public void setGoodsSubtitle(String goodsSubtitle) {
        this.goodsSubtitle = goodsSubtitle == null ? null : goodsSubtitle.trim();
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    public String getGoodsKeywords() {
        return goodsKeywords;
    }

    public void setGoodsKeywords(String goodsKeywords) {
        this.goodsKeywords = goodsKeywords == null ? null : goodsKeywords.trim();
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief == null ? null : goodsBrief.trim();
    }

    public String getGoodsAdded() {
        return goodsAdded;
    }

    public void setGoodsAdded(String goodsAdded) {
        this.goodsAdded = goodsAdded == null ? null : goodsAdded.trim();
    }

    public Date getGoodsUptime() {
        return goodsUptime;
    }

    public void setGoodsUptime(Date goodsUptime) {
        this.goodsUptime = goodsUptime;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsRecom() {
        return goodsRecom;
    }

    public void setGoodsRecom(String goodsRecom) {
        this.goodsRecom = goodsRecom == null ? null : goodsRecom.trim();
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg == null ? null : goodsImg.trim();
    }

    public BigDecimal getGoodsScore() {
        return goodsScore;
    }

    public void setGoodsScore(BigDecimal goodsScore) {
        this.goodsScore = goodsScore;
    }

    public String getGoodsDeno() {
        return goodsDeno;
    }

    public void setGoodsDeno(String goodsDeno) {
        this.goodsDeno = goodsDeno == null ? null : goodsDeno.trim();
    }

    public String getGoodsSeoTitle() {
        return goodsSeoTitle;
    }

    public void setGoodsSeoTitle(String goodsSeoTitle) {
        this.goodsSeoTitle = goodsSeoTitle == null ? null : goodsSeoTitle.trim();
    }

    public String getGoodsSeoKeyword() {
        return goodsSeoKeyword;
    }

    public void setGoodsSeoKeyword(String goodsSeoKeyword) {
        this.goodsSeoKeyword = goodsSeoKeyword == null ? null : goodsSeoKeyword.trim();
    }

    public String getGoodsSeoDesc() {
        return goodsSeoDesc;
    }

    public void setGoodsSeoDesc(String goodsSeoDesc) {
        this.goodsSeoDesc = goodsSeoDesc == null ? null : goodsSeoDesc.trim();
    }

    public String getGoodsProm() {
        return goodsProm;
    }

    public void setGoodsProm(String goodsProm) {
        this.goodsProm = goodsProm == null ? null : goodsProm.trim();
    }

    public String getGoodsInfoInstocksell() {
        return goodsInfoInstocksell;
    }

    public void setGoodsInfoInstocksell(String goodsInfoInstocksell) {
        this.goodsInfoInstocksell = goodsInfoInstocksell == null ? null : goodsInfoInstocksell.trim();
    }

    public String getGoodsUsecoupon() {
        return goodsUsecoupon;
    }

    public void setGoodsUsecoupon(String goodsUsecoupon) {
        this.goodsUsecoupon = goodsUsecoupon == null ? null : goodsUsecoupon.trim();
    }

    public String getGoodsCreateName() {
        return goodsCreateName;
    }

    public void setGoodsCreateName(String goodsCreateName) {
        this.goodsCreateName = goodsCreateName == null ? null : goodsCreateName.trim();
    }

    public Date getGoodsCreateTime() {
        return goodsCreateTime;
    }

    public void setGoodsCreateTime(Date goodsCreateTime) {
        this.goodsCreateTime = goodsCreateTime;
    }

    public String getGoodsModifiedName() {
        return goodsModifiedName;
    }

    public void setGoodsModifiedName(String goodsModifiedName) {
        this.goodsModifiedName = goodsModifiedName == null ? null : goodsModifiedName.trim();
    }

    public Date getGoodsModifiedTime() {
        return goodsModifiedTime;
    }

    public void setGoodsModifiedTime(Date goodsModifiedTime) {
        this.goodsModifiedTime = goodsModifiedTime;
    }

    public String getGoodsDelName() {
        return goodsDelName;
    }

    public void setGoodsDelName(String goodsDelName) {
        this.goodsDelName = goodsDelName == null ? null : goodsDelName.trim();
    }

    public Date getGoodsDelTime() {
        return goodsDelTime;
    }

    public void setGoodsDelTime(Date goodsDelTime) {
        this.goodsDelTime = goodsDelTime;
    }

    public String getGoodsDelflag() {
        return goodsDelflag;
    }

    public void setGoodsDelflag(String goodsDelflag) {
        this.goodsDelflag = goodsDelflag == null ? null : goodsDelflag.trim();
    }

    public Long getGoodsBelo() {
        return goodsBelo;
    }

    public void setGoodsBelo(Long goodsBelo) {
        this.goodsBelo = goodsBelo;
    }

    public String getGoodsBeloName() {
        return goodsBeloName;
    }

    public void setGoodsBeloName(String goodsBeloName) {
        this.goodsBeloName = goodsBeloName == null ? null : goodsBeloName.trim();
    }

    public String getIsThird() {
        return isThird;
    }

    public void setIsThird(String isThird) {
        this.isThird = isThird == null ? null : isThird.trim();
    }

    public Long getThirdCateId() {
        return thirdCateId;
    }

    public void setThirdCateId(Long thirdCateId) {
        this.thirdCateId = thirdCateId;
    }

    public String getIsThirdAuditUsed() {
        return isThirdAuditUsed;
    }

    public void setIsThirdAuditUsed(String isThirdAuditUsed) {
        this.isThirdAuditUsed = isThirdAuditUsed == null ? null : isThirdAuditUsed.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason == null ? null : refuseReason.trim();
    }

    public String getGoodsDetailDesc() {
        return goodsDetailDesc;
    }

    public void setGoodsDetailDesc(String goodsDetailDesc) {
        this.goodsDetailDesc = goodsDetailDesc == null ? null : goodsDetailDesc.trim();
    }

    public String getMobileDesc() {
        return mobileDesc;
    }

    public void setMobileDesc(String mobileDesc) {
        this.mobileDesc = mobileDesc == null ? null : mobileDesc.trim();
    }
}