package com.bean.service;

import com.bean.dao.CardRecordMapper;
import com.bean.dao.CustomerCardMapper;
import com.bean.exception.MyException;
import utils.MyLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhao on 2016/6/30.
 */
@Service
public class CardRecordServiceImpl implements CardRecordService {
    private MyLogger LOGGER = new MyLogger(CardRecordServiceImpl.class);
    @Autowired
    private CardRecordMapper cardRecordMapper;
    @Autowired
    private CustomerCardMapper customerCardMapper;

    public boolean cardRecord(String rfidCode, BigDecimal amount, String recordDate, String recordType, String orderID) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("amount", amount);
        map.put("rfidCode", rfidCode);
        map.put("recodeType", recordType);
        map.put("recordDate", recordDate);
        map.put("orderID", orderID);
        if (!(cardRecordMapper.insertCardRecord(map) > 0 && customerCardMapper.updateCustomerAmount(map) > 0)) {
            try {
                throw new MyException("用户卡号不存在");
            } catch (MyException e) {
            } finally {
                LOGGER.error("用户卡号不存在:"+rfidCode);
                return false;
            }
        }
        return true;
    }
}
