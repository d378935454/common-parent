package com.bean.dao;

import com.bean.model.VendingUp;
import mybatis.basemapper.BaseMapper;

import java.sql.SQLException;
import java.util.Map;

public interface VendingUpMapper   extends BaseMapper<VendingUp> {
    /**
     * 批量添加送货记录
     * @param map
     * @return
     * @throws SQLException
     */
    int insertList(Map<String, Object> map) throws SQLException;

    /**
     * 批量修改售货柜的库存信息
     * @param map
     * @return
     */
    int updateAddStockList(Map<String, Object> map) throws SQLException;
}