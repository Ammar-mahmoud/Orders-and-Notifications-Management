package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Component
public class SimpleOrderRepo implements Repoable{
    public final List<SimpleOrder> simpleOrderDB;
    private final NotificationDB notificationDB;
    public void add(SimpleOrder simpleOrder) {
//        System.out.println("before adding ");
        simpleOrderDB.add(simpleOrder);
    }

    public void delete(SimpleOrder simpleOrder) {
        simpleOrderDB.remove(simpleOrder);
    }
    public SimpleOrder search(int orderid){
        for(SimpleOrder simpleOrder:simpleOrderDB){
            if(simpleOrder.getID() == orderid){
                return simpleOrder;
            }
        }
        return null;
    }

    @Scheduled(fixedRate = 5000)
    public void makeShipped() {
        if(!simpleOrderDB.isEmpty()) {
            for (SimpleOrder order: simpleOrderDB)
            {
                if (!order.is_shipped() && System.currentTimeMillis() - order.getOrderdate().getTime() > 60000 ){
                    order.set_shipped(true);
                    order.setTotalPrice(order.getTotalPrice() + 40); // 40 $ Shipping expenses
                    notificationDB.makeNotificationforSimpleOrder(order,2);
                }
            }
        }
    }
}