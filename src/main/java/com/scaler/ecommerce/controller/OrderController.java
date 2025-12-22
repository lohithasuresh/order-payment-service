package com.scaler.ecommerce.controller;

import com.scaler.ecommerce.entity.Order;
import com.scaler.ecommerce.dto.OrderDTO;
import com.scaler.ecommerce.enums.OrderStatus;
import com.scaler.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController
{

    private final OrderService orderService;

    /**
     * Create an order for a specific customer
     */
    @PostMapping("/customer/{customerId}")
    public Order createOrderForCustomer(
            @PathVariable Long customerId,
            @RequestBody Order order) {

        return orderService.createOrderForCustomer(customerId, order);
    }

    /**
     * Get all orders
     */
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders().stream()
                .map(order -> new OrderDTO(
                        order.getId(),
                        order.getAmount(),
                        order.getDescription(),
                        order.getOrderDate(),
                        order.getStatus(),
                        order.getCustomer() != null ? order.getCustomer().getId() : null
                )).toList();
    }

    /**
     * Update order status
     */
    @PutMapping("/{orderId}/status")
    public Order updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status) {

        return orderService.updateOrderStatus(orderId, status);
    }
}
