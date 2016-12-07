package com.bean.service;

import java.math.BigDecimal;

/**
 * Created by zhao on 2016/6/30.
 */
public interface CardRecordService {
    /**
     * 会员卡充值/消费记录
     *
     * @param rfidCode   物理卡号
     * @param amount     金额
     * @param recordDate 充值/消费 日期
     * @param recordType 记录类型 0充值 1消费
     * @param orderID    充值/消费ID
     * @return
     */
    boolean cardRecord(String rfidCode, BigDecimal amount, String recordDate, String recordType, String orderID) ;
}
