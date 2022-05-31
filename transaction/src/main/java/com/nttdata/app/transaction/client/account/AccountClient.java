package com.nttdata.app.transaction.client.account;

import com.nttdata.app.transaction.client.account.model.Account;
import com.nttdata.app.transaction.client.account.model.CreateAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "service-customer", url = "http://localhost:8083")
@FeignClient(name = "service-customer")
public interface AccountClient {
    @GetMapping("/api/account/{id}")
    Account getAccountFeign(@PathVariable long id);

    @PutMapping("/api/account/{id}")
    CreateAccount putAccountFeign(@RequestBody CreateAccount createAccount, @PathVariable long id);
}
