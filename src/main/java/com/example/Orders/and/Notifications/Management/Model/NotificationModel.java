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
    private int notificationID;
    private String notificationTimeStamp;
    private boolean sent;
    private Map<String, String> placeholder;
}
