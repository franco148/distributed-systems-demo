package com.francofral.rabbitmq;

import com.francofral.clients.notification.NotificationRequest;
import com.francofral.notificationsvr.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("Consuming notification {} from queue", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
