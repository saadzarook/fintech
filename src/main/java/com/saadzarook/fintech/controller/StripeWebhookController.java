package com.saadzarook.fintech.controller;

import com.saadzarook.fintech.service.webhook.StripeWebhookService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/webhooks/stripe")
@RequiredArgsConstructor
@Slf4j
public class StripeWebhookController {

    // TODO Add event signature verification

    private final StripeWebhookService stripeWebhookService;

    @PostMapping
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        log.info("Received Stripe webhook with payload: {}", payload);
        try {
            Event event = stripeWebhookService.constructEvent(payload, sigHeader);
            stripeWebhookService.handleEvent(event);
            return ResponseEntity.ok("Webhook received and processed.");

        } catch (SignatureVerificationException e) {
            System.err.println("Invalid signature: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature.");

        } catch (Exception e) {
            System.err.println("Error processing webhook: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing webhook.");
        }
    }
}
