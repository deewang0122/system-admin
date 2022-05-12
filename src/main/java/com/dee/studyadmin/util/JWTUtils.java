package com.dee.studyadmin.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dee.studyadmin.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtils {
    private static final String SIGN = "sign22";//将sign设置成全局变量

    private static final Long EXPIRE_TIME = 5*60*1000L;


    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(User user) {
        //过期时间
        //Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)// 添加头部
                //可以将基本信息放到claims中
                .withClaim("id", user.getId())  //userId
                .withClaim("name", user.getName())  //userName
                .withClaim("loginCode", user.getLoginCode())    //loginCode
                //.withExpiresAt(expireDate)  //超时设置,设置过期的日期
                .withIssuedAt(new Date())   //签发时间
                .sign(Algorithm.HMAC256(SIGN)); //SECRET加密
        return token;
    }

    public static DecodedJWT verifyToken(String token){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return decodedJWT;
    }


    public static User getUserByToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);

        if (decodedJWT.getClaims() == null) {
            return null;
        }

        User user = new User();

        user.setId(decodedJWT.getClaim("id").asLong());
        user.setLoginCode(decodedJWT.getClaim("name").asString());
        user.setName(decodedJWT.getClaim("loginCode").asString());

        return user;
    }


}