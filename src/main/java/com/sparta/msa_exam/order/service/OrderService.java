package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.dto.CreateOrderDTO;
import com.sparta.msa_exam.order.dto.ReadOrderDTO;
import com.sparta.msa_exam.order.dto.UpdateOrderDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderService {

    // 주문 추가
    Long createOrder(CreateOrderDTO createOrderDTO);

    // 주문 수정
    ReadOrderDTO updateOrder(Long order_id, UpdateOrderDTO updateOrderDTO);

    // 주문 조회
    ReadOrderDTO getReadOne(Long orderId);

}
