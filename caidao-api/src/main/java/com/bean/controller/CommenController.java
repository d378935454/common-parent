package com.bean.controller;


import com.bean.RSTFul.RSTFulBody;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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

    @RequestMapping(value = "/exportExcel")
    public void exportExcel(HttpServletResponse response){
        // 生成提示信息，
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
            // 进行转码，使其支持中文文件名
            codedFileName = java.net.URLEncoder.encode("中文", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            //产生工作表对象
            HSSFSheet sheet = workbook.createSheet();
            for (int i = 0; i <= 30000; i++)
            {
                HSSFRow row = sheet.createRow((int)i);//创建一行
                HSSFCell cell = row.createCell((int)0);//创建一列
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("测试成功" + i);
            }
            fOut = response.getOutputStream();
            workbook.write(fOut);
        }
        catch (UnsupportedEncodingException e1)
        {}
        catch (Exception e)
        {}
        finally
        {
            try
            {
                fOut.flush();
                fOut.close();
            }
            catch (IOException e)
            {}
        }
        System.out.println("文件生成...");
    }


}
