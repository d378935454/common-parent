package com.bean.springboot.dao.Impl;

import com.bean.springboot.dao.GoodsDao;
import com.bean.springboot.dto.goods.GoodsInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by bean on 2017/4/2.
 */
@Repository
public class GoodsDaoImpl implements GoodsDao {

    @PersistenceContext
    private EntityManager em;

    /**
     * 得到所有的商品
     *
     * @return
     */
    @Override
    public List<GoodsInfo> getAllGoodsInfo() {
        return em.createQuery("select g from GoodsInfo g where g.isDelete=0",GoodsInfo.class).getResultList();
    }
}
