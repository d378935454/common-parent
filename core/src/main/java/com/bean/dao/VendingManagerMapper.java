package com.bean.dao;

import com.bean.model.VendingManager;
import mybatis.basemapper.BaseMapper;

import java.util.Map;

public interface VendingManagerMapper extends BaseMapper<VendingManager> {
    int validateManager(Map<String, String> map);
}