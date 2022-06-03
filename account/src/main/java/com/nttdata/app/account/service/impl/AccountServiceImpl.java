package com.nttdata.app.account.service.impl;

import com.nttdata.app.account.client.customer.CustomerClient;
import com.nttdata.app.account.client.customer.model.Customer;
import com.nttdata.app.account.client.product.ProductClient;
import com.nttdata.app.account.client.product.model.Limit;
import com.nttdata.app.account.client.product.model.Product;
import com.nttdata.app.account.model.entity.Account;
import com.nttdata.app.account.model.CreateAccount;
import com.nttdata.app.account.repository.AccountRepository;
import com.nttdata.app.account.service.IAccountService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductClient productClient;

    @Autowired
    CustomerClient customerClient;
    @Override
    public Account create(CreateAccount account){
        Account auxAccount=null;
        try{
            Customer customer = customerClient.getCustomerFeign(Long.valueOf(account.getCustomer_id()));
            Product product = productClient.getProductFeign(Long.valueOf(account.getProduct_id()));

            //obtiene las cuentas actulaes que pose este cliente
            List<Account> accounts=accountRepository.findAll()
                        .stream()
                        .filter(x->customer.getId()== x.getId())
                        .collect(Collectors.toList());
            //obtiene los limites establecidos para esta tipo de cuenta
            Limit limit=productClient.getFilterLimitProductFeign(product.getId()).
                        stream().filter(x->customer.getType().getId()==Long.valueOf(x.getTypeCustomer()))
                        .collect(Collectors.toList()).get(0);
                if(limit.getLimitAccount() && accounts.size()>0){
                    System.out.println("El usuario excedio sus limites de cuenta");
                }
                else{
                    Account newAccount=Account.builder()
                            .customer(account.getCustomer_id())
                            .product(account.getProduct_id())
                            .currentMovement(account.getCurrent_movement())
                            .balance(account.getBalance())
                            .credit(account.getCredit())
                            .build();
                    auxAccount =accountRepository.save(newAccount);
                }

        }catch (FeignException e){
            System.out.println("Error al acceder indices que no existen");
        }
        return auxAccount;
    }

    @Override
    public List<Account> all(){
        return accountRepository.findAll();
    }

    @Override
    public Account show(Long id) {
        return accountRepository.findById(id).get();
    }
    
//
//    @Override
//    public CreateAccount update(CreateAccount account, Long id) {
//
//        this.listAccounts.stream().filter(accountAux -> accountAux.getId() == id)
//                .forEach(account1 -> {
//                    account1.setBalance(account.getBalance());
//                    account1.setCredit(account.getCredit());
//                    account1.setCurrentMovement(account.getCurrentMovement());
//                });
//
//        return account;
//    }
//
//    private void accountVerify(Customer customer, CreateAccount createAccount) throws RequirementFailedException{
//
//        List <Customer> customers = (List<Customer>) this.listAccounts.stream().filter(account -> account.getProduct().getId() == createAccount.getProduct_id())
//                                                                               .flatMap(account -> account.getCustomers().stream())
//                                                                                .collect(Collectors.toList());
//        long countCustomer = customers.stream().filter(customerAux -> customerAux.getId() == createAccount.getCustomer_id()).count();
//
//        Limit limitQuantity = productClient.getFilterLimitProductFeign(createAccount.getProduct_id()).stream().filter(limit -> limit.getTypeCustomer().getId() == customer.getType().getId())
//                                                                                       .reduce((first, second) -> second).orElseThrow(() -> new RequirementFailedException("Este tipo de producto no es admitido."));
//
//        if(!limitQuantity.getLimitAccount()){
//            return;
//        }
//
//        if(countCustomer >= limitQuantity.getMaxAccount()){
//            throw new RequirementFailedException("Cantidad limite de cuentas superadas por este usuario o no puede tener este tipo de cuenta.");
//        }
//    }
}
