package com.example.Orders.and.Notifications.Management.Controller;
import com.example.Orders.and.Notifications.Management.Model.NotificationModel;
import com.example.Orders.and.Notifications.Management.Model.StatisticModel;
import com.example.Orders.and.Notifications.Management.Service.NotificarionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificarionService notificarionService;

    @GetMapping("/Queue")
    public Queue<NotificationModel> getAllProducts() {
        return notificarionService.getQueue();
    }

    @GetMapping("/getLiveStatistics")
    public StatisticModel getLiveStatistics(){
        return notificarionService.generateLiveStatistics();
    }
}
