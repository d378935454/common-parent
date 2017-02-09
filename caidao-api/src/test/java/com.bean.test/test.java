package com.bean.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Date;

import static jxl.biff.FormatRecord.logger;

/**
 * Created by Mr.bean on 2016/3/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-*.xml"})
public class test {
//    @Autowired
//    private VendingService vendingService;
//
//    @Autowired
//    private CustomerMapper customerMapper;
//    @Autowired
//    private CustomerCardService customerCardService;
//    @Autowired
//    private RedisService redisService;
//    @Autowired
//    private UserOperationsService userOperationsService;

    public void test() throws SQLException {
//        Customer customer=new Customer();
//        customer.setCustomerId(123L);
//        customer.setCustomerNickname("哈哈");
//        userOperationsService.add(customer);
//        userOperationsService.getUser("123");
    }
    @Test
    public void aa(){
    String  a=generatorJWT("user");
        checkToken(a);

    }
    private String generatorJWT(String userName) {

        String jwt = Jwts.builder().setSubject("龚道顺")
                .claim("roles", "龚道顺").setSubject("loginAccess").setIssuedAt(new Date()).setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                .signWith(SignatureAlgorithm.HS256, "123").compact();

        logger.debug("##############jwt=" + jwt);
        return jwt;
    }
    /**
     * 验证token的有效性
     */
    private boolean checkToken(String jwt) {
        Claims claims = Jwts.parser().setSigningKey("123").parseClaimsJws(jwt).getBody();

        if (claims.get("roles").toString().equals("龚道顺")) {
            logger.debug("登录人员的姓名：" + claims.get("roles").toString());
            return true;
        }
        return false;
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

