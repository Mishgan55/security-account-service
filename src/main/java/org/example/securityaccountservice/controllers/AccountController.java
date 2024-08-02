package org.example.securityaccountservice.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.securityaccountservice.dtos.AccountDto;
import org.example.securityaccountservice.requests.TransferRequest;
import org.example.securityaccountservice.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    AccountService accountService;

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PostMapping("/admin/{id}/block")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountDto> blockAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.blockedAccount(id));
    }

    @PostMapping("/admin/{id}/unblock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountDto> unblockAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.unblockedAccount(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AccountDto> getAccount(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(accountService.getAccountByUserId(userId));
    }

    @PostMapping("/deposit")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AccountDto> deposit(Authentication authentication,
                                              @RequestBody TransferRequest request) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(accountService.deposit(userId, request.amount()));
    }

    @PostMapping("/withdraw")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AccountDto> withdraw(Authentication authentication,
                                               @RequestBody TransferRequest request) {
        Long userId = (Long) authentication.getPrincipal();
        return ResponseEntity.ok(accountService.withdraw(userId, request.amount()));
    }

}
