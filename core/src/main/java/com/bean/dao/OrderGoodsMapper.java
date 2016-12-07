package com.bean.dao;

import com.bean.model.OrderGoods;
import mybatis.basemapper.BaseMapper;

import java.sql.SQLException;
import java.util.Map;

public interface OrderGoodsMapper extends BaseMapper<OrderGoods> {
    Map getGoodsInfoIdByNo(Long goodsInfoId) throws SQLException;
}