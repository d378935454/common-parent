package com.bean.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bean.springboot.dto.order.Express;
import com.bean.springboot.dto.order.Order;
import com.bean.springboot.sevice.OrderSevice;
import com.bean.springboot.utils.RSTFulBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ppctest02 on 2017/3/21.
 */
@RestController
@RequestMapping("mobile/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Value(value = "${lyw.name}")
    private String a;

    @Autowired
    private OrderSevice orderSevice;

    @RequestMapping("/create-order")
    public RSTFulBody test(
            HttpServletRequest request,
            @RequestBody JSONObject orderMap
    ) {
        Order order = JSON.parseObject(orderMap.getJSONObject("order").toJSONString(), Order.class);
        Express express = JSON.parseObject(orderMap.getJSONObject("express").toJSONString(), Express.class);
        order.getOrderInfos().stream()
                .forEach(o -> o.setExpress(express));
        orderSevice.insertOrder(order);
        return new RSTFulBody();

    }

}
