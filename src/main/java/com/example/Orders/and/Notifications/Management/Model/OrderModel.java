package com.example.Orders.and.Notifications.Management.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class OrderModel implements Modelable{
    protected int ID;
    protected boolean is_Composit;
    protected boolean is_shipped;
    protected Date orderdate;

    public OrderModel(int ID , boolean is_Composit , boolean is_shipped ){
        this.ID=ID;
        this.is_Composit=is_Composit;
        this.is_shipped=is_shipped;
        this.orderdate = new Date();
    }


}
