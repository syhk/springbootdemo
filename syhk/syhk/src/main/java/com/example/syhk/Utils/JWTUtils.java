package com.example.syhk.Utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
public class JWTUtils {
    private static final String jwtToken = "syhk725482520@##$$";
    public static String createToken(String userId)
    {
        Map<String,Object> claims = new HashMap<>();

        claims.put("userId",userId);

        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,jwtToken) // 签发算法， 秘钥为 jwtToken
                .setClaims(claims)   //body 数据要唯一，自己设置
                .setIssuedAt(new Date()) //签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000 ));// 一天有效期

        String token = jwtBuilder.compact();
        return token;
    }

    public static Map<String,Object> checkToken(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
