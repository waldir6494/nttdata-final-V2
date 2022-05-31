package com.nttdata.app.account.service;

import com.nttdata.app.account.model.Account;
import com.nttdata.app.account.model.CreateAccount;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface IAccountService {
    Account create(CreateAccount account);
    List<Account> all();
    Account show(Long id);
    CreateAccount update(CreateAccount account, Long id);
}
