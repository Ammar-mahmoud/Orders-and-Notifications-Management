package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class OrderDB implements Repoable{
    public static List<SimpleOrder> simpleOrderDB;
    public static List<CompositOrder> compositOrderDB;

    public void add(SimpleOrder simpleOrder) {
        simpleOrderDB.add(simpleOrder);
    }
    public void add(CompositOrder compositOrder) {
        compositOrderDB.add(compositOrder);
    }

    public CompositOrder search(int id)
    {
        for (CompositOrder compositOrder : compositOrderDB){
            if (compositOrder.getID() == id){
                return compositOrder;
            }
        }
        return null;
    }


    public void delete(SimpleOrder simpleOrder) {
        simpleOrderDB.remove(simpleOrder);
    }
    public void delete(CompositOrder compositOrder) {
        compositOrderDB.remove(compositOrder);
    }

}