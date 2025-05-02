package com.example.rabbitmqpractice.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

@Service
public class RedisService {

    RedisTemplate<String,String> redisTemplate;

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);
    public void setMessage(String key,String value){
        redisTemplate.opsForValue().set(key,value);
        logger.info("value set for key {} is {}",key,redisTemplate.opsForValue().get(key));
    }
}
