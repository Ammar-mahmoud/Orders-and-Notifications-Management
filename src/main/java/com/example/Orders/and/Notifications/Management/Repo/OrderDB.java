package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.*;

import java.util.List;

public class OrderDB implements Repoable{
    public static List<SimpleOrder> simpleOrderDB;
    public static List<CompositOrder> compositOrderDB;


    public void add(SimpleOrder simpleOrder) {
        simpleOrderDB.add(simpleOrder);
    }
    public void add(CompositOrder compositOrder) {
        compositOrderDB.add(compositOrder);
    }
    public void delete(SimpleOrder simpleOrder) {
        simpleOrderDB.remove(simpleOrder);
    }
    public void delete(CompositOrder compositOrder) {
        compositOrderDB.remove(compositOrder);
    }
//    public Modelable search(int id) {
//        for(OrderModel order: orderDB)
//        {
//            if (order.)
//        }
//    }

}