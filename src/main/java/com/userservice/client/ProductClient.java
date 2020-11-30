package com.userservice.client;

import com.userservice.dto.PageAndSizeDTO;
import com.userservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ProductClient", url = "http://localhost:8081/api/product-ms")
public interface ProductClient {

    @GetMapping("all")
    List<User> getProducts(PageAndSizeDTO pageAndSizeDTO);
}
