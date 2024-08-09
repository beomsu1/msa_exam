package com.sparta.msa_exam.order.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Long product_id;
    private String name;
    private int supply_price;
    private String seller;
}
