package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.dto.CreateOrderDTO;
import com.sparta.msa_exam.order.dto.ReadOrderDTO;
import com.sparta.msa_exam.order.dto.UpdateOrderDTO;
import com.sparta.msa_exam.order.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2(topic = "Order Controller")
@RequiredArgsConstructor
public class OrderController {

    @Value("${server.port}")
    private String port;

    private final OrderService orderService;


    // 주문 추가
    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDTO createOrderDTO, @RequestHeader(value = "X-Id", required = true) String id, HttpServletResponse response) {

        log.info("Order Controller : POST CreateOrder");

        response.setHeader("Server-Port", port);
        Long order_id = orderService.createOrder(createOrderDTO);

        return ResponseEntity.ok(order_id + "번으로 주문이 완료되었습니다.");
    }

    // 주문 확인
    @GetMapping("/order/{orderId}")
    public ResponseEntity<ReadOrderDTO> getReadOne(@PathVariable Long orderId, HttpServletResponse response) {

        log.info("Order Controller : GET Read One");

        response.setHeader("Server-Port", port);
        ReadOrderDTO readOrderDTO = orderService.getReadOne(orderId);

        return ResponseEntity.ok(readOrderDTO);
    }

    // 주문에 상품 추가
    @PutMapping("/order/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody UpdateOrderDTO updateOrderDTO, @RequestHeader(value = "X-Id", required = true) String id, HttpServletResponse response) {

        log.info("Order Controller : Update Order");

        response.setHeader("Server-Port", port);
        ReadOrderDTO readOrderDTO = orderService.updateOrder(orderId, updateOrderDTO);

        return ResponseEntity.ok(readOrderDTO);
    }
}
