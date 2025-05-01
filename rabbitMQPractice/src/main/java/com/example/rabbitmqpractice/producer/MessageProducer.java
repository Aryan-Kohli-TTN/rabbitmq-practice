package com.example.rabbitmqpractice.producer;

import com.example.rabbitmqpractice.co.TestCO;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private final Logger logger = LoggerFactory.getLogger(MessageProducer.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routingkey.name}")
    private String routingKeyName;
    @Value("${rabbitmq.json.routingkey.name}")
    private String jsonRoutingKeyName;

    public void produceMessage(String message){
        logger.info("Sending message {}",message);
        rabbitTemplate.convertAndSend(exchangeName,routingKeyName,message);
        logger.info("message sent successfully {}",message);
    }
    public void produceJsonMessage(TestCO testCO){
        logger.info("Sending object :  {}",testCO.toString());
        rabbitTemplate.convertAndSend(exchangeName,jsonRoutingKeyName,testCO);
        logger.info("Object sent successfully {}",testCO.toString());
    }

}
