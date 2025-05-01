package com.example.rabbitmqpractice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routingkey.name}")
    private String routingKeyName;

    @Value("${rabbitmq.json.queue.name}")
    String jsonQueueName;
    @Value("${rabbitmq.json.routingkey.name}")
    String jsonRoutingKeyName;

    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchangeName);
    }
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(routingKeyName);
    }
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueueName);
    }
    @Bean
    public Binding jsonbinding(){
        return BindingBuilder.bind(jsonQueue()).to(topicExchange()).with(jsonRoutingKeyName);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate getTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;

        // rabbitTemplate does not automatically convert json so we need
        // to externally set messageConverter
    }
    /*
    other than these more 3 beans are needed
    ConnectionFactory
    RabbitTemplate
    RabbitAdmin
    but in spring boot they are autmatically done
    * */
}
