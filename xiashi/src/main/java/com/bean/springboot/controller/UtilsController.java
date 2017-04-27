package com.bean.springboot.controller;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by ppctest02 on 2017/4/27.
 */
@RequestMapping("mobile")
@Controller
public class UtilsController {

    private static final Logger log = LoggerFactory.getLogger(UtilsController.class);

    @RequestMapping(value = "/sosOutImg*")
    public void getImage(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String finlpath = antPathMatcher.extractPathWithinPattern(pattern, path);
        File file = new File(FilenameUtils.concat("", finlpath));
    }
}
