package com.bean.token;

import com.bean.aspect.TokenAspect;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import utils.MyLogger;

import java.util.Date;

/**
 * Created by bean on 2017/2/13.
 */
public class TokenUtil {
    private static final  MyLogger log = new MyLogger(TokenAspect.class);
    private static final Long OUTTIME = 60 * 60 * 1000L;
    private TokenUtil() {

    }

    public static String getJWTString(String tel, String key, String commpanyId){
        if (tel == null) {
            throw new NullPointerException("null username is illegal");
        }
        if (key == null) {
            throw new NullPointerException("null key is illegal");
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                .setIssuer("Jersey-Security-Basic")
                .setSubject(tel)
                .setAudience("user")
                .setExpiration(new Date(System.currentTimeMillis()+OUTTIME))
                .claim("role","du")
                .setIssuedAt(new Date())
                .setId(commpanyId)
                .signWith(signatureAlgorithm,key)
                .compact();
    }
    public static boolean isValid(String token, String key) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
            return true;
        } catch (Exception e) {
            log.error(e);
        }
        return false;

    }
    public static String getName(String jwsToken, String key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return String.valueOf(claimsJws.getBody().get("name"));
        }
        return null;
    }
    public static String[] getRoles(String jwsToken, String key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return claimsJws.getBody().getAudience().split(",");
        }
        return new String[]{};
    }
    public static int getVersion(String jwsToken, String key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return Integer.parseInt(claimsJws.getBody().getId());
        }
        return -1;
    }
    /**
     * @Title: getCompanyId
     * @Description: TODO(获取企业ID)
     * @param @param jwsToken
     * @param @param key
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getCompanyId(String jwsToken, String key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return  String.valueOf(claimsJws.getBody().get("id"));
        }
        return null;
    }
    /**
     * @Title: setAcccessToken
     * @Description: TODO(存放鉴权中心token)
     * @param @param accessToken
     * @param @param key
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static void setAcccessToken(String authToken, String key,String accessToken) {
        if (isValid(authToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            claimsJws.getBody().put("acessToken", accessToken);
        }
    }
    /**
     * @Title: getCompanyId
     * @Description: TODO(获取鉴权中心Token)
     * @param @param jwsToken
     * @param @param key
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getAccessToken(String jwsToken, String key) {
        if (isValid(jwsToken, key)) {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwsToken);
            return claimsJws.getBody().getSubject();
        }
        return null;
    }
}
