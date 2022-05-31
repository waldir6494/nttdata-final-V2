package com.nttdata.app.transaction;

import com.nttdata.app.transaction.model.Transaction;
import com.nttdata.app.transaction.model.TypeTransaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    public List<Transaction> transactions(){
        List<Transaction> Transactions = new ArrayList<>();
        return Transactions;
    }

    @Bean
    public List<TypeTransaction> typeTransactions(){
        List<TypeTransaction> typeTransactions = new ArrayList<>();
        return typeTransactions;
    }

}
