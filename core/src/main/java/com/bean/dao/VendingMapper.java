package com.bean.dao;

import com.bean.model.Vending;
import mybatis.basemapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface VendingMapper extends BaseMapper<Vending> {
    /**
     * 得到商品管理信息
     *
     * @param map
     * @return
     */
    List<Vending> getGoodsManageList(Map<String, Object> map);

    int updateCustomerAmount(String rfidCode);
}