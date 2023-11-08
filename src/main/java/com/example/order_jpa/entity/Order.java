package com.example.order_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id") //key는 원래 nullable false
    private Long orderId;
    @ManyToOne(fetch = FetchType.LAZY) //N+1 발생 방지
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "order_date", length = 10)
    private String orderDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 10)
    private OrderStatus orderStatus;
    private long totalPrice;
    private int totalQuantity;
}
