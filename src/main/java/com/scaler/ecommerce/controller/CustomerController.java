package com.scaler.ecommerce.controller;

import com.scaler.ecommerce.dto.CreateCustomerRequestDTO;
import com.scaler.ecommerce.entity.Customer;
import com.scaler.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public Customer createCustomer(
            @Valid @RequestBody CreateCustomerRequestDTO request
    ) {
        return customerService.createCustomer(request);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }
}
