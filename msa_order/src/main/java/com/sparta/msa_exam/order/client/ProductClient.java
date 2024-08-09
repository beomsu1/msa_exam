package com.sparta.msa_exam.order.client;

import com.sparta.msa_exam.order.dto.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductResponseDTO> getProductList();

}
