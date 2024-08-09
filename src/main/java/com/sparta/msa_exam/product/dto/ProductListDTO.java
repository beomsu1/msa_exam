package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductListDTO implements Serializable {

    private Long product_id;
    private String name;
    private int supply_price;
    private String seller;

    public ProductListDTO(Product product) {
        this.product_id = product.getProduct_id();
        this.name = product.getName();
        this.supply_price = product.getSupply_price();
        this.seller = product.getSeller();
    }
}
