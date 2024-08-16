package com.francofral;

import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    // Example of using an annotation to observe methods
    // <user.name> will be used as a metric name
    // <getting-user-name> will be used as a span  name
    // <userType=userType2> will be set as a tag for both metric & span
    @Observed(
        name = "user.name",
        contextualName = "register-check_fraud-notification",
        lowCardinalityKeyValues = {"userType", "userType2"}
    )
    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest) {
        log.info("New customer registration {}", customerRequest);
        customerService.registerCustomer(customerRequest);
    }
}
