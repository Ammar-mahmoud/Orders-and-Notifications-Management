# Orders and Notifications Management Module

## Table of Contents
- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [How to Run](#how-to-run)
- [APIs and Endpoints](#apis-and-endpoints)
  - [User Operations](#user-operations)
  - [Balance Operations](#balance-operations)
  - [Product Operations](#product-operations)
  - [Shopping Cart Operations](#shopping-cart-operations)
  - [Order Operations](#order-operations)
  - [Shipping Operations](#shipping-operations)
  - [Notification Operations](#notification-operations)
  - [Bonus Operations](#bonus-operations)
- [Class Diagram](#class-diagram)
- [Contributing](#contributing)

## Overview
This module provides features for online purchase orders and management of message notifications based on various actions taken during order-related operations.

## Technologies Used
- Java Programming Language
- IntelliJ IDE for Java EE or VS Code with Spring Boot
- Spring Boot (with Spring Initializer)
- Application Server: Tomcat
- In-memory database (built-in technology or lists)
- Postman for testing the API

## How to Run
1. Clone the Git repository.
2. Open the project in IntelliJ IDE or VS Code.
3. Ensure you have the correct Java version (Java JDK 11.0.16.1).
4. Build and run the project.
5. Test the API using Postman.

## APIs and Endpoints
### User Operations
1. `POST /api/customer/register`: Register a new user.
   - Request Body: `CustomerModel`
   - Response Body: `Response`

2. `GET /api/customer/login/{name}/{password}`: Login user.
   - Path Variables: `name`, `password`
   - Response Body: `Response`

3. `PUT /api/customer/addBalance/{name}/{amount}`: Add balance to a user account.
   - Path Variables: `name`, `amount`
   - Response Body: `Response`

### Product Operations
4. `GET /api/product/allProducts`: Get all products.

### Shopping Cart Operations
5. `GET /api/customer/addToShoppingCart/{name}/{ProductSerial}/{amount}`: Add products to the shopping cart.
   - Path Variables: `name`, `ProductSerial`, `amount`
   - Response Body: `Response`

### Order Operations
6. `POST /api/customer/order/simple`: Create a simple order.
   - Request Body: `OrderModel`
   - Response Body: `Response`

7. `POST /api/customer/order/compound`: Create a compound order.
   - Request Body: `CompoundOrderModel`
   - Response Body: `Response`

8. `GET /api/customer/order/details/{orderId}`: Get details of an order.
   - Path Variable: `orderId`
   - Response Body: `OrderDetailsModel`

9. `DELETE /api/customer/order/cancel/{orderId}`: Cancel an order.
   - Path Variable: `orderId`
   - Response Body: `Response`

### Shipping Operations
10. `POST /api/customer/shipping/ship/{orderId}`: Ship an order.
   - Path Variable: `orderId`
   - Response Body: `Response`

### Notification Operations
11. `POST /api/notification/create`: Create a notification.
    - Request Body: `NotificationModel`
    - Response Body: `Response`

12. `GET /api/notification/queue`: Get the notification queue.
    - Response Body: `Queue<NotificationModel>`

### Bonus Operations
13. `POST /api/customer/order/cancel-auto/{orderId}`: Cancel an order automatically.
    - Path Variable: `orderId`
    - Response Body: `Response`

14. `GET /api/notification/liveStatistics`: Get live statistics.
    - Response Body: `StatisticModel`

### Class Diagram
<html>
  <img src="Ordering and Notification system UML.png" alt="System Architecture Diagram">
</html>
 
## Contributing
1. Fork the repository.
2. Create a new branch for your feature.
3. Make your changes and commit them.
4. Push to the branch.
5. Submit a pull request.
