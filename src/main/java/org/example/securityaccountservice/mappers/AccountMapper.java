package org.example.securityaccountservice.mappers;

import org.example.securityaccountservice.dtos.AccountDto;
import org.example.securityaccountservice.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toAccountDto(Account account);
}
