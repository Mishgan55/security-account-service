package org.example.securityaccountservice.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.securityaccountservice.dtos.AccountDto;
import org.example.securityaccountservice.entities.Account;
import org.example.securityaccountservice.mappers.AccountMapper;
import org.example.securityaccountservice.repositories.AccountRepository;
import org.example.securityaccountservice.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {

    AccountRepository accountRepository;
    AccountMapper accountMapper;

    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toAccountDto)
                .toList();
    }

    public Account findSafe(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public AccountDto blockedAccount(Long id) {
        return blockedOrUnblocked(id);
    }

    public AccountDto unblockedAccount(Long id) {
        return blockedOrUnblocked(id);
    }

    private AccountDto blockedOrUnblocked(Long id) {
        Account account = findSafe(id);
        account.setBlocked(Boolean.TRUE.equals(account.getBlocked()));
        return accountMapper.toAccountDto(accountRepository.save(account));
    }

    public AccountDto getAccountByUserId(Long id) {
        Account account = findSafe(id);
        return accountMapper.toAccountDto(account);
    }

    public AccountDto deposit(Long id, BigDecimal amount) {
        Account account = findSafe(id);
        if (Boolean.TRUE.equals(account.getBlocked())) {
            throw new IllegalStateException("Account is blocked");
        }
        account.setBalance(account.getBalance().add(amount));
        return accountMapper.toAccountDto(accountRepository.save(account));
    }

    public AccountDto withdraw(Long id, BigDecimal amount) {
        Account account = findSafe(id);
        if (Boolean.TRUE.equals(account.getBlocked())) {
            throw new IllegalStateException("Account is blocked");
        }
        if (account.getBalance().compareTo(amount) > 0) {
            throw new IllegalStateException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(amount));
        return accountMapper.toAccountDto(accountRepository.save(account));
    }
}
