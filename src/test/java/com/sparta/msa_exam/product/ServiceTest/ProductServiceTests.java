package com.sparta.msa_exam.product.ServiceTest;

import com.sparta.msa_exam.product.dto.ProductCreateDTO;
import com.sparta.msa_exam.product.dto.ProductListDTO;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2(topic = "Product Service Tests")
public class ProductServiceTests {
    
    @Autowired
    private ProductService productService;
    
    @Test
    @DisplayName("상품 생성 서비스 테스트")
    public void createProductServiceTest(){

        // Given
        log.info("Create Product Service Test Start");

        String id = "beomsu";

        ProductCreateDTO productCreateDTO = ProductCreateDTO.builder()
                .name("헤드셋")
                .supply_price(80000)
                .id(id)
                .build();

        // When
        Product product = productService.createProduct(productCreateDTO);

        Assertions.assertEquals(productCreateDTO.getName(), product.getName());

        // Then
        log.info("product: " + product.getName() +", " +product.getSupply_price());

        log.info("Create Product Service Test Complete");
    }

    @Test
    @DisplayName("상품 목록 조회 서비스 테스트")
    public void getProductListServiceTest(){

        // Given
        log.info("Get Product List Service Test Start");

        // When
        List<ProductListDTO> productList = productService.getProductList();

        // Then
        log.info(productList.stream().toList());

        log.info("Get Product List Service Test Complete");
    }
}
