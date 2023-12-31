package com.example.Orders.and.Notifications.Management.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CompoundOrder extends OrderModel {
    private List<SimpleOrder> orders;


    public CompoundOrder(int ID, List<SimpleOrder> orders) {
        super(ID, true,false);
        this.orders = orders;
    }
}
