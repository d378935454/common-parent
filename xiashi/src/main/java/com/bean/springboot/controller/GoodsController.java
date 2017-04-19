package com.bean.springboot.controller;

import com.bean.springboot.sevice.GoodsSevice;
import com.bean.springboot.utils.RSTFulBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ppctest02 on 2017/4/19.
 */
@RestController
@RequestMapping("mobile/goods")
public class GoodsController {

    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    private GoodsSevice goodsSevice;

    @RequestMapping("get-all-goods-info")
    public RSTFulBody getAllGoodsInfo(){

        return  new RSTFulBody().success(goodsSevice.getAllGoodsInfo());
    }

}
