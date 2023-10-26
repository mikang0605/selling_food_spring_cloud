package com.example.user_service.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 * <p>
 * 使用的是java-jwt
 */
public class JWTUtil {

    /**
     * 生成签名
     *
     * @param claims     附带信息
     * @param secret     生成token的密钥
     * @param expireTime 过期时间，单位：秒
     * @return 加密的token
     */
    public static String sign(Map<String, String> claims, String secret, Long expireTime) {
        Date date = new Date(System.currentTimeMillis() + expireTime * 1000);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带信息
        JWTCreator.Builder builder = JWT.create();
        for (String key : claims.keySet()) {
            builder.withClaim(key, claims.get(key));
        }
        // 过期时间
        builder.withExpiresAt(date);
        return builder.sign(algorithm);
    }

    /**
     * 验证token
     *
     * @param token  要验证的token
     * @param secret 密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier build = JWT.require(algorithm).build();
        build.verify(token);
        return true;
    }

    /**
     * 验证token
     *
     * @param token  要验证的token
     * @param secret 密钥
     * @return 状态及信息，1：正确，0：错误
     */
    public static Map<String, Object> verifyInfo(String token, String secret) {
        Map<String, Object> map = new HashMap<>();
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier build = JWT.require(algorithm).build();
            build.verify(token);
            map.put("state", "1");
            map.put("message", "令牌正确");
            return map;
        } catch (TokenExpiredException e) {
            // 需要打印时，可以去掉注释
            map.put("state", "0");
            map.put("message", "令牌过期");
            return map;
        } catch (SignatureVerificationException e) {
            map.put("state", "0");
            map.put("message", "验证错误");
            return map;
        } catch (AlgorithmMismatchException e) {
            map.put("state", "0");
            map.put("message", "算法不匹配");
            return map;
        } catch (Exception e) {
            map.put("state", "0");
            map.put("message", e.getMessage());
            return map;
        }
    }

    /**
     * 获取token中的信息，无需解密也能获得
     *
     * @param token 要解析的token
     * @param key   信息的key
     * @return 信息
     */
    public static String getClaims(String token, String key) {
        try {
            DecodedJWT decode = JWT.decode(token);
            return decode.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取过期时间,无需解密也能获得
     *
     * @param token 需要解析的token
     * @return 过期时间
     */
    public static Date getExpiresAt(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            return decode.getExpiresAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取token的剩余时间,无需解密也能获得,单位：毫秒
     *
     * @param token 需要解析的token
     * @return 剩余时间，如果已过期，则返回 -1
     */
    public static Long getExpiresAtMill(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            long l = decode.getExpiresAt().getTime() - System.currentTimeMillis();
            return l < 0 ? -1L : l;
        } catch (JWTDecodeException e) {
            return -2L;
        }
    }
}

