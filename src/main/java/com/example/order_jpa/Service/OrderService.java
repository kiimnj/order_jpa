package com.example.order_jpa.Service;

import com.example.order_jpa.dto.OrderDto;
import com.example.order_jpa.entity.*;
import com.example.order_jpa.exception.NoEnoughStockException;
import com.example.order_jpa.repository.JPAOrderRepository;
import com.example.order_jpa.repository.JPAProductRepository;
import com.example.order_jpa.repository.JPAUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final JPAOrderRepository orderRepository;
    private final JPAProductRepository productRepository;
    private JPAUserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUserId(Long UserId) {
        return orderRepository.findOrdersByUserId(UserId);
    }

    public void addOrder(OrderDto orderDto) throws NoEnoughStockException {
        User user = userRepository.findById(orderDto.getUserId());
        Product product = productRepository.findById(orderDto.getProductId());
        // 주문상품 생성
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderDto.getOrderQuantity());

        //영속 상태
        // 주문생성
        Order order = Order.createOrder(user, orderProduct);
        orderRepository.save(order);
        //product 재고 감소
//        product.setQuantity(product.getQuantity() - orderDto.getOrderQuantity());
//        product.decreaseQuantity(orderDto.getOrderQuantity);
        productRepository.save(product);
    }

    public void cancelOrder(long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.cancel();
        productRepository.save(order.getOrderProducts().get(0).getProduct());
        orderRepository.save(order);
    }

    public Order getOrderInfo(Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

}
