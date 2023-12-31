package com.example.Orders.and.Notifications.Management.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SimpleOrder extends OrderModel {
    private double totalPrice;
    ShoppingCartModel shoppingCartModel;
    private String customerName;

    public SimpleOrder(int ID, String customerName) {
        super(ID, false,false);
        this.totalPrice=0.0;
        this.customerName=customerName;
        this.shoppingCartModel=null;
    }


}