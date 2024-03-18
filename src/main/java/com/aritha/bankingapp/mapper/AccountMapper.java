package com.aritha.bankingapp.mapper;

import com.aritha.bankingapp.dto.Accountdto;
import com.aritha.bankingapp.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(Accountdto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccount_holder_name(),
                accountDto.getBalance()
        );
        return account;
    }

    public static Accountdto mapToAccountDto(Account account) {
        Accountdto accountDto = new Accountdto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
