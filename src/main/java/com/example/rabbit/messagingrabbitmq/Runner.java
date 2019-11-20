package com.example.rabbit.messagingrabbitmq;

import org.springframework.amqp.core.TopicExchange;
import com.example.rabbit.RabbitApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.concurrent.TimeUnit;


@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    private final Receiver receiver;

    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
