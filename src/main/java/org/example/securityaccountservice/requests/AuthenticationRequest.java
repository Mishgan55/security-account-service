package org.example.securityaccountservice.requests;

public record AuthenticationRequest(
        String email,
        String password
) {
}
