package com.userservice.client;

import com.userservice.dto.OrderBodyDTO;
import com.userservice.dto.ProductDetailDTO;
import com.userservice.dto.PageAndSizeDTO;
import com.userservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "ProductClient", url = "http://localhost:8081/api/product-ms")
public interface ProductClient {

    @GetMapping("all")
    List<ProductDTO> getProducts(@Valid PageAndSizeDTO pageAndSizeDTO);

    @PostMapping("purchasing")
    ProductDetailDTO purchaseProduct(@RequestBody OrderBodyDTO orderBodyDTO);
}
