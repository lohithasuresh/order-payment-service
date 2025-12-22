package com.scaler.ecommerce.service.impl;

import com.scaler.ecommerce.dto.CreateOrderRequestDTO;
import com.scaler.ecommerce.dto.OrderDTO;
import com.scaler.ecommerce.entity.Customer;
import com.scaler.ecommerce.entity.Order;
import com.scaler.ecommerce.enums.OrderStatus;
import com.scaler.ecommerce.exception.OrderNotFoundException;
import com.scaler.ecommerce.repository.CustomerRepository;
import com.scaler.ecommerce.repository.OrderRepository;
import com.scaler.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    public OrderDTO createOrderForCustomer(Long customerId, CreateOrderRequestDTO request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setAmount(request.getAmount());
        order.setDescription(request.getDescription());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        return mapToDTO(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    @Transactional
    public OrderDTO updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setStatus(status);
        return mapToDTO(orderRepository.save(order));
    }

    private OrderDTO mapToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getAmount(),
                order.getDescription(),
                order.getOrderDate(),
                order.getStatus(),
                order.getCustomer().getId()
        );
    }
}
