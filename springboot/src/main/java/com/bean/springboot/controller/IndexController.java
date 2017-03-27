package com.bean.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by ppctest02 on 2017/3/21.
 */
@Controller
public class IndexController {

    @Value(value="${lyw.name}")
    private String a;
    @RequestMapping("/test")
    public String test(HttpServletResponse response) {
//            try {
////                vendingService.getVendingAdvByScreenAndToken(new SimpleDateFormat("yyyyMMddHHmmss").parse("20160708153908"),"1");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        return "vueDemo/vuedemo";

    }

}
