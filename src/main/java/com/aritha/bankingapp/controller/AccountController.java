package com.aritha.bankingapp.controller;

import com.aritha.bankingapp.dto.Accountdto;
import com.aritha.bankingapp.entity.Account;
import com.aritha.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add Account REST API
    @PostMapping
    public ResponseEntity<Accountdto> addAccount(@RequestBody Accountdto accountdto) {
        return new ResponseEntity<>(accountService.createAccount(accountdto), HttpStatus.CREATED);
    }

    //Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<Accountdto> getAccountById(@PathVariable Long id) {
        Accountdto accountdto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountdto);
    }

    //Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<Accountdto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request){
        Accountdto accountdto = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(accountdto);
    }

    //withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<Accountdto> withdraw(@PathVariable Long id,
                                               @RequestBody Map<String, Double> request){
        Accountdto accountdto = accountService.withdraw(id, request.get("amount"));
        return ResponseEntity.ok(accountdto);
    }

    //Get All Accounts REST API
    @GetMapping
    public ResponseEntity<List<Accountdto>> getAllAccounts(){
        List<Accountdto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    //Delete the account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
