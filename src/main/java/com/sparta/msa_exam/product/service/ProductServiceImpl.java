package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductCreateDTO;
import com.sparta.msa_exam.product.dto.ProductListDTO;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "Product Service Impl")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * 상풍 목록 가져오기
     * @return 상품 목록
     */
    @Cacheable(cacheNames = "ProductCache", key = "getMethodName()")
    @Override
    public List<ProductListDTO> getProductList() {

        log.info("Product Service : getProductList");

        List<Product> productList = productRepository.findAll();

        return productList.stream().map(ProductListDTO::new).toList();
    }

    /**
     * 상품 생성
     *
     * @param productCreateDTO
     * @return "상품 생성 완료"
     */
    @Override
    public Product createProduct(ProductCreateDTO productCreateDTO) {

        log.info("Product Service : createProduct");

        Product product = Product.builder()
                .name(productCreateDTO.getName())
                .supply_price(productCreateDTO.getSupply_price())
                .seller(productCreateDTO.getId())
                .build();

        productRepository.save(product);

        return product;
    }
}
