package com.francofral.notificationsvr;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
//@ConfigurationProperties(prefix = "spring.rabbitmq")
public class NotificationMqConfig {

    @Value("${spring.rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${spring.rabbitmq.queue.notification}")
    private String notificationQueue;

    @Value("${spring.rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(internalExchange);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(notificationQueue);
    }

    @Bean
    public Binding internalNotificationBinding() {
        return BindingBuilder.bind(notificationQueue())
                .to(internalTopicExchange())
                .with(internalNotificationRoutingKey);
    }
}
