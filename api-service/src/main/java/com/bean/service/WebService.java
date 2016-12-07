package com.bean.service;

import com.bean.model.GoodsInfo;

import java.util.List;

/**
 * Created by bean on 2016/7/22.
 */
public interface WebService {
    int updateStockByNo(List<GoodsInfo> goodsInfos) ;
}
