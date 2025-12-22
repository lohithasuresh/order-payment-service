package com.scaler.ecommerce.service;

import com.scaler.ecommerce.dto.OrderDTO;
import com.scaler.ecommerce.entity.Order;
import com.scaler.ecommerce.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderDTO createOrderForCustomer(Long customerId, Order order);
    List<OrderDTO> getAllOrders();
    OrderDTO updateOrderStatus(Long orderId, OrderStatus status);
}
