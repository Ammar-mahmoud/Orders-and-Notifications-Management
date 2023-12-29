package com.example.Orders.and.Notifications.Management.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ShoppingCartModel {
    private double totalPrice;
    private Map<String, Integer> Products;
    public ShoppingCartModel() {
        this.totalPrice = 0.0;
        this.Products = new HashMap<>();
    }
}
