package com.saadzarook.fintech.controller;

import com.saadzarook.fintech.model.dto.request.PaymentRequest;
import com.saadzarook.fintech.service.PaymentService;
import com.stripe.model.PaymentIntent;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment-intents")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@Valid @RequestBody PaymentRequest paymentRequest) {
        log.info("Received payment request: {}", paymentRequest);

        PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentRequest);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("clientSecret", paymentIntent.getClientSecret());

        log.info("Payment intent created successfully: {}", paymentIntent.getId());
        return ResponseEntity.ok(responseData);
    }

}
