package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.Modelable;
import com.example.Orders.and.Notifications.Management.Model.NotificationModel;

import java.util.Queue;

public class NotificationDB implements Repoable{
    private Queue<NotificationModel> notificationQueue;
    private Queue<NotificationModel> notificationDB;

    public void addNotification(NotificationModel notificationmodel) {
        notificationQueue.add(notificationmodel);
        notificationDB.add(notificationmodel);
    }
    public void deleteNotification(){
        notificationQueue.poll();
    }
}
