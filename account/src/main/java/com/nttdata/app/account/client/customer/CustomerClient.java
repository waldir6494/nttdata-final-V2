package com.nttdata.app.account.client.customer;

import com.nttdata.app.account.client.customer.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-customer", url = "http://localhost:8081")
public interface CustomerClient {
    @GetMapping("/api/customer/{id}")
    Customer getCustomerFeign(@PathVariable long id);
}
