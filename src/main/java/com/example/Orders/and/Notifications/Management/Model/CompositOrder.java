package com.example.Orders.and.Notifications.Management.Model;

import java.util.List;

public class CompositOrder extends OrderModel{
    private List<SimpleOrder> orders;

    public CompositOrder() {
        super(0, true,false);
        this.orders = new ArrayList<>();
    }

    public CompositOrder(int ID, List<SimpleOrder> orders) {
        super(ID, true,false);
        this.orders = orders;
    }
}
