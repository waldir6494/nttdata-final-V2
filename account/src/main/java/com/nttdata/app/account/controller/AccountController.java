package com.nttdata.app.account.controller;

import com.nttdata.app.account.model.Account;
import com.nttdata.app.account.model.CreateAccount;
import com.nttdata.app.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private IAccountService accountService;
    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody CreateAccount account) {
        Account accountCreated = this.accountService.create(account);
        return accountCreated;
    }

    @GetMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Account> all() {
        return this.accountService.all();
    }

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Account show(@PathVariable Long id) {
        return this.accountService.show(id);
    }

    @PutMapping("/account/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccount update(@RequestBody CreateAccount account, @PathVariable Long id) {
        LOGGER.info("update de controllerrrrrrrrrrrr");
        return this.accountService.update(account, id);

    }
}
