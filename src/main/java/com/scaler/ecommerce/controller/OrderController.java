package com.scaler.ecommerce.controller;

import com.scaler.ecommerce.dto.CreateOrderRequestDTO;
import com.scaler.ecommerce.dto.OrderDTO;
import com.scaler.ecommerce.enums.OrderStatus;
import com.scaler.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/customer/{customerId}")
    public OrderDTO createOrderForCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CreateOrderRequestDTO request
    ) {
        return orderService.createOrderForCustomer(customerId, request);
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{orderId}/status")
    public OrderDTO updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status
    ) {
        return orderService.updateOrderStatus(orderId, status);
    }
}
