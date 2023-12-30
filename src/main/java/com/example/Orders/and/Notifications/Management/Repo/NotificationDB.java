package com.example.Orders.and.Notifications.Management.Repo;
import com.example.Orders.and.Notifications.Management.Model.*;
import com.example.Orders.and.Notifications.Management.NotificationComponent.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Getter
@Setter
@Component
public class NotificationDB implements Repoable {
    private final CustomerDB customerDB;
    private final ProductDB productDB;
    private Queue<NotificationModel> notificationQueue;
    private Queue<NotificationModel> notificationDB;

    public void addNotification(NotificationModel notificationmodel) {
        notificationQueue.add(notificationmodel);
        notificationDB.add(notificationmodel);
    }
    public void deleteNotification(){
        notificationQueue.poll();
    }

    public void makeNotificationforSimpleOrder(SimpleOrder simpleOrder, int type) {
        if (type == 1) {
            List<String> productNames = getProductNames(simpleOrder.getShoppingCartModel());
            NotificationModel model = fillPlaceOrderTemplate(placeOrderTemplate, simpleOrder.getCustomerName(), productNames);
            addNotification(model);
        } else {
            NotificationModel model = fillShippingOrderTemplate(shippingOrderTemplate, simpleOrder.getCustomerName(), simpleOrder.getID());
            addNotification(model);
        }
    }

    public void makeNotificationforCompositeOrder(CompositOrder compositOrder, int type) {
        if (type == 1) {
            for (SimpleOrder simpleOrder : compositOrder.getOrders()) {
                List<String> productNames = getProductNames(simpleOrder.getShoppingCartModel());
                NotificationModel notificationModel = fillPlaceOrderTemplate(placeOrderTemplate, simpleOrder.getCustomerName(), productNames);
                addNotification(notificationModel);
            }
        } else {
            for (SimpleOrder simpleOrder : compositOrder.getOrders()) {
                NotificationModel model = fillShippingOrderTemplate(shippingOrderTemplate, simpleOrder.getCustomerName(), simpleOrder.getID());
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
            Notification notification = new Notification(notificationModel.getCustomerModel().getPreferredCommunicationWay());
            notification.execute(notificationModel);
        } else {
            System.out.println("no notification to send");
        }
    }

    public StatisticModel generateLiveStatistics() {
        int placeTempelate = 0, shippingTemplate = 0;
        String Tempans = "";
        String customercomm = "";
        for (NotificationModel notificationModel : notificationDB) {
            if (notificationModel.getSubject().equals("Place Order")) {
                placeTempelate++;
            } else {
                shippingTemplate++;
            }
            if (placeTempelate > shippingTemplate) {
                Tempans = "Place Order";
            } else Tempans = "Shipping Order";
        }
        customercomm = getMostNotified();
        return (new StatisticModel(customercomm, Tempans));
    }


    public String getMostNotified() {
        Map<String, Integer> emailCounts = new HashMap<>();
        Map<String, Integer> phoneCounts = new HashMap<>();

        for (NotificationModel notificationModel : notificationDB) {
            String preferredCommunication = notificationModel.getCustomerModel().getPreferredCommunicationWay();

            if ("Email".equals(preferredCommunication)) {
                String email = notificationModel.getCustomerModel().getEmail();
                emailCounts.put(email, emailCounts.getOrDefault(email, 0) + 1);
            } else if ("SMS".equals(preferredCommunication)) {
                String phone = notificationModel.getCustomerModel().getMobileNumber();
                phoneCounts.put(phone, phoneCounts.getOrDefault(phone, 0) + 1);
            }
        }

        Entry<String, Integer> mostNotifiedEmail = findMostNotified(emailCounts);
        Entry<String, Integer> mostNotifiedPhone = findMostNotified(phoneCounts);
        if (mostNotifiedEmail != null && mostNotifiedPhone != null) {
            if (mostNotifiedEmail.getValue() > mostNotifiedPhone.getValue()) {
                return mostNotifiedEmail.getKey();
            } else if (mostNotifiedEmail.getValue() < mostNotifiedPhone.getValue()) {
                return mostNotifiedPhone.getKey();
            } else {
                return (mostNotifiedEmail.getKey() + " and " + mostNotifiedPhone.getKey());
            }
        }
        else if(mostNotifiedEmail == null){
            return mostNotifiedPhone.getKey();
        }else if( mostNotifiedPhone== null) {
            return mostNotifiedEmail.getKey();
        }
        return null;
    }

    private Entry<String, Integer> findMostNotified(Map<String, Integer> counts) {
        String mostNotified = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostNotified = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        if (mostNotified != null) {
            System.out.println("fe 7aga bt return");
            return new AbstractMap.SimpleEntry<>(mostNotified, maxCount);
        } else {
            System.out.println("most nofiied is null in find most notified"+ mostNotified );
            return null;
        }
    }
}