package com.scaler.ecommerce.service;

import com.scaler.ecommerce.entity.Customer;
import com.scaler.ecommerce.entity.Order;
import com.scaler.ecommerce.enums.OrderStatus;
import com.scaler.ecommerce.repository.CustomerRepository;
import com.scaler.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    /**
     * Create order and link it to a customer
     */
    public Order createOrderForCustomer(Long customerId, Order order) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    /**
     * Get all orders
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Update order status
     */
    public Order updateOrderStatus(Long orderId, OrderStatus status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        return orderRepository.save(order);
    }
}
