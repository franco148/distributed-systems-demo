package com.francofral;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayServiceApplication.class, args);
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