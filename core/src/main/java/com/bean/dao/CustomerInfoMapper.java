package com.bean.dao;

import com.bean.model.CustomerAllInfo;
import com.bean.model.CustomerInfo;
import mybatis.basemapper.BaseMapper;

import java.sql.SQLException;

public interface CustomerInfoMapper extends BaseMapper<CustomerInfo> {
    int insert(CustomerAllInfo allInfo) throws SQLException;

    int insertSelective(CustomerAllInfo allInfo) throws SQLException;
}