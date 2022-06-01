package com.nttdata.app.transaction.controller;

import com.nttdata.app.transaction.model.TransactionCreate;
import com.nttdata.app.transaction.model.entity.Transaction;
import com.nttdata.app.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;

    @PostMapping("/transaction/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction deposit(@RequestBody TransactionCreate transaction) {
        return this.transactionService.deposit(transaction);
    }
/*
    @GetMapping("/transaction/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transaction show(@PathVariable long id) {
        return this.transactionService.show(id);
    }

    @GetMapping(value = {"/transaction"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Transaction> all() {
        return this.transactionService.all();
    }*/
}
