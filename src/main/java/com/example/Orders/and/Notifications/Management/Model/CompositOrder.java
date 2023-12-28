package com.example.Orders.and.Notifications.Management.Model;

import java.util.List;

public class CompositOrder implements OrderModel{
    private List<OrderModel> orders;
}
