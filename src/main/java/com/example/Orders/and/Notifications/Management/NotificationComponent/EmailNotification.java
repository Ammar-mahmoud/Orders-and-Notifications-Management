package com.example.Orders.and.Notifications.Management.NotificationComponent;

import com.example.Orders.and.Notifications.Management.Model.NotificationModel;

//        Dear {x} , your booking of the {y} is confirmed. thanks for using our store :)
public class EmailNotification implements NotifyMethod{

    @Override
    public void Notify(NotificationModel notificationModel) {
        System.out.println("Sending email to : "+notificationModel.getCustomerModel().getEmail());
    }
}
