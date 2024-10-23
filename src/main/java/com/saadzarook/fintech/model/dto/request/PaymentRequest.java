package com.saadzarook.fintech.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    @Min(value = 1, message = "Amount must be greater than zero")
    private int amount;

    @NotBlank(message = "Currency is required")
    private String currency;
}
