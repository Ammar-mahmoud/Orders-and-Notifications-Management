package com.example.Orders.and.Notifications.Management.Model;

import java.util.List;

public class CompositOrder extends OrderModel{
    private List<OrderModel> orders;

    public CompositOrder(int ID, List<OrderModel> orders) {
        super(ID, true);
        this.orders = orders;
    }
}
