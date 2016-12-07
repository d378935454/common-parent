package com.bean.dao;

import com.bean.model.CustomerCard;
import com.bean.model.CustomerPointLevel;
import mybatis.basemapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhao on 2016/6/30.
 */
public interface CustomerCardMapper extends BaseMapper<CustomerCard> {
    List<CustomerCard> getCustomerCard(String rfidCode);

    int updateCustomerAmount(Map<String, Object> map);

    long getCustomerLevelById(long customerId);

    void updateCustomerLevel(Map<String, Object> map);

    /**
     * 查找默认等级
     *
     */
    CustomerPointLevel selectDefaultCustomerLevel();
    /**
     * 将卡和用户绑定
     *
     */
    int updateByCardCodeSelective(CustomerCard bean);
}
