package com.bean.dao;

import com.bean.model.VendingAdv;
import mybatis.basemapper.BaseMapper;

import java.sql.SQLException;
import java.util.Map;

public interface VendingAdvMapper extends BaseMapper<VendingAdv>{
    VendingAdv getByScreenAndToken(Map<String, Object> map) throws SQLException;
}