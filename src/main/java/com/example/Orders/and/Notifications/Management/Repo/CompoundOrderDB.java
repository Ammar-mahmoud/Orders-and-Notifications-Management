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
public class CompoundOrderDB implements Repoable{
    public final List<CompoundOrder> compoundOrderDB;
    private final NotificationDB notificationDB;

    public void add(CompoundOrder compoundOrder) {
        compoundOrderDB.add(compoundOrder);
    }

    public CompoundOrder search(int id)
    {
        for (CompoundOrder compoundOrder : compoundOrderDB){
            if (compoundOrder.getID() == id){
                return compoundOrder;
            }
        }
        return null;
    }

    public void delete(CompoundOrder compoundOrder) {
        compoundOrderDB.remove(compoundOrder);
    }
    @Scheduled(fixedRate = 5000)
    public void makeShipped() {
        if(!compoundOrderDB.isEmpty()) {
            for (CompoundOrder order: compoundOrderDB)
            {
                if (!order.is_shipped() && System.currentTimeMillis() - order.getOrderdate().getTime() > 40000) {
                    order.set_shipped(true);
                    notificationDB.makeNotificationforCompositeOrder(order, 2);
                    break;
                }
            }
        }
    }

}