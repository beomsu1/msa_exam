package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductCreateDTO;
import com.sparta.msa_exam.product.dto.ProductListDTO;
import com.sparta.msa_exam.product.entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProductService {

    // 상품 목록
    public List<ProductListDTO> getProductList();

    // 상품 생성
    public Product createProduct(ProductCreateDTO productCreateDTO);


}
