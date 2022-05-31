package com.nttdata.app.account.service.impl;

import com.nttdata.app.account.client.customer.CustomerClient;
import com.nttdata.app.account.client.customer.model.Customer;
import com.nttdata.app.account.client.product.ProductClient;
import com.nttdata.app.account.client.product.model.Limit;
import com.nttdata.app.account.exceptions.RequirementFailedException;
import com.nttdata.app.account.model.Account;
import com.nttdata.app.account.model.CreateAccount;
import com.nttdata.app.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {
    public static int idCount = 1;

    @Autowired
    List<Account> listAccounts;
    @Autowired
    ProductClient productClient;

    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    CustomerClient customerClient;
    @Override
    public Account create(CreateAccount account){
        Account accountAux = null;
        try{
            long auxId = AccountServiceImpl.idCount++;

            List<Customer> customers = new ArrayList<>();
            customers.add(customerClient.getCustomerFeign(account.getCustomer_id()));

            this.accountVerify(customers.get(0), account);

            accountAux = Account.builder()
                    .id(auxId)
                    .balance(account.getBalance())
                    .credit(account.getCredit())
                    .currentMovement(account.getCurrentMovement())
                    .product(productClient.getProductFeign(account.getProduct_id()))
                    .customers(customers)
                    .build();
            this.listAccounts.add(accountAux);
        } catch (RequirementFailedException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return accountAux;
    }

    @Override
    public List<Account> all() {
        return this.listAccounts;
    }

    @Override
    public Account show(Long id) {
        return this.listAccounts.stream().filter(account -> account.getId() == id).findFirst().get();
    }

    @Override
    public CreateAccount update(CreateAccount account, Long id) {

        this.listAccounts.stream().filter(accountAux -> accountAux.getId() == id)
                .forEach(account1 -> {
                    account1.setBalance(account.getBalance());
                    account1.setCredit(account.getCredit());
                    account1.setCurrentMovement(account.getCurrentMovement());
                });

        return account;
    }

    private void accountVerify(Customer customer, CreateAccount createAccount) throws RequirementFailedException{

        List <Customer> customers = (List<Customer>) this.listAccounts.stream().filter(account -> account.getProduct().getId() == createAccount.getProduct_id())
                                                                               .flatMap(account -> account.getCustomers().stream())
                                                                                .collect(Collectors.toList());
        long countCustomer = customers.stream().filter(customerAux -> customerAux.getId() == createAccount.getCustomer_id()).count();

        Limit limitQuantity = productClient.getFilterLimitProductFeign(createAccount.getProduct_id()).stream().filter(limit -> limit.getTypeCustomer().getId() == customer.getType().getId())
                                                                                       .reduce((first, second) -> second).orElseThrow(() -> new RequirementFailedException("Este tipo de producto no es admitido."));

        if(!limitQuantity.getLimitAccount()){
            return;
        }

        if(countCustomer >= limitQuantity.getMaxAccount()){
            throw new RequirementFailedException("Cantidad limite de cuentas superadas por este usuario o no puede tener este tipo de cuenta.");
        }
    }
}
