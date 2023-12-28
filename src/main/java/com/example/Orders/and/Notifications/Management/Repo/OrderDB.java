package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.Modelable;
import com.example.Orders.and.Notifications.Management.Model.OrderModel;
import com.example.Orders.and.Notifications.Management.Model.ProductModel;

import java.util.List;

public class OrderDB implements Repoable{
    public static List<OrderModel> orderDB;
    public void add(OrderModel orderModel) {
        orderDB.add(orderModel);
    }
    public void delete(OrderModel orderModel) {
        orderDB.remove(orderModel);
    }
//    public Modelable search(int id) {
//        for(OrderModel order: orderDB)
//        {
//            if (order.)
//        }
//    }

}
