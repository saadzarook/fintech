package com.saadzarook.fintech.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${STRIPE_API_KEY}")
    private String apiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = apiKey;
    }

    public PaymentIntent createPaymentIntent(Map<String, Object> params) throws StripeException {
        return PaymentIntent.create(params);
    }
}
