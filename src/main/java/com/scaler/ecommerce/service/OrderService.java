package com.scaler.ecommerce.service;

import com.scaler.ecommerce.dto.CreateOrderRequestDTO;
import com.scaler.ecommerce.dto.OrderDTO;
import com.scaler.ecommerce.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderDTO createOrderForCustomer(Long customerId, CreateOrderRequestDTO request);

    List<OrderDTO> getAllOrders();

    OrderDTO updateOrderStatus(Long orderId, OrderStatus status);
}
