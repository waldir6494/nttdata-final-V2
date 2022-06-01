package com.nttdata.app.transaction;

import com.nttdata.app.transaction.model.TransactionModel;
import com.nttdata.app.transaction.model.TypeTransactionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    public List<TransactionModel> transactions(){
        List<TransactionModel> Transactions = new ArrayList<>();
        return Transactions;
    }

    @Bean
    public List<TypeTransactionModel> typeTransactions(){
        List<TypeTransactionModel> typeTransactions = new ArrayList<>();
        return typeTransactions;
    }

}
