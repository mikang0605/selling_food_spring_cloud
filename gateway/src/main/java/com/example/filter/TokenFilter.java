package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.pojo.dto.Result;
import com.example.utils.JWTUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component
public class TokenFilter implements GlobalFilter {

    @Resource
    private StringRedisTemplate template;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        if(url.contains("/login")){
            return chain.filter(exchange);
        }
        if(url.contains("img")){
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getHeaders().getFirst("X-Token");
        if(token == null){
            Result result = new Result();
            result.setCode(50008);
            result.setMessage("token为空");
            ServerHttpResponse response = exchange.getResponse();
            DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONBytes(result));
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        Long expiresAtMill = JWTUtil.getExpiresAtMill(token);
        if(expiresAtMill == -1L){
            Result result = new Result();
            result.setMessage("token已过期");
            result.setCode(50014);
            ServerHttpResponse response = exchange.getResponse();
            DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONBytes(result));
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }else if (expiresAtMill == -2L){
            Result result = new Result();
            result.setMessage("token非法");
            result.setCode(50008);
            ServerHttpResponse response = exchange.getResponse();
            DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONBytes(result));
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        else if(Boolean.TRUE.equals(template.hasKey(token))){
            Result result = new Result();
            result.setCode(50014);
            result.setMessage("token已过期");
            ServerHttpResponse response = exchange.getResponse();
            DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONBytes(result));
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }
}
