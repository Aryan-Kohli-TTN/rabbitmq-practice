package com.example.rabbitmqpractice.consumer;

import com.example.rabbitmqpractice.co.TestCO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void messageReceiver(String message){
        logger.info("Received message is {}",message);
    }

    @RabbitListener(queues = "${rabbitmq.json.queue.name}")
    public void jsonMessageReceiver(TestCO testCO){
        logger.info("json object received : {} ",testCO.toString());
    }
}
