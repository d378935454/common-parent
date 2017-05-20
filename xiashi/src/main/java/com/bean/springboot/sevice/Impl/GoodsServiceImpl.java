package com.bean.springboot.sevice.Impl;

import com.bean.springboot.dao.GoodsDao;
import com.bean.springboot.dto.goods.GoodsInfo;
import com.bean.springboot.sevice.GoodsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bean on 2017/4/2.
 */
@Service
public class GoodsServiceImpl implements GoodsSevice {

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 得到所有商品
     *
     * @return
     */
    @Override
    public List<GoodsInfo> getAllGoodsInfo() {
        return goodsDao.getAllGoodsInfo();
    }
}
