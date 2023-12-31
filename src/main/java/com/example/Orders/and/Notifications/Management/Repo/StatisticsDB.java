package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.NotificationModel;
import com.example.Orders.and.Notifications.Management.Model.StatisticModel;
import com.example.Orders.and.Notifications.Management.Repo.NotificationDB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@Component
public class StatisticsDB implements Repoable{
    private final NotificationDB notificationDB;
    public StatisticsDB(NotificationDB notificationDB){
        this.notificationDB=notificationDB;
    }
    public StatisticModel generateLiveStatistics() {
        int placeTempelate = 0, shippingTemplate = 0;
        String Tempans = "";
        String customercomm = "";
        for (NotificationModel notificationModel : notificationDB.getNotificationDB()) {
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

        for (NotificationModel notificationModel : notificationDB.getNotificationDB()) {
            String preferredCommunication = notificationModel.getCustomerModel().getPreferredCommunicationWay();

            if ("Email".equals(preferredCommunication)) {
                String email = notificationModel.getCustomerModel().getEmail();
                emailCounts.put(email, emailCounts.getOrDefault(email, 0) + 1);
            } else if ("SMS".equals(preferredCommunication)) {
                String phone = notificationModel.getCustomerModel().getMobileNumber();
                phoneCounts.put(phone, phoneCounts.getOrDefault(phone, 0) + 1);
            }
        }

        Map.Entry<String, Integer> mostNotifiedEmail = findMostNotified(emailCounts);
        Map.Entry<String, Integer> mostNotifiedPhone = findMostNotified(phoneCounts);
        if (mostNotifiedEmail != null && mostNotifiedPhone != null) {
            if (mostNotifiedEmail.getValue() > mostNotifiedPhone.getValue()) {
                return mostNotifiedEmail.getKey();
            } else if (mostNotifiedEmail.getValue() < mostNotifiedPhone.getValue()) {
                return mostNotifiedPhone.getKey();
            } else {
                return (mostNotifiedEmail.getKey() + " and " + mostNotifiedPhone.getKey());
            }
        }
        else if(mostNotifiedEmail == null && mostNotifiedPhone!= null){
            return mostNotifiedPhone.getKey();
        }else if( mostNotifiedPhone== null && mostNotifiedEmail!=null) {
            return mostNotifiedEmail.getKey();
        }
        return null;
    }

    private Map.Entry<String, Integer> findMostNotified(Map<String, Integer> counts) {
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
