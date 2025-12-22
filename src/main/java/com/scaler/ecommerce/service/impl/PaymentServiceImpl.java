package com.scaler.ecommerce.service.impl;

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

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Payment makePayment(Long orderId, Payment paymentRequest) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + orderId));

        if (paymentRepository.existsByOrderId(orderId)) {
            throw new PaymentAlreadyExistsException("Payment already exists for this order");
        }

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new PaymentAlreadyExistsException("Order already completed or cancelled");
        }

        // Create payment safely
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getAmount()); // 🔐 secure
        payment.setMethod(paymentRequest.getMethod());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentDate(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        // Update order status
        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);

        // Create transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(order.getAmount());
        transaction.setType(TransactionType.DEBIT);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setPayment(savedPayment);

        transactionRepository.save(transaction);

        return savedPayment;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
