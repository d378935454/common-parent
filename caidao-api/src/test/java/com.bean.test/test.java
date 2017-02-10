package com.bean.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void aa(){
    String  a=createJWT("1","","",60L);
        checkToken(a);

    }
    //Sample method to construct a JWT

    private String createJWT(String id, String issuer, String subject, long ttlMillis) {

//The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

//We will sign our JWT with our ApiKey secret
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
//        Key signingKey = MacProvider.generateKey();

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm,  "secretkey");

//if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

//Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
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

