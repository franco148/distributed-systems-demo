package com.francofral.notificationsvr;

import com.francofral.clients.notification.NotificationRequest;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {

    private final NotificationService service;

    @Observed(
        name = "user.name",
        contextualName = "register-notification",
        lowCardinalityKeyValues = {"userType", "userType2"}
    )
    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("New notification... {}", notificationRequest);

        service.send(notificationRequest);
    }
}
