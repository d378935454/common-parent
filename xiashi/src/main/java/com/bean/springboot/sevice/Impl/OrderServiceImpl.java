package com.bean.springboot.sevice.Impl;

import com.bean.springboot.dao.OrderDao;
import com.bean.springboot.dto.order.Order;
import com.bean.springboot.sevice.OrderSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by bean on 2017/4/2.
 */
@Service

public class OrderServiceImpl implements OrderSevice {

    @Autowired
    private OrderDao goodsDao;

    /**
     * 插入订单
     *
     * @param order
     * @return
     */
    @Override
    @Transactional
    public void insertOrder(Order order) {
        goodsDao.insertOrder(order);
    }
}
