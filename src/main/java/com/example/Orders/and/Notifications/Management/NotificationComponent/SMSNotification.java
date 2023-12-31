package com.example.Orders.and.Notifications.Management.NotificationComponent;

import com.example.Orders.and.Notifications.Management.Model.NotificationModel;

public class SMSNotification implements NotifyMethod {
    @Override
    public void Notify(NotificationModel notificationModel) {
        System.out.println("SMS Email Sent." + notificationModel.getCustomerModel().getMobileNumber());
    }
}
