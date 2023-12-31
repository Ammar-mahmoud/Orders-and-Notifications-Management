package com.example.Orders.and.Notifications.Management.Service;

import com.example.Orders.and.Notifications.Management.Model.NotificationModel;
import com.example.Orders.and.Notifications.Management.Model.NotificationTemplateModel;
import com.example.Orders.and.Notifications.Management.Model.StatisticModel;
import com.example.Orders.and.Notifications.Management.Repo.NotificationDB;
import com.example.Orders.and.Notifications.Management.Repo.StatisticsDB;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificarionService {
    private final NotificationDB notificationDB;
    private final StatisticsDB statisticsDB;

    public NotificarionService(NotificationDB notificationDB, StatisticsDB statisticsDB) {
        this.notificationDB = notificationDB;
        this.statisticsDB=statisticsDB;
    }

    public  StatisticModel generateLiveStatistics() {
        return statisticsDB.generateLiveStatistics();
    }

    public Queue<NotificationModel> getQueue()
    {
        return notificationDB.getNotificationQueue();
    }


}