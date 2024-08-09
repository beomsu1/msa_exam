package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.dto.CreateOrderDTO;
import com.sparta.msa_exam.order.dto.ReadOrderDTO;
import com.sparta.msa_exam.order.dto.ProductResponseDTO;
import com.sparta.msa_exam.order.dto.UpdateOrderDTO;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProductsList;
import com.sparta.msa_exam.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "OrderService")
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    /**
     * 상품 조회
     * @return List<ProductResponseDTO>
     */
    public List<ProductResponseDTO> getProduct() {
        return productClient.getProductList();
    }

    /**
     * 주문 생성
     * @param createOrderDTO
     */
    @Override
    public Long createOrder(CreateOrderDTO createOrderDTO) {

        // 상품 목록
        List<ProductResponseDTO> productList = getProduct();

        // 주문할 상품 목록
        List<OrderProductsList> orderProductsList = new ArrayList<>();

        // 주문 하려는 상품이 상품 목록에 있다면 추가
        for (Long productId : createOrderDTO.getProduct_ids()) {
            boolean productExists = false; // 상품 존재 여부

            for (ProductResponseDTO productResponseDTO : productList) {
                if (productId.equals(productResponseDTO.getProduct_id())) {
                    orderProductsList.add(OrderProductsList.builder().product_id(productId).build());

                    log.info("현재 주문 상품 ID 목록: " + orderProductsList.stream()
                            .map(OrderProductsList::getProduct_id).toList());
                    productExists = true;
                    break;

                }
            }
            if (!productExists) {
                throw new IllegalArgumentException(productId + "번 상품이 존재하지 않습니다.");
            }
        }

        Order order = Order.builder()
                .name(createOrderDTO.getId())
                .product_ids(orderProductsList)
                .build();

        // order 주입
        for (OrderProductsList orderProductList : orderProductsList) {
            orderProductList.setOrder(order);
        }

        orderRepository.save(order);

        return order.getOrder_id();
    }

    /**
     * 주문 조회
     * @param orderId
     * @return ReadOrderDTO
     */
    @Override
    // cacheNames: 적용할 캐시 규칙을 지정하기 위한 이름, 이 메서드로 인해서 만들어질 캐시를 지칭하는 이름
    // key: 캐시 데이터를 구분하기 위해 활용하는 값
    // args[0]: getReadOne 메서드의 첫 번째 인자인 orderId
    @Cacheable(cacheNames = "OrderCache", key = "args[0]")
    public ReadOrderDTO getReadOne(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

        return toDTO(order);
    }


    /**
     * 주문 수정
     * @param order_id 
     * @param updateOrderDTO
     * @return
     */
    @Override
    public ReadOrderDTO updateOrder(Long order_id, UpdateOrderDTO updateOrderDTO) {

        Order order = orderRepository.findById(order_id).orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        // 주문 추가할 상품
        OrderProductsList orderProductsList = OrderProductsList.builder()
                .product_id(updateOrderDTO.getProduct_id())
                .build();

        order.addProduct(orderProductsList);
        orderRepository.save(order);

        return toDTO(order);
    }

    // Entity -> DTO
    public ReadOrderDTO toDTO(Order order) {
        return ReadOrderDTO.builder()
                .order_id(order.getOrder_id())
                .product_ids(order.getProduct_ids().stream().map(OrderProductsList::getProduct_id).toList())
                .build();
    }
}
