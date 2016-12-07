package com.bean.dao;

import com.bean.model.GoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(@Param("goodsInfoId") Long goodsInfoId, @Param("goodsInfoImgId") String goodsInfoImgId);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(@Param("goodsInfoId") Long goodsInfoId, @Param("goodsInfoImgId") String goodsInfoImgId);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    /**
     * 通过货品编码修改库存
     * @param goodsInfos
     * @return
     */
    int updateStockByNo(List<GoodsInfo> goodsInfos) throws SQLException;
}