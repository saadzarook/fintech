package com.saadzarook.fintech.service;

import com.saadzarook.fintech.global.exception.payments.PaymentException;
import com.saadzarook.fintech.model.dto.request.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
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
            params.put("payment_method_types", Arrays.asList("card"));

            return stripeService.createPaymentIntent(params);
        } catch (StripeException e) {
            throw new PaymentException("Error creating payment intent", e);
        }
    }
}
