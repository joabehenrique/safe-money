package com.safe.safe.controller;

import com.safe.safe.dto.AccountDTO;
import com.safe.safe.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/account")
public class AccountController {
    public final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAllAccount(){
        List<AccountDTO> account = accountService.findAllAccount();
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> findAccountById(@PathVariable Long id){
        AccountDTO account = accountService.findAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> saveAccount(@RequestBody AccountDTO newAccount){
        AccountDTO account = accountService.saveAccount(newAccount);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO newDataAccount, @PathVariable Long id){
        AccountDTO account = accountService.updateAccount(id, newDataAccount);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
