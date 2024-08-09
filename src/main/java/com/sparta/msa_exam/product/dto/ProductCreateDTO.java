package com.sparta.msa_exam.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreateDTO {

    private String id;
    private String name;
    private int supply_price;
}
