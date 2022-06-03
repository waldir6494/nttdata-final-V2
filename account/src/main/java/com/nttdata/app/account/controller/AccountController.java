package com.nttdata.app.account.controller;

import com.nttdata.app.account.model.entity.Account;
import com.nttdata.app.account.model.CreateAccount;
import com.nttdata.app.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody CreateAccount account) {
        Account accountCreated = accountService.create(account);
        return accountCreated;
    }

    @GetMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Account> all() {
        return accountService.all();
    }

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Account show(@PathVariable Long id) {
        return accountService.show(id);
    }

    @PutMapping("/account/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Account update(@RequestBody CreateAccount account, @PathVariable Long id) {
        return this.accountService.update(account, id);
    }
}
