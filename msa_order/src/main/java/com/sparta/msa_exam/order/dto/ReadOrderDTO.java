package com.sparta.msa_exam.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadOrderDTO implements Serializable {

    private Long order_id;

    @Builder.Default
    private List<Long> product_ids = new ArrayList<>();
}
