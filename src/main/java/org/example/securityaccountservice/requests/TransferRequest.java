package org.example.securityaccountservice.requests;

import java.math.BigDecimal;

public record TransferRequest(
        BigDecimal amount
) {
}
