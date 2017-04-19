package com.bean.springboot.sevice;

import com.bean.springboot.dto.order.Order;

/**
 * Created by bean on 2017/4/2.
 */
public interface OrderSevice {

    /**
     * 插入订单
     * @return
     */
    void insertOrder(Order order);
}
