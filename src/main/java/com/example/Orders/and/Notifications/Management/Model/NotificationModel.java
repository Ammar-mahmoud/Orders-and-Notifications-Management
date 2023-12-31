package com.example.Orders.and.Notifications.Management.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
public class NotificationModel implements Modelable{
    private CustomerModel customerModel;
    private String notificationTimeStamp;
    private String subject;
    private String notificationTosent;
    private String language;


}
