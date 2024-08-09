package com.sparta.msa_exam.order.ServiceTest;

import com.sparta.msa_exam.order.dto.CreateOrderDTO;
import com.sparta.msa_exam.order.dto.ReadOrderDTO;
import com.sparta.msa_exam.order.dto.UpdateOrderDTO;
import com.sparta.msa_exam.order.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@SpringBootTest
@Log4j2(topic = "Order Service Test")
public class OrderServiceTests {

    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("주문 생성 서비스 테스트")
    @Transactional
    public void createOrderServiceTest() {

        // Given
        log.info("Create Order Service Test Start");

        CreateOrderDTO createOrderDTO = CreateOrderDTO.builder()
                .product_ids(Arrays.asList(1L, 2L))
                .id("beomsu")
                .build();

        // When
        Long order_id = orderService.createOrder(createOrderDTO);

        // Then
        log.info(order_id + "번으로 주문이 완료되었습니다.");

        log.info("Create Order Service Test Complete");

    }

    @Test
    @DisplayName("주문 정보 서비스 테스트")
    @Transactional
    public void getReadOneServiceTest() {

        // Given
        log.info("getReadOne Service Test Start");

        Long order_id = 1L;

        // When
        ReadOrderDTO readOrderDTO = orderService.getReadOne(order_id);

        // Then
        log.info(readOrderDTO);
        log.info("getReadOne Service Test Complete");
    }

    @Test
    @DisplayName("주문 추가 서비스 테스트")
    @Transactional
    public void updateOrderServiceTest() {

        // Given
        log.info("Update Order Service Test Start");

        Long order_id = 4L;
        UpdateOrderDTO updateOrderDTO = UpdateOrderDTO.builder()
                .product_id(3L)
                .id("beomsu")
                .build();

        // When
        ReadOrderDTO readOrderDTO = orderService.updateOrder(order_id, updateOrderDTO);

        // Then
        log.info(readOrderDTO);
        log.info("Update Order Service Test Complete");

    }


}
