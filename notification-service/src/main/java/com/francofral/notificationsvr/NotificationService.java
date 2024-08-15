package com.francofral.notificationsvr;

import com.francofral.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository repository;

    public void send(NotificationRequest notificationRequest) {
        repository.save(
            Notification.builder()
                    .toCustomerId(notificationRequest.toCustomerId())
                    .toCustomerEmail(notificationRequest.toCustomerEmail())
                    .sender("FrancoFral")
                    .message(notificationRequest.message())
                    .sentAt(LocalDateTime.now())
                    .build()
        );
    }
}
