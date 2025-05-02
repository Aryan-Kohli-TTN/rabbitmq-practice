package com.example.rabbitmqpractice.controller;

import com.example.rabbitmqpractice.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/redis")
@RequiredArgsConstructor
public class RedisTestController {
    private final RedisService redisService;

    @GetMapping("")
    ResponseEntity<Object> setKey(@RequestParam("key") String key , @RequestParam("value") String value)
    {
        redisService.setMessage(key,value);
        return new ResponseEntity<>("key is set", HttpStatus.OK);
    }
}
