package com.nttdata.app.transaction.controller;

import com.nttdata.app.transaction.model.entity.TypeTransaction;
import com.nttdata.app.transaction.service.ITypeTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TypeTransactionController {
    @Autowired
    private ITypeTransactionService typeTransactionService;

    @PostMapping("/type/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public TypeTransaction create(@RequestBody TypeTransaction typeTransaction) {
        return this.typeTransactionService.save(typeTransaction);
    }

    @GetMapping("/type/transaction/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TypeTransaction show(@PathVariable long id) {
        return this.typeTransactionService.show(id);
    }

    @GetMapping(value = {"/type/transaction"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<TypeTransaction> all() {
        return typeTransactionService.all();
    }
}
