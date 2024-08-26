package com.francofral;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(
    scanBasePackages = "com.francofral"
)
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.francofral.clients")
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Configuration(proxyBeanMethods = false)
    static class ObservabilityConfig {
        // To have the @Observed support we need to register this aspect
        @Bean
        ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
            return new ObservedAspect(observationRegistry);
        }
    }
}