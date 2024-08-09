package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.dto.ProductCreateDTO;
import com.sparta.msa_exam.product.dto.ProductListDTO;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2(topic = "ProductController")
public class ProductController {

    @Value("${server.port}")
    private String port;

    private final ProductService productService;


    // 상품 등록
    @PostMapping("/products")
    public ResponseEntity<?> postCreateProduct(@RequestBody ProductCreateDTO productCreateDTO,
                                               @RequestHeader(value = "X-Id", required = true) String id,
                                               HttpServletResponse response){

        log.info("Product Controller : Post Create Product");

        Product product = productService.createProduct(productCreateDTO);
        response.setHeader("Server-Port", port);

        return ResponseEntity.ok(product.getName() + " 상품 등록이 완료되었습니다.");
    }

    // 상품 목록 조회
    @GetMapping("products")
    public List<ProductListDTO> getProductList(HttpServletResponse response){

        log.info("Product Controller : Get Product List");

        response.setHeader("Server-Port", port);

        return productService.getProductList();
    }

}
