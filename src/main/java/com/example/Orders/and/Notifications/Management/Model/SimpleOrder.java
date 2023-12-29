package com.example.Orders.and.Notifications.Management.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SimpleOrder extends OrderModel{
    private double totalPrice;
    ShoppingCartModel shoppingCartModel;
    private String customerName;
}
