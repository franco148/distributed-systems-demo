package com.francofral.clients.notification;

import io.micrometer.observation.annotation.Observed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@Observed
@FeignClient("NOTIFICATION-SERVICE")
public interface NotificationClient {

    @PostMapping("api/v1/notification")
    void sendNotification(NotificationRequest notificationRequest);
}