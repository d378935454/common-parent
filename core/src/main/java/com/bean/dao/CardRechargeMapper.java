package com.bean.dao;

import com.bean.model.CardRecharge;
import mybatis.basemapper.BaseMapper;

import java.util.Map;

public interface CardRechargeMapper extends BaseMapper<CardRecharge> {
    int insertCardRecharge(Map<String, Object> map);
}