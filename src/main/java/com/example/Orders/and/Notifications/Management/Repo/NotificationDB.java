package com.example.Orders.and.Notifications.Management.Repo;
import com.example.Orders.and.Notifications.Management.Model.*;
import com.example.Orders.and.Notifications.Management.NotificationComponent.Notification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@Component
public class NotificationDB implements Repoable {
    private final CustomerDB customerDB;
    private final ProductDB productDB;
    private final NotificationTemplateDB notificationTemplateDB;
    private Queue<NotificationModel> notificationQueue;
    private List<NotificationModel> notificationDB;
    private NotificationTemplateModel placeOrderTemplate;
    private NotificationTemplateModel shippingOrderTemplate;

    public NotificationDB(CustomerDB customerDB, ProductDB productDB , NotificationTemplateDB notificationTemplateDB) {
        this.customerDB = customerDB;
        this.productDB = productDB;
        this.notificationTemplateDB=notificationTemplateDB;
        this.notificationQueue = new LinkedList<>();
        this.notificationDB = new ArrayList<>();
        this.placeOrderTemplate = notificationTemplateDB.CreatePlaceOrderTemplete();
        this.shippingOrderTemplate = notificationTemplateDB.CreateShippingOrderTemplete();
    }

    public void addNotification(NotificationModel notificationmodel) {
        notificationQueue.add(notificationmodel);
        notificationDB.add(notificationmodel);
    }



    public void deleteNotification() {
        notificationQueue.poll();
    }

    public void makeNotificationforSimpleOrder(SimpleOrder simpleOrder, int type) {
        if (type == 1) {
            CustomerModel customerModel=customerDB.search(simpleOrder.getCustomerName());
            List<String> productNames = getProductNames(customerModel.getShoppingCartModel());
            NotificationModel model = notificationTemplateDB.fillPlaceOrderTemplate(placeOrderTemplate, simpleOrder.getCustomerName(), productNames);
            addNotification(model);
        } else {
            NotificationModel model = notificationTemplateDB.fillShippingOrderTemplate(shippingOrderTemplate, simpleOrder.getCustomerName(), simpleOrder.getID());
            addNotification(model);
        }
    }

    public void makeNotificationforCompositeOrder(CompoundOrder compoundOrder, int type) {
        if (type == 1) {

            for (SimpleOrder simpleOrder : compoundOrder.getOrders()) {
                CustomerModel customerModel=customerDB.search(simpleOrder.getCustomerName());
                List<String> productNames = getProductNames(customerModel.getShoppingCartModel());
                NotificationModel notificationModel = notificationTemplateDB.fillPlaceOrderTemplate(placeOrderTemplate, simpleOrder.getCustomerName(), productNames);
                addNotification(notificationModel);
            }
        } else {
            for (SimpleOrder simpleOrder : compoundOrder.getOrders()) {
                NotificationModel model = notificationTemplateDB.fillShippingOrderTemplate(shippingOrderTemplate, simpleOrder.getCustomerName(), simpleOrder.getID());
                simpleOrder.setTotalPrice(simpleOrder.getTotalPrice()+40); // 40$ Shipping expenses
                addNotification(model);
            }
        }
    }

    public List<String> getProductNames(ShoppingCartModel shoppingCartModel) {
        List<String> res = new ArrayList<>();
        for (String key : shoppingCartModel.getProducts().keySet()) {
            ProductModel desired_product = productDB.search(key);
            res.add(desired_product.getName());
        }
        return res;
    }

    @Scheduled(fixedRate = 15000)
    public void processNotifications() {
        if (!notificationQueue.isEmpty()) {
            NotificationModel notificationModel = notificationQueue.poll();
            String preferredCommunication = notificationModel.getCustomerModel().getPreferredCommunicationWay();
            Notification notification = new Notification(preferredCommunication);
            notification.execute(notificationModel);
        } else {
            System.out.println("no notification to send");
        }
    }

}