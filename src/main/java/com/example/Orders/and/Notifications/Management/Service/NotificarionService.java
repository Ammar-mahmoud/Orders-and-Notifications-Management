package com.example.Orders.and.Notifications.Management.Service;

import com.example.Orders.and.Notifications.Management.Model.NotificationModel;
import com.example.Orders.and.Notifications.Management.Model.NotificationTemplateModel;
import com.example.Orders.and.Notifications.Management.Model.StatisticModel;
import com.example.Orders.and.Notifications.Management.Repo.NotificationDB;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificarionService {
    private final NotificationDB notificationDB;

    public NotificarionService(NotificationDB notificationDB) {
        this.notificationDB = notificationDB;
    }

    public  StatisticModel generateLiveStatistics() {
        return notificationDB.generateLiveStatistics();
    }

    public Queue<NotificationModel> getQueue()
    {
        return notificationDB.getNotificationQueue();
    }


}
