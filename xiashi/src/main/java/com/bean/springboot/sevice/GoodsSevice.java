package com.bean.springboot.sevice;

import com.bean.springboot.dto.goods.GoodsInfo;

import java.util.List;

/**
 * Created by bean on 2017/4/2.
 */
public interface GoodsSevice {

    /**
     * 得到所有商品
     * @return
     */
    List<GoodsInfo> getAllGoodsInfo();
}
