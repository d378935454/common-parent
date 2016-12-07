package com.bean.service;

import com.bean.dao.CardRechargeMapper;
import com.bean.dao.CardRecordMapper;
import com.bean.dao.CustomerCardMapper;
import com.bean.model.CustomerCard;
import utils.MyLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhao on 2016/6/30.
 */
@Service
public class CardRechargeServiceImpl implements CardRechargeService {
    private MyLogger LOGGER = new MyLogger(VendingManagerService.class);
    @Autowired
    private CardRechargeMapper cardRechargeMapper;
    @Autowired
    private CardRecordMapper cardRecordMapper;
    @Autowired
    private CustomerCardMapper customerCardMapper;
    @Transactional
    public int cardRecharge(BigDecimal rechargeAmount, String rfidCode, String rechargeWay, String rechargeDate, String orderID, String rechargeType,String vendingCode) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("rechargeAmount", rechargeAmount);
        map.put("rfidCode", rfidCode);
        map.put("rechargeWay", rechargeWay);
        map.put("rechargeDate", rechargeDate);
        map.put("orderID", orderID);
        map.put("rechargeType",rechargeType);
        map.put("vendingCode",vendingCode);
        map.put("recordDate",rechargeDate);
        map.put("recodeType","0");
        map.put("amount",rechargeAmount);


        //取到会员等级，判断是否能够提升会员
        List<CustomerCard> customerCard = customerCardMapper.getCustomerCard(rfidCode);
        long CustomerId=customerCard.get(0).getCustomerId();
        long levelId=customerCardMapper.getCustomerLevelById(CustomerId);
        //充值1000以上3000以下，不是黄金会员或以上的变成黄金会员
        Map<String, Object> levelmap = new ConcurrentHashMap<>();
        levelmap.put("customerId", CustomerId);
//		        *gc.add(1,-1)表示年份减一.
//				*gc.add(2,-1)表示月份减一.
//				*gc.add(3.-1)表示周减一.
//				*gc.add(5,-1)表示天减一.
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        if (rechargeAmount.compareTo(new BigDecimal(1000)) >= 0 && rechargeAmount.compareTo(new BigDecimal(3000)) < 0 && levelId < 3) {
            levelmap.put("levelId", 3);
            gc.add(2, 6);
            levelmap.put("endTime", gc.getTime());
            customerCardMapper.updateCustomerLevel(levelmap);
        } else if (rechargeAmount.compareTo(new BigDecimal(3000)) >= 0 && rechargeAmount.compareTo(new BigDecimal(5000)) < 0 && levelId < 4) {
            levelmap.put("levelId", 4);
            gc.add(2, 12);
            levelmap.put("endTime", gc.getTime());
            customerCardMapper.updateCustomerLevel(levelmap);
        } else if (rechargeAmount.compareTo(new BigDecimal(5000)) >= 0 && levelId < 5) {
            levelmap.put("levelId", 5);
            gc.add(2, 12);
            levelmap.put("endTime", gc.getTime());
            customerCardMapper.updateCustomerLevel(levelmap);
        }
        cardRechargeMapper.insertCardRecharge(map);
        cardRecordMapper.insertCardRecord(map);
        LOGGER.info("会员卡充值 cardRecharge" + "充值金额：" + rechargeAmount + ",物理卡号：" + rfidCode + ",充值方式：" + rechargeWay + ",充值日期：" + rechargeDate + ",订单ID：" + orderID);
        return customerCardMapper.updateCustomerAmount(map);
    }
}
