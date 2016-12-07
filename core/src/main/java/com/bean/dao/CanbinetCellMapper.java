package com.bean.dao;

import com.bean.model.CanbinetCell;
import mybatis.basemapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface CanbinetCellMapper extends BaseMapper<CanbinetCell>{
    List<CanbinetCell> getListWithPassWordByMap(Map<String, Object> canbinetCellMap);

    List<CanbinetCell> getCellsByMap(Map<String, Object> canbinetCellMap);

    CanbinetCell getCellsBycellCodeAndCanbinet(Map<String, Object> canbinetMap);
}