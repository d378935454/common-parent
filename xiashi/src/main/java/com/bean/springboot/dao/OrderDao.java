package com.bean.springboot.dao;

import com.bean.springboot.dto.order.Order;

/**
 * Created by bean on 2017/4/2.
 */
public interface OrderDao {

    /**
     * 新建订单
     * @return
     */
    void insertOrder(Order order);
}
