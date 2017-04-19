package com.bean.springboot.dao.Impl;

import com.bean.springboot.dao.OrderDao;
import com.bean.springboot.dto.order.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by bean on 2017/4/2.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager em;


    /**
     * 新建订单
     *
     * @param order
     * @return
     */
    @Override
    public void insertOrder(Order order) {
        em.persist(order);
    }
}
