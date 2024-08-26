package com.francofral;

import com.francofral.clients.fraud.FraudCheckResponse;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public class FraudController {

    private final FraudCheckService service;

    public FraudController(FraudCheckService service) {
        this.service = service;
    }

    @Observed(
        name = "user.name",
        contextualName = "register-check_fraud",
        lowCardinalityKeyValues = {"userType", "userType2"}
    )
    @GetMapping("{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        boolean isFraudulentCustomer = service.isFraudulentCustomer(customerId);
        log.info("Fraud check request for customer {}", customerId);

        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
