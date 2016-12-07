package com.bean.service;

import com.bean.model.Customer;
import com.bean.model.CustomerCard;
import com.bean.model.CustomerInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhao on 2016/6/30.
 */
public interface CustomerCardService {
    /**
     * 更新会员余额
     *
     * @param rfidCode 会员卡物理卡号
     * @return
     */
    int updateCustomerAmount(String rfidCode, BigDecimal amount, String recordType);

    /**
     * 获取会员卡信息
     *
     * @param rfidCode 会员卡物理卡号
     * @return
     */
    List<CustomerCard> getCustomerCard(String rfidCode);

    /**
     * 判断物理卡号，电话是否已使用
     * @param rfidCode 物理卡号
     * @param mobile 电话
     * @param vmid 售卖柜code
     * @return
     */
    String getCheckCustomerCard(String rfidCode, String mobile,String vmid);

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    String sendMsgCodeToMobile(String mobile);
//    /**
//     * 售货机开卡
//     * @param customer
//     * @param customerInfo
//     * @param customerCard
//     */
//    void insertCustomerCard(Customer customer, CustomerInfo customerInfo, CustomerCard customerCard);
}
