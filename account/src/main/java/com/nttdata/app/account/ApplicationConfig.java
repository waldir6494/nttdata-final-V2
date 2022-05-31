package com.nttdata.app.account;

import com.nttdata.app.account.model.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    public List<Account> listAccounts(){
        List<Account> list = new ArrayList<>();
        return list;
    }
}
