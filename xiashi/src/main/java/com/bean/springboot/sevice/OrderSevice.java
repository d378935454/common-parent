package com.bean.springboot.sevice;

import com.alibaba.fastjson.JSONObject;
import com.bean.springboot.dto.order.Order;
import com.bean.springboot.type.StateType;

import java.util.List;

/**
 * Created by bean on 2017/4/2.
 */
public interface OrderSevice {

    /**
     * 插入订单
     * @return
     */
    void insertOrder(Order order);

    /**
     * 根据类型得到订单
     * @param type
     * @return
     */
    List<Order> getOrderListByType(StateType type);

    /**
     * 根据id得到订单
     * @param id
     * @return
     */
    Order getOrderById(Long id);

    /**
     * 根据id修改订单state
     * @param id
     * @param stateType
     */
    void updateStateById(Long id, StateType stateType,StateType $state);

    /**
     * 质检订单
     * @param orderInfos
     */
    void check(JSONObject orderInfos);

    /**
     * 上传收货凭证图片
     * @param id
     */
    void upPic(Long id,String picUrl);
}
