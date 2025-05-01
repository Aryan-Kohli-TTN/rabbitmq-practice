package com.example.rabbitmqpractice.controller;

import com.example.rabbitmqpractice.co.TestCO;
import com.example.rabbitmqpractice.producer.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

    private final MessageProducer messageProducer;
    @GetMapping("/msg")
    ResponseEntity<Object> sendMessage(@RequestParam("message") String message){
        messageProducer.produceMessage(message);
        return ResponseEntity.ok("message sent");
    }
    @GetMapping("/msg/json")
    ResponseEntity<Object> sendJsonMessage(@RequestBody TestCO testCO){
        messageProducer.produceJsonMessage(testCO);
        return ResponseEntity.ok("json object sent");
    }
}
