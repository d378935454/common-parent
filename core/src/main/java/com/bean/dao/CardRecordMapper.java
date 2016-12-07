package com.bean.dao;

import com.bean.model.CardRecord;
import mybatis.basemapper.BaseMapper;

import java.util.Map;

public interface CardRecordMapper extends BaseMapper<CardRecord> {
    int insertCardRecord(Map<String, Object> map);
}