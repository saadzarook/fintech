package com.saadzarook.fintech.service;

import com.saadzarook.fintech.global.exception.payments.PaymentException;
import com.saadzarook.fintech.model.dto.request.PaymentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final StripeService stripeService;

    public PaymentIntent createPaymentIntent(PaymentRequest paymentRequest) throws PaymentException {
        try {
            log.info("Creating payment intent for amount: {} {}", paymentRequest.getAmount(), paymentRequest.getCurrency());
            Map<String, Object> params = new HashMap<>();
            params.put("amount", paymentRequest.getAmount());
            params.put("currency", paymentRequest.getCurrency());
            params.put("payment_method_types", List.of("card"));

            return stripeService.createPaymentIntent(params);
        } catch (StripeException e) {
            throw new PaymentException("Error creating payment intent", e);
        }
    }
}
