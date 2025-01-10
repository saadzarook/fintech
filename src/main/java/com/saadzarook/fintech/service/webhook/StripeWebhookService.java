package com.saadzarook.fintech.service.webhook;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StripeWebhookService {

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    /**
     * Constructs and verifies the Stripe Event object.
     *
     * @param payload   The raw JSON payload from Stripe.
     * @param sigHeader The Stripe-Signature header.
     * @return The verified Event object.
     * @throws SignatureVerificationException If the signature verification fails.
     */
    public Event constructEvent(String payload, String sigHeader) throws SignatureVerificationException {
        return Webhook.constructEvent(
                payload, sigHeader, endpointSecret
        );
    }

    public void handleEvent(Event event) {
        String eventType = event.getType();
        log.info("Received event type: {}", eventType);

        switch (eventType) {
            case "payment_intent.succeeded":
                handlePaymentIntentSucceeded(event);
                break;
            case "payment_intent.payment_failed":
                handlePaymentIntentFailed(event);
                break;
            default:
                log.warn("Unhandled event type: {}", eventType);
        }
    }


    private void handlePaymentIntentSucceeded(Event event) {
        PaymentIntent paymentIntent = (PaymentIntent) event.getData().getObject();

        log.info("PaymentIntent Succeeded:");
        log.info("ID: {}", paymentIntent.getId());
        log.info("Amount: {}", paymentIntent.getAmount());
        log.info("Currency: {}", paymentIntent.getCurrency());
        log.info("Customer ID: {}", paymentIntent.getCustomer());


    }

    private void handlePaymentIntentFailed(Event event) {
        com.stripe.model.PaymentIntent paymentIntent = (com.stripe.model.PaymentIntent) event.getData().getObject();

        log.info("PaymentIntent Failed:");
        log.info("ID: {}", paymentIntent.getId());
        log.info("Amount: {}", paymentIntent.getAmount());
        log.info("Currency: {}", paymentIntent.getCurrency());
        log.info("Customer ID: {}", paymentIntent.getCustomer());

        if (paymentIntent.getLastPaymentError() != null) {
            log.info("Failure Message: {}", paymentIntent.getLastPaymentError().getMessage());
        } else {
            log.info("Failure Message: Unknown error.");
        }

    }

}
