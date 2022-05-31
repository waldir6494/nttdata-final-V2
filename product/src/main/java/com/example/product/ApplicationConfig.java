package com.example.product;

import com.example.product.client.customer.CustomerClient;
import com.example.product.client.customer.model.TypeCustomer;
import com.example.product.model.Limit;
import com.example.product.model.Product;
import com.example.product.model.TypeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class ApplicationConfig {
    @Autowired
    CustomerClient customerClient;
    @Bean
    public List<TypeProduct> typeProduct(){
        List<TypeProduct> typeProduct = new ArrayList<>();
        typeProduct.add(new TypeProduct(1, "Débito", false));
        typeProduct.add(new TypeProduct(2, "Crédito", true));
        return typeProduct;
    }

    @Bean
    public List<Product> products(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Ahorro", false,false,true, 0, 10,null, this.typeProduct().get(0)));
        productList.add(new Product(2, "Cuenta corriente", false, true,false, 0, null, null, this.typeProduct().get(0)));
        productList.add(new Product(3, "Plazo fijo", false, true,true, 0, 1,new Date(), this.typeProduct().get(0)));
        return productList;
    }

    @Bean
    public List<Limit> limits(){
        List<Limit> limitList = new ArrayList<>();
        TypeCustomer personal = this.customerClient.getTypeCustomerFeign(1);
        TypeCustomer empresarial = this.customerClient.getTypeCustomerFeign(2);
        limitList.add(new Limit(1, this.products().get(0), personal, true, 1));
        limitList.add(new Limit(2, this.products().get(1), personal, true, 1));
        limitList.add(new Limit(3, this.products().get(2), personal, false, 0));

        limitList.add(new Limit(3, this.products().get(1), empresarial, false, 0));
        return limitList;
    }

    /*@Bean
    public List<Debit> listDebit(){
        List<Debit> debits = new ArrayList<>();
        debits.add(new Debit(1,"Ahorro",1, false, 10));
        debits.add(new Debit(2,"Cuenta corriente",2, true, 0));
        debits.add(new Debit(3,"Plazo Fijo",3, false, 1));
        return debits;
    }*/

}
