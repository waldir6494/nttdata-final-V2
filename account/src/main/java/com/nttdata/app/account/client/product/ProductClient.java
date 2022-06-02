package com.nttdata.app.account.client.product;

import com.nttdata.app.account.client.product.model.Limit;
import com.nttdata.app.account.client.product.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "service-product", url = "http://localhost:8082")
@FeignClient(name = "service-product")
public interface ProductClient {
    @GetMapping("/api/limit/product/{id}")
    List<Limit> getFilterLimitProductFeign(@PathVariable Long id);

    @GetMapping("/api/product/{id}")
    Product getProductFeign(@PathVariable Long id);

}
