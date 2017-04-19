package com.bean.springboot.dao;

import com.bean.springboot.dto.goods.GoodsInfo;

import java.util.List;

/**
 * Created by bean on 2017/4/2.
 */
public interface GoodsDao {

    /**
     * 得到所有的商品
     * @return
     */
    List<GoodsInfo> getAllGoodsInfo();
}
