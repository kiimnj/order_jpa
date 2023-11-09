package com.example.order_jpa.controller;

import com.example.order_jpa.Service.OrderService;
import com.example.order_jpa.Service.ProductService;
import com.example.order_jpa.Service.UserService;
import com.example.order_jpa.dto.OrderDto;
import com.example.order_jpa.entity.Order;
import com.example.order_jpa.exception.NoEnoughStockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/list")
    public String getAllOrders(Model model) {
        List<Order> allOrders = orderService.getAllOrders();
        model.addAttribute("orders", allOrders);
        return "order/orderList";
    }

    @GetMapping("/list/{userId}")
    public String getAllOrdersById(@PathVariable Long userId,
                                   Model model) {
        List<Order> allOrders = orderService.getOrdersByUserId(userId);
        model.addAttribute("orders", allOrders);
        return "order/orderList";
    }

    @PostMapping("/list/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/order/list";
    }

    @GetMapping("/info/{orderId}")
    public String getOrderInfo(@PathVariable Long orderId, Model model) throws NoEnoughStockException  {
        Order order = orderService.getOrderInfo(orderId);
        model.addAttribute("order", order);
        return "order/orderInfo";
    }

    @GetMapping("/add")
    public String addOrder(Model model) throws NoEnoughStockException {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("products", productService.getAllProducts());
        return "order/orderForm";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute OrderDto orderDto) throws NoEnoughStockException {
        orderService.addOrder(orderDto);
        return "redirect:/order/list";
    }
}
