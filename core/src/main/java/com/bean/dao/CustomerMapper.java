package com.bean.dao;

import com.bean.model.Customer;
import com.bean.model.CustomerAddress;
import com.bean.model.CustomerAllInfo;
import mybatis.basemapper.BaseMapper;

import java.sql.SQLException;


/**
 * Created by zhao on 2016/6/30.
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    //省查电话是否被注册
     Long checkMobileExist(String mobile) throws SQLException;

     int insertSelective(CustomerAllInfo allInfo) throws SQLException;

     int insertAddress(CustomerAddress cusAddr) throws SQLException;
}
