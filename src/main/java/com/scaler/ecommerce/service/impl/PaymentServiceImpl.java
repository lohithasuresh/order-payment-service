package com.scaler.ecommerce.service.impl;

import com.scaler.ecommerce.dto.PaymentDTO;
import com.scaler.ecommerce.entity.Order;
import com.scaler.ecommerce.entity.Payment;
import com.scaler.ecommerce.entity.Transaction;
import com.scaler.ecommerce.enums.OrderStatus;
import com.scaler.ecommerce.enums.PaymentStatus;
import com.scaler.ecommerce.enums.TransactionType;
import com.scaler.ecommerce.exception.OrderNotFoundException;
import com.scaler.ecommerce.exception.PaymentAlreadyExistsException;
import com.scaler.ecommerce.repository.OrderRepository;
import com.scaler.ecommerce.repository.PaymentRepository;
import com.scaler.ecommerce.repository.TransactionRepository;
import com.scaler.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public PaymentDTO makePayment(Long orderId, PaymentDTO paymentRequest) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + orderId));

        if (paymentRepository.existsByOrderId(orderId)) {
            throw new PaymentAlreadyExistsException("Payment already exists for this order");
        }

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new PaymentAlreadyExistsException("Order already completed or cancelled");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getAmount());
        payment.setMethod(paymentRequest.getMethod());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDate(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);

        Transaction transaction = new Transaction();
        transaction.setAmount(order.getAmount());
        transaction.setType(TransactionType.DEBIT);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setPayment(savedPayment);
        transactionRepository.save(transaction);

        return mapToDTO(savedPayment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private PaymentDTO mapToDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getStatus(),
                payment.getMethod(),
                payment.getOrder() != null ? payment.getOrder().getId() : null
        );
    }
}
