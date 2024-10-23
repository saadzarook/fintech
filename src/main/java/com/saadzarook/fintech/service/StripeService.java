package com.saadzarook.fintech.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.util.Map;

public interface StripeService {
    PaymentIntent createPaymentIntent(Map<String, Object> params) throws StripeException;
}
