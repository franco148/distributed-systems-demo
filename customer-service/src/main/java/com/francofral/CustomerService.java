package com.francofral;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.isNull;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

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
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:9191/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());

        if (isNull(fraudCheckResponse) || fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster detected");
        }

        // TODO: send notification
    }
}
