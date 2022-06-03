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

    @PostMapping("/transaction/withdrawal")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction withdrawal(@RequestBody TransactionCreate transaction) {
        return this.transactionService.withdrawal(transaction);
    }

    @PostMapping("/transaction/credit/use")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction useCredit(@RequestBody TransactionCreate transaction) {
        return this.transactionService.useCredit(transaction);
    }

    @PostMapping("/transaction/credit/pay")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction paymentCredit(@RequestBody TransactionCreate transaction) {
        return this.transactionService.paymentCredit(transaction);
    }
    @GetMapping("/transaction/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findByAccount(@PathVariable Long id) {
        return this.transactionService.findByAccount(id);
    }

    @GetMapping("/transaction/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transaction show(@PathVariable long id) {
        return this.transactionService.show(id);
    }

    @GetMapping(value = {"/transaction"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Transaction> all() {
        return this.transactionService.all();
    }
}
