package com.bean.test;

import com.bean.dao.CustomerCardMapper;
import com.bean.dao.CustomerMapper;
import com.bean.model.Customer;
import com.bean.model.CustomerCard;
import com.bean.model.Goods;
import com.bean.redis.RedisService;
import com.bean.redis.UserOperationsService;
import com.bean.service.CustomerCardService;
import com.bean.service.VendingService;
import utils.Utils;
import utils.Validation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Mr.bean on 2016/3/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-*.xml"})
public class test {
    @Autowired
    private VendingService vendingService;

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerCardService customerCardService;
//    @Autowired
//    private RedisService redisService;
//    @Autowired
//    private UserOperationsService userOperationsService;
    @Test
    public void test() throws SQLException {
//        Customer customer=new Customer();
//        customer.setCustomerId(123L);
//        customer.setCustomerNickname("哈哈");
//        userOperationsService.add(customer);
//        userOperationsService.getUser("123");
    }
    private void aa(String a,List<String> list ,Goods goods){
        for (String s:list){
           if(s.equals(a)){
               a="gai";
               goods.setAuditStatus("1");
           }
        }

    }
}

/*try {

            String content = "120605181003;http://www.cnblogs.com/jtmjx";
            String path = "D:/";

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
            File file1 = new File(path,"餐巾纸.jpg");
            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);

        } catch (Exception e) {
            e.printStackTrace();
        }*/

