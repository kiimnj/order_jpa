package com.example.order_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id") //key는 원래 nullable false
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY) //N+1 발생 방지
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //N+1
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(name = "order_date", length = 10)
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 10)
    private OrderStatus orderStatus;
    private long totalPrice;
    private int totalQuantity;

    public void cancel(){
        this.setOrderStatus(OrderStatus.CANCELLED);
        for (OrderProduct orderProduct : this.orderProducts) {
            orderProduct.cancelOrderProduct();
        }
    }
    public void addOrderProduct(OrderProduct orderProduct){
        orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
    }
    public static Order createOrder(User user, OrderProduct... orderProducts) {
        return null;
        //오더서비스 addOrder에서 옮겨와서 수정
        //order 생성
        long totalPrice = 0L;
        int totalQuantity = 0;

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.CREATED);
        for(OrderProduct orderProduct : orderProducts){
            totalPrice += orderProduct.getOrderPrice();
            totalQuantity += orderProduct.getOrderQuantity();
            order.addOrderProduct(orderProduct);
        }
        order.setTotalPrice(totalPrice);
        order.setTotalQuantity(totalQuantity);

        return order;

    }
}
