package com.bean.springboot.sevice.Impl;

import com.alibaba.fastjson.JSONObject;
import com.bean.springboot.dao.OrderDao;
import com.bean.springboot.dto.order.Order;
import com.bean.springboot.sevice.OrderSevice;
import com.bean.springboot.type.StateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    /**
     * 根据类型得到订单
     *
     * @param type
     * @return
     */
    @Override
    public List<Order> getOrderListByType(StateType type) {
        return  goodsDao.getOrderListByType(type);
    }

    /**
     * 根据id得到订单
     *
     * @param id
     * @return
     */
    @Override
    public Order getOrderById(Long id) {
        return goodsDao.getOrderById(id);
    }

    /**
     * 根据id修改订单state
     *
     * @param id
     * @param stateType
     */
    @Override
    @Transactional
    public void updateStateById(Long id, StateType stateType,StateType $state) {
        goodsDao.updateStateById(id,stateType,$state);
    }

    /**
     * 质检订单
     *
     * @param orderInfos
     */
    @Override
    @Transactional
    public void check(JSONObject orderInfos) {
        goodsDao.check(orderInfos);

    }

    /**
     * 上传收货凭证图片
     *
     * @param id
     */
    @Override
    @Transactional
    public void upPic(Long id,String picUrl) {
        goodsDao.upPic(id,picUrl);
    }
}
