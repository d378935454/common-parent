package com.bean.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.springboot.dto.order.Express;
import com.bean.springboot.dto.order.Order;
import com.bean.springboot.dto.order.OrderInfo;
import com.bean.springboot.sevice.OrderSevice;
import com.bean.springboot.type.StateType;
import com.bean.springboot.utils.FileUtil;
import com.bean.springboot.utils.RSTFulBody;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by ppctest02 on 2017/3/21.
 */
@RestController
@RequestMapping("mobile/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    @Value(value = "${root-path}")
    private String ROOTPATH;

    @Autowired
    private OrderSevice orderSevice;

    /**
     * 新建订单
     *
     * @param request
     * @param orderMap
     * @return
     */
    @RequestMapping("/create-order")
    public RSTFulBody test(
            HttpServletRequest request,
            @RequestBody JSONObject orderMap
    ) {
        Order order = JSON.parseObject(orderMap.getJSONObject("order").toJSONString(), Order.class);
        order.setState(StateType.CREATED);
        Express express = JSON.parseObject(orderMap.getJSONObject("express").toJSONString(), Express.class);
        order.getOrderInfos()
                .forEach(o -> {
                    o.setOrder(order);
                });
        order.setExpress(express);
        orderSevice.insertOrder(order);
        return new RSTFulBody();
    }

    /**
     * 根据类型得到订单
     *
     * @param type
     * @return
     */
    @RequestMapping("/getOrderListByType")
    public RSTFulBody getOrderListByType(
            HttpServletRequest request,
            StateType type
    ) {
        return new RSTFulBody().success(orderSevice.getOrderListByType(type));
    }

    /**
     * 根据id得到订单
     *
     * @param id
     * @return
     */
    @RequestMapping("/getOrderById")
    public RSTFulBody getOrderById(
            HttpServletRequest request,
            Long id
    ) {
        return new RSTFulBody().success(orderSevice.getOrderById(id));
    }

    /**
     * 根据id修改订单state
     *
     * @param id
     * @return
     */
    @RequestMapping("/updateStateById")
    public RSTFulBody updateStateById(
            HttpServletRequest request,
            Long id,
            StateType stateType,
            StateType $state
    ) {
        orderSevice.updateStateById(id, stateType, $state);
        return new RSTFulBody().success();
    }

    /**
     * 质检订单
     *
     * @param orderInfosMap
     * @return
     */
    @RequestMapping("/check")
    public RSTFulBody check(
            HttpServletRequest request,
            @RequestBody JSONObject orderInfosMap
    ) {
//        List<OrderInfo> orderInfos =JSON.parseArray(orderInfosMap.toString(),OrderInfo.class);
        orderSevice.check(orderInfosMap);
        return new RSTFulBody().success();
    }

    /**
     * 上传收货凭证
     *
     * @param file 图片
     * @param id  orderid
     * @param orderNo 订单编号
     * @return
     */
    @RequestMapping("/upPic")
    public RSTFulBody upPic(
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") Long id,
            @RequestParam("orderNo") String orderNo

    ) {
        try {
            String filename=FileUtil.upload(ROOTPATH , file,orderNo);
            orderSevice.upPic(id,filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RSTFulBody().success();
    }

    /**
     * 输入收货凭证信息
     *
     * @return
     */
    @RequestMapping("/Over")
    public RSTFulBody Over(
            HttpServletRequest request,
           @RequestBody JSONObject order

    ) {
       long id=  order.getLongValue("id");
       Timestamp relSendDate= order.getTimestamp("relSendDate");
        order.getJSONArray("orderInfos");
        JSONArray orderInfoList=order.getJSONArray("orderInfos");
        orderSevice.Over(id,relSendDate,orderInfoList);
        return new RSTFulBody().success();
    }

}
