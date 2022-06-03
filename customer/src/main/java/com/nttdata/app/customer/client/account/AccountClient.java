package com.nttdata.app.customer.client.account;

import com.nttdata.app.customer.client.account.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-account")
public interface AccountClient {
    @GetMapping("/api/customer/account/{id}")
    List<Account> getCustomerAccountFeign(@PathVariable Long id);
}
