package com.saadzarook.fintech;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.saadzarook.fintech.model.dto.request.PaymentRequest;
import com.saadzarook.fintech.service.PaymentService;
import com.saadzarook.fintech.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private StripeService stripeService;

    @Test
    void testCreatePaymentIntent() throws StripeException {
        // Arrange
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(5000);
        paymentRequest.setCurrency("usd");

        PaymentIntent mockPaymentIntent = new PaymentIntent();
        mockPaymentIntent.setId("pi_test");
        mockPaymentIntent.setClientSecret("test_client_secret");
        mockPaymentIntent.setCurrency("usd");

        when(stripeService.createPaymentIntent(anyMap())).thenReturn(mockPaymentIntent);

        // Act
        PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentRequest);

        // Assert
        assertNotNull(paymentIntent);
        assertEquals("pi_test", paymentIntent.getId());
        assertEquals("test_client_secret", paymentIntent.getClientSecret());
    }
}
