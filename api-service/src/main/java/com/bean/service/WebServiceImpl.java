package com.bean.service;

import com.bean.dao.GoodsInfoMapper;
import com.bean.model.GoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by bean on 2016/7/22.
 */
@Service
public class WebServiceImpl implements WebService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Override
    public int updateStockByNo(List<GoodsInfo> goodsInfos) {
        try {
            return goodsInfoMapper.updateStockByNo(goodsInfos);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
