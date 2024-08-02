package org.example.securityaccountservice.requests;

public record RegisterRequest(
        String firstname,
        String lastname,
        String email,
        String password
) {
}
