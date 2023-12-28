package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.Modelable;
import com.example.Orders.and.Notifications.Management.Model.NotificationTemplateModel;
import com.example.Orders.and.Notifications.Management.Model.ProductModel;

import java.util.List;

public class NotificationTemplateDB implements Repoable{
    public static List<NotificationTemplateModel> notificationTemplates;
    public void add(NotificationTemplateModel notificationTemplateModel) {
        notificationTemplates.add(notificationTemplateModel);
    }

    public void delete(NotificationTemplateModel notificationTemplateModel) {
        notificationTemplates.remove(notificationTemplateModel);
    }

}
