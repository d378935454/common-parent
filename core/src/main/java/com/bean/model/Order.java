package com.bean.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    /**
     * 订单Id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 订单总金额
     */
    private BigDecimal orderPrice;

    /**
     * 订单优惠金额
     */
    private BigDecimal orderPrePrice;

    /**
     * 订单原始总金额
     */
    private BigDecimal orderOldPrice;

    /**
     * 订单状态(0未付款 1已付款未发货 2已发货 3已经收货 4作废 7:申请退货 8：同意退货 9:拒绝退货 10:待商家收货 11:订单结束 12:申请退款13： 拒绝退款)
     */
    private String orderStatus;

    /**
     * 会员ID
     */
    private Long customerId;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date sendExpressTime;

    /**
     * 收货时间
     */
    private Date getGoodsTime;

    /**
     * 收货地址ID
     */
    private Long shoppingAddrId;

    /**
     * 收货省
     */
    private String shippingProvince;

    /**
     * 收货市
     */
    private String shippingCity;

    /**
     * 收货区县
     */
    private String shippingCounty;

    /**
     * 收货详细地址
     */
    private String shippingAddress;

    /**
     * 收货人
     */
    private String shippingPerson;

    /**
     * 收货电话
     */
    private String shippingPhone;

    /**
     * 收货手机
     */
    private String shippingMobile;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 发票内容
     */
    private String invoiceContent;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 发票类型
     */
    private String invoiceType;

    /**
     * 客户留言
     */
    private String customerRemark;

    /**
     * 支付方式
     */
    private Long payId;

    /**
     * 积分
     */
    private Long orderIntegral;

    /**
     * 使用的优惠券
     */
    private String couponNo;

    /**
     * 是否评价 0未评价 1已经评价
     */
    private String evaluateFlag;

    /**
     * 运费
     */
    private BigDecimal expressPrice;

    /**
     * 邮编
     */
    private String shippingPostcode;

    /**
     * 取消时间
     */
    private Date orderCancelTime;

    /**
     * 原因
     */
    private String orderCancelRemark;

    /**
     * 退单金额
     */
    private BigDecimal backPrice;

    /**
     * 商家ID
     */
    private Long businessId;

    /**
     * 第三方 0  经销商1
     */
    private String dealerType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 主单编号
     */
    private String orderOldCode;

    /**
     * 是否是新增订单 0 新增订单 1 已查看订单
     */
    private String orderNewStatus;

    /**
     *
     */
    private String orderCargoStatus;

    /**
     *
     */
    private String orderMType;

    /**
     *
     */
    private String orderExpressType;

    /**
     * 订单促销优惠
     */
    private BigDecimal orderPrePriceOrder;

    /**
     * 1在线支付 0货到付款
     */
    private String orderLinePay;

    /**
     * 区id
     */
    private Integer shippingCountyId;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 仓库名
     */
    private String wareName;

    /**
     * 订单销售模式 0 平台 1 售卖机
     */
    private String orderMode;

    /**
     * 售卖机ID
     */
    private Long vendingId;

    /**
     * 售卖机货道码
     */
    private String vendingAisleNum;

    /**
     * 配送到仓储状态 0 未确认 1 确认 2 配送到仓储
     */
    private String orderDistributionStatus;

    /**
     *
     */
    private String bossFlag;

    /**
     * 是否成功
     */
    private Boolean isSuccess;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderPrePrice() {
        return orderPrePrice;
    }

    public void setOrderPrePrice(BigDecimal orderPrePrice) {
        this.orderPrePrice = orderPrePrice;
    }

    public BigDecimal getOrderOldPrice() {
        return orderOldPrice;
    }

    public void setOrderOldPrice(BigDecimal orderOldPrice) {
        this.orderOldPrice = orderOldPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getSendExpressTime() {
        return sendExpressTime;
    }

    public void setSendExpressTime(Date sendExpressTime) {
        this.sendExpressTime = sendExpressTime;
    }

    public Date getGetGoodsTime() {
        return getGoodsTime;
    }

    public void setGetGoodsTime(Date getGoodsTime) {
        this.getGoodsTime = getGoodsTime;
    }

    public Long getShoppingAddrId() {
        return shoppingAddrId;
    }

    public void setShoppingAddrId(Long shoppingAddrId) {
        this.shoppingAddrId = shoppingAddrId;
    }

    public String getShippingProvince() {
        return shippingProvince;
    }

    public void setShippingProvince(String shippingProvince) {
        this.shippingProvince = shippingProvince == null ? null : shippingProvince.trim();
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity == null ? null : shippingCity.trim();
    }

    public String getShippingCounty() {
        return shippingCounty;
    }

    public void setShippingCounty(String shippingCounty) {
        this.shippingCounty = shippingCounty == null ? null : shippingCounty.trim();
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress == null ? null : shippingAddress.trim();
    }

    public String getShippingPerson() {
        return shippingPerson;
    }

    public void setShippingPerson(String shippingPerson) {
        this.shippingPerson = shippingPerson == null ? null : shippingPerson.trim();
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone == null ? null : shippingPhone.trim();
    }

    public String getShippingMobile() {
        return shippingMobile;
    }

    public void setShippingMobile(String shippingMobile) {
        this.shippingMobile = shippingMobile == null ? null : shippingMobile.trim();
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent == null ? null : invoiceContent.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType == null ? null : invoiceType.trim();
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark == null ? null : customerRemark.trim();
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getOrderIntegral() {
        return orderIntegral;
    }

    public void setOrderIntegral(Long orderIntegral) {
        this.orderIntegral = orderIntegral;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo == null ? null : couponNo.trim();
    }

    public String getEvaluateFlag() {
        return evaluateFlag;
    }

    public void setEvaluateFlag(String evaluateFlag) {
        this.evaluateFlag = evaluateFlag == null ? null : evaluateFlag.trim();
    }

    public BigDecimal getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(BigDecimal expressPrice) {
        this.expressPrice = expressPrice;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode == null ? null : shippingPostcode.trim();
    }

    public Date getOrderCancelTime() {
        return orderCancelTime;
    }

    public void setOrderCancelTime(Date orderCancelTime) {
        this.orderCancelTime = orderCancelTime;
    }

    public String getOrderCancelRemark() {
        return orderCancelRemark;
    }

    public void setOrderCancelRemark(String orderCancelRemark) {
        this.orderCancelRemark = orderCancelRemark == null ? null : orderCancelRemark.trim();
    }

    public BigDecimal getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(BigDecimal backPrice) {
        this.backPrice = backPrice;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getDealerType() {
        return dealerType;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType == null ? null : dealerType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderOldCode() {
        return orderOldCode;
    }

    public void setOrderOldCode(String orderOldCode) {
        this.orderOldCode = orderOldCode == null ? null : orderOldCode.trim();
    }

    public String getOrderNewStatus() {
        return orderNewStatus;
    }

    public void setOrderNewStatus(String orderNewStatus) {
        this.orderNewStatus = orderNewStatus == null ? null : orderNewStatus.trim();
    }

    public String getOrderCargoStatus() {
        return orderCargoStatus;
    }

    public void setOrderCargoStatus(String orderCargoStatus) {
        this.orderCargoStatus = orderCargoStatus == null ? null : orderCargoStatus.trim();
    }

    public String getOrderMType() {
        return orderMType;
    }

    public void setOrderMType(String orderMType) {
        this.orderMType = orderMType == null ? null : orderMType.trim();
    }

    public String getOrderExpressType() {
        return orderExpressType;
    }

    public void setOrderExpressType(String orderExpressType) {
        this.orderExpressType = orderExpressType == null ? null : orderExpressType.trim();
    }

    public BigDecimal getOrderPrePriceOrder() {
        return orderPrePriceOrder;
    }

    public void setOrderPrePriceOrder(BigDecimal orderPrePriceOrder) {
        this.orderPrePriceOrder = orderPrePriceOrder;
    }

    public String getOrderLinePay() {
        return orderLinePay;
    }

    public void setOrderLinePay(String orderLinePay) {
        this.orderLinePay = orderLinePay == null ? null : orderLinePay.trim();
    }

    public Integer getShippingCountyId() {
        return shippingCountyId;
    }

    public void setShippingCountyId(Integer shippingCountyId) {
        this.shippingCountyId = shippingCountyId;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName == null ? null : wareName.trim();
    }

    public String getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(String orderMode) {
        this.orderMode = orderMode == null ? null : orderMode.trim();
    }

    public Long getVendingId() {
        return vendingId;
    }

    public void setVendingId(Long vendingId) {
        this.vendingId = vendingId;
    }

    public String getVendingAisleNum() {
        return vendingAisleNum;
    }

    public void setVendingAisleNum(String vendingAisleNum) {
        this.vendingAisleNum = vendingAisleNum == null ? null : vendingAisleNum.trim();
    }

    public String getOrderDistributionStatus() {
        return orderDistributionStatus;
    }

    public void setOrderDistributionStatus(String orderDistributionStatus) {
        this.orderDistributionStatus = orderDistributionStatus == null ? null : orderDistributionStatus.trim();
    }

    public String getBossFlag() {
        return bossFlag;
    }

    public void setBossFlag(String bossFlag) {
        this.bossFlag = bossFlag == null ? null : bossFlag.trim();
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
    private String rfidCode;//芯片号

    public String getRfidCode() {
        return rfidCode;
    }

    public void setRfidCode(String rfidCode) {
        this.rfidCode = rfidCode;
    }
}