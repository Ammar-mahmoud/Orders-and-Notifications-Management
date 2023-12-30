package com.example.Orders.and.Notifications.Management.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NotificationTemplateModel implements Modelable{
    private int templateID;
    private String content;
    private String subject;
    private List<String> placeholders;
}
