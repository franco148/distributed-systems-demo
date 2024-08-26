package com.francofral;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
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