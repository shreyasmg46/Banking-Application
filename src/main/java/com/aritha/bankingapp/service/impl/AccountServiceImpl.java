package com.aritha.bankingapp.service.impl;

import com.aritha.bankingapp.dto.Accountdto;
import com.aritha.bankingapp.entity.Account;
import com.aritha.bankingapp.mapper.AccountMapper;
import com.aritha.bankingapp.repository.AccountRepository;
import com.aritha.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Accountdto createAccount(Accountdto accountdto) {
        Account account = AccountMapper.mapToAccount(accountdto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public Accountdto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public Accountdto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public Accountdto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));

        if(amount > account.getBalance() ){
            throw new RuntimeException("Insufficient Balance");
        }
        double withdrawtotal = account.getBalance() - amount;
        account.setBalance(withdrawtotal);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<Accountdto> getAllAccounts() {
       List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account -> AccountMapper.mapToAccountDto(account))).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.deleteById(id);

    }
}
