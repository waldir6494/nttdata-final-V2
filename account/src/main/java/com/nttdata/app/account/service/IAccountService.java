package com.nttdata.app.account.service;

import com.nttdata.app.account.model.entity.Account;
import com.nttdata.app.account.model.CreateAccount;

import java.util.List;

public interface IAccountService {
    Account create(CreateAccount account);
    List<Account> all();
    Account show(Long id);
//    CreateAccount update(CreateAccount account, Long id);
}
