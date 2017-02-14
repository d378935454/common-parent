package com.bean.controller;

import com.bean.RSTFul.RSTFulBody;
import com.bean.model.GoodsInfo;
import com.bean.service.VendingService;
import com.bean.service.WebService;
import com.bean.token.Token;
import com.bean.token.TokenUtil;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import utils.MyLogger;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.bean on 2016/3/16.
 */
@Controller
@RequestMapping("/machine")
public class TestController {
    /**
     * 日志记录
     **/
    private static final MyLogger LOGGER = new MyLogger(TestController.class);
    @Autowired
    private VendingService vendingService;

    @Autowired
    private WebService webService;

    private static final  String ROOTPATH = "d:\\";

    @Token
    @RequestMapping("/test")
    @ResponseBody
    public RSTFulBody test(HttpServletResponse response) {
//            try {
////                vendingService.getVendingAdvByScreenAndToken(new SimpleDateFormat("yyyyMMddHHmmss").parse("20160708153908"),"1");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        return new RSTFulBody().data("哈哈").body("");

    }
    @RequestMapping("/getToken")
    @ResponseBody
    public RSTFulBody getToken(HttpServletResponse response) {
        return new RSTFulBody().data("哈哈").body(TokenUtil.getJWTString("duhongda",TokenUtil.getSECRET(),"1.0.0"));

    }

    @RequestMapping("/upStockExcel")
    @ResponseBody
    public RSTFulBody upStockExcel(HttpServletResponse response,
                                   @RequestParam(value = "file") MultipartFile[] files) {
        LOGGER.info("Excel");
        for (MultipartFile file : files) {
            List<GoodsInfo> goodsInfos = new ArrayList<>();
            Workbook wb = null;
            try {
                String fileName = file.getOriginalFilename();
                File localFile = new File(ROOTPATH + fileName);
                //将上传文件写入到指定文件出、核心！
                file.transferTo(localFile);
                wb = Workbook.getWorkbook(localFile);
            } catch (IOException|BiffException e) {
                LOGGER.error(e);
//                e.printStackTrace();
            }
            Sheet rs = wb.getSheet(0);//或者rwb.getSheet(0)
            //  PrintWriter out=response.getWriter();
//            int clos = rs.getColumns();//得到所有的列
            int rows = rs.getRows();//得到所有的行
            for (int i = 1; i < rows; i++) {
                //第一个是列数，第二个是行数
                GoodsInfo bean = new GoodsInfo();
                bean.setGoodsInfoItemNo(rs.getCell(0, i).getContents());
                bean.setGoodsInfoStock(new Long(rs.getCell(1, i).getContents()));
                goodsInfos.add(bean);
            }
//                webService.updateStockByNo(goodsInfos);
        }
        return new RSTFulBody().data("哈哈").body("");

    }

}
