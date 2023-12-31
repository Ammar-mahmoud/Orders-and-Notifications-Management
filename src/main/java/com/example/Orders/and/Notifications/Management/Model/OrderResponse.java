package com.example.Orders.and.Notifications.Management.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {
    private String message;
    private boolean status;
    private OrderModel orderModel;
    public OrderResponse(){}

}
