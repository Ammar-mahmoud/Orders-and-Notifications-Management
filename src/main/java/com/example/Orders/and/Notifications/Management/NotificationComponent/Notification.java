package com.example.Orders.and.Notifications.Management.NotificationComponent;

import com.example.Orders.and.Notifications.Management.Model.NotificationModel;

public class Notification {

    private NotifyMethod notifyMethod;

    public Notification(String type) {
        NotificationFactory notificationFactory = new NotificationFactory();
        this.notifyMethod = notificationFactory.factory(type);
    }


    public void execute(NotificationModel notificationModel)
    {
        notifyMethod.Notify(notificationModel);
    }

}
