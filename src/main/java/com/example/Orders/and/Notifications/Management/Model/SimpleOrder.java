package com.example.Orders.and.Notifications.Management.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SimpleOrder implements OrderModel{
    private int ID;
    private double totalPrice;
    private List<ProductModel> orderProducts;
    private CustomerModel customer;
}
