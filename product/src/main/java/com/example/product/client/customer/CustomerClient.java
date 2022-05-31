package com.example.product.client.customer;

import com.example.product.client.customer.model.TypeCustomer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "service-customer", url = "http://localhost:8081")
@FeignClient(name = "service-customer")
public interface CustomerClient {
    @GetMapping("/api/type/customer/{id}")
    TypeCustomer getTypeCustomerFeign(@PathVariable long id);
}
