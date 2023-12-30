package com.example.Orders.and.Notifications.Management.NotificationComponent;

public class NotificationFactory {
    public NotifyMethod factory(String type)
    {
        if (type.equals("Email"))
        {
            return new EmailNotification();
        }
        else {
            return new SMSNotification();
        }
    }
}
