# **Order-Payment Service (Spring Boot)**

This is a **Java Spring Boot backend service** that manages Customers, Orders, Payments, and Transactions.  
The project demonstrates clean layered architecture, DTO-based APIs, JPA relationships, validation, and Swagger (OpenAPI) documentation.
---
## 🚀 Features
- Customer management (Create & Fetch customers)
- Order management with status tracking
- Payment processing linked to orders
- Automatic transaction creation on successful payment
- DTO-based request/response handling
- Bean validation using Jakarta Validation
- Swagger UI for API testing
- H2 database with file-based persistence
- Centralized exception handling
- Clean and meaningful Git commit history
---
## 🛠 Tech Stack
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA (Hibernate)**
- **H2 Database**
- **Swagger / OpenAPI 3 (springdoc)**
- **Maven**
- **Lombok**
---
## 🎯 Design & Best Practices
### ✅ DTO Pattern
- Entities are **not exposed directly**
- Separate DTOs for requests and responses
- Improves security, readability, and maintainability

### ✅ Validation
- Bean validation using `@NotNull`, `@NotBlank`, `@Positive`, etc.
- Enabled via `@Valid` in controllers
- Validation errors handled centrally

### ✅ JPA Relationships
- `Order → Customer` mapped using `@ManyToOne`
- `Payment → Order` mapped using `@OneToOne`
- `Transaction → Payment` mapped using `@ManyToOne`
- Proper foreign key constraints via `@JoinColumn`

### ✅ Exception Handling
- Centralized using `@ControllerAdvice`
- Custom business exceptions:
    - `OrderNotFoundException`
    - `PaymentAlreadyExistsException`
- Structured error responses with timestamps and status codes

---

## 📂 Project Structure
```
src/main/java/com/scaler/ecommerce
├── config/
│ └── OpenApiConfig.java
├── controller/
│ ├── CustomerController.java
│ ├── OrderController.java
│ └── PaymentController.java
├── dto/
│ ├── CreateCustomerRequestDTO.java
│ ├── CreateOrderRequestDTO.java
│ ├── CreatePaymentRequestDTO.java
│ ├── CustomerDTO.java
│ ├── OrderDTO.java
│ └── PaymentDTO.java
├── entity/
│ ├── Customer.java
│ ├── Order.java
│ ├── Payment.java
│ └── Transaction.java
├── enums/
│ ├── OrderStatus.java
│ ├── PaymentMethod.java
│ ├── PaymentStatus.java
│ └── TransactionType.java
├── exception/
│ ├── GlobalExceptionHandler.java
│ ├── OrderNotFoundException.java
│ └── PaymentAlreadyExistsException.java
├── repository/
│ ├── CustomerRepository.java
│ ├── OrderRepository.java
│ ├── PaymentRepository.java
│ └── TransactionRepository.java
├── service/
│ └── impl/
│   ├── OrderServiceImpl.java
│   └── PaymentServiceImpl.java
│ ├── CustomerService.java
│ ├── OrderService.java
│ └── PaymentService.java
└── OrderPaymentServiceApplication.java
```
---
## 🚀 How to Run Locally

### 1️⃣ Clone Repository
```
git clone https://github.com/lohithasuresh/order-payment-service.git
cd order-payment-service
```
### 2️⃣ Run Application
```
mvn spring-boot:run
```
---
## 🌐 Access URLs
| Tool | URL |
|------|-----|
| Swagger UI | http://localhost:8080/swagger-ui.html |
| OpenAPI Docs | http://localhost:8080/v3/api-docs |
| H2 Console | http://localhost:8080/h2-console |
---
## 🗄 H2 Database Configuration

**JDBC URL**
```jdbc:h2:file:./data/ecomdb```

**Username** ```sa```

**Password**```(empty)```

📌 **Note:** 
The database is file-based, so data **persists across application restarts**.
---
## 📌 API Endpoints
### 👤 Customer APIs
| Method | Endpoint | Description |
|------|----------|-------------|
| POST | `/customers` | Create customer |
| GET | `/customers` | Get all customers |
| GET | `/customers/{id}` | Get customer by ID |
---
### 📦 Order APIs
| Method | Endpoint | Description |
|------|----------|-------------|
| POST | `/orders/customer/{customerId}` | Create order for customer |
| GET | `/orders` | Get all orders |
| PUT | `/orders/{orderId}/status` | Update order status |
---
### 💳 Payment APIs
| Method | Endpoint | Description |
|------|----------|-------------|
| POST | `/payments/{orderId}` | Make payment for an order |
| GET | `/payments` | Get all payments |
---
## 📊 Sample Data
Sample records are loaded using `data.sql`.
- Includes predefined customers, orders, and payments
- Enables immediate API testing via Swagger UI
---
## 🧪 API Testing
All APIs can be tested directly using **Swagger UI**.  
No external tools like **Postman** are required.



