package com.bean.controller;


import com.bean.RSTFul.RSTFulBody;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by bean on 2016/5/18.
 */
@Controller
public class CommenController {
    @RequestMapping(value = "/sosOutImg*")
    public void getImage(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String finlpath = antPathMatcher.extractPathWithinPattern(pattern, path);
        File file = new File(FilenameUtils.concat("d:", finlpath));
    }

   /* *
     * 将request的String转成date
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class,new CustomDateEditor(simpleDateFormat,true));
    }
    @RequestMapping(value = "/seckill")
    @ResponseBody
    public RSTFulBody seckill(){
        return  new RSTFulBody().success("").data("hehe     ");
    }
}
