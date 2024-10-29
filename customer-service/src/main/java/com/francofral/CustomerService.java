package com.francofral;

import com.francofral.clients.fraud.FraudCheckResponse;
import com.francofral.clients.fraud.FraudClient;
import com.francofral.clients.notification.NotificationClient;
import com.francofral.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.isNull;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate,
        FraudClient fraudClient,
        NotificationClient notificationClient,
        RabbitMQMessageProducer rabbitMQMessageProducer) {

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();

        // TODO: check if email valid
        // Save customer
        customerRepository.saveAndFlush(customer);

        // Check if email not taken

        // Check is fraudster
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD-SERVICE/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId());
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (isNull(fraudCheckResponse) || fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster detected");
        }

        // Send notification
        // TODO: make it async. i.e add to queue
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to FrancoFral demos...", customer.getFirstName())
        );
        rabbitMQMessageProducer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");
    }
}
