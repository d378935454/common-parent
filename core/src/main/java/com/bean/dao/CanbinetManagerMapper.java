package com.bean.dao;

import com.bean.model.CanbinetManager;
import mybatis.basemapper.BaseMapper;

import java.util.Map;

public interface CanbinetManagerMapper extends BaseMapper<CanbinetManager>{
    /**
     * 通过卡号和云柜号找管理员
     * @param map
     * @return
     */
    CanbinetManager getCabinetManagerByRfidAndCanbinetId(Map<String, Object> map);
}