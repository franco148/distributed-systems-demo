package com.francofral;

import com.francofral.notificationsvr.NotificationMqConfig;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(
    scanBasePackages = {
        "com.francofral",
        "com.francofral.notificationsvr"
    }
)
@EnableDiscoveryClient
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Configuration(proxyBeanMethods = false)
    static class ObservabilityConfig {
        // To have the @Observed support we need to register this aspect
        @Bean
        ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
            return new ObservedAspect(observationRegistry);
        }
    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationMqConfig notificationMqConfig) {
//        return args -> {
//            producer.publish("This is a test message", notificationMqConfig.getInternalExchange(), notificationMqConfig.getInternalNotificationRoutingKey());
//        };
//    }
}