package com.nttdata.app.transaction.client.product;

import com.nttdata.app.transaction.client.product.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-product")
public interface ProductClient {
    @GetMapping("/api/product/{id}")
    Product getProductFeign(@PathVariable long id);
}
