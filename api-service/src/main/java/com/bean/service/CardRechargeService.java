package com.bean.service;

import java.math.BigDecimal;

/**
 * Created by zhao on 2016/6/30.
 */
public interface CardRechargeService {

    /**
     * 会员卡充值
     *
     * @param rechargeAmount 充值金额
     * @param rfidCode       物理卡号
     * @param rechargeWay    充值方式
     * @param rechargeDate   充值日期
     * @param orderID        订单号
     * @param rechargeType   充值类型 1、现金 2、微信 3、支付宝
     * @param vendingCode    机器编码
     * @return
     */
    int cardRecharge(BigDecimal rechargeAmount, String rfidCode, String rechargeWay, String rechargeDate, String orderID, String rechargeType, String vendingCode);
}
