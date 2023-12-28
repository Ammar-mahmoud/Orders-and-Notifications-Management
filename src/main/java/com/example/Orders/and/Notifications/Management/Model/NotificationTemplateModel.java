package com.example.Orders.and.Notifications.Management.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NotificationTemplateModel implements Modelable{
    private String content;
    private int templateID;
    private String subject;
    private List<String> placeholders;
    private List<String> languages;
}
