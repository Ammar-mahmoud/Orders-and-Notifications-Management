package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Component
public class NotificationTemplateDB implements Repoable{
    private final CustomerDB customerDB;
    public static List<NotificationTemplateModel> notificationTemplates;
    public void add(NotificationTemplateModel notificationTemplateModel) {
        notificationTemplates.add(notificationTemplateModel);
    }

    public void delete(NotificationTemplateModel notificationTemplateModel) {
        notificationTemplates.remove(notificationTemplateModel);
    }
    public NotificationTemplateModel CreatePlaceOrderTemplete() {

        String content = "Dear {Customer} , your booking of the {Products} is confirmed. thanks for using our store :)";
        String subject = "Place Order";
        List<String> placeholders = List.of("name", "Products");
        NotificationTemplateModel temp = new NotificationTemplateModel(1, content, subject, placeholders);
        return temp;
    }

    public NotificationTemplateModel CreateShippingOrderTemplete() {
        String content = "Dear {customer}, your order with ID {orderID} has been shipped. Thanks for choosing our store!";
        String subject = "Shipping Order";
        List<String> placeholders = List.of("name", "orderID");
        NotificationTemplateModel temp = new NotificationTemplateModel(2, content, subject, placeholders);
        return temp;
    }

    public NotificationModel fillPlaceOrderTemplate(NotificationTemplateModel temp, String name, List<String> productNames) {
        String toSent = temp.getContent();
        String finalStr = replacePlaceholders(toSent, "{Customer}", name);
        String productsString = String.join(", ", productNames);
        finalStr = replacePlaceholders(finalStr, "{Products}", productsString);
        CustomerModel customerModel = customerDB.search(name);
        LocalDate currentDate = LocalDate.now();
        String dateString = currentDate.toString();
        NotificationModel model = new NotificationModel(customerModel, dateString, temp.getSubject(), finalStr,customerModel.getLanguage());
        return model;
    }

    public NotificationModel fillShippingOrderTemplate(NotificationTemplateModel temp, String name, int orderID) {
        String toSent = temp.getContent();
        String finalStr = replacePlaceholders(toSent, "{customer}", name);
        finalStr = replacePlaceholders(finalStr, "{orderID}", String.valueOf(orderID));
        CustomerModel customerModel = customerDB.search(name);
        LocalDate currentDate = LocalDate.now();
        String dateString = currentDate.toString();
        NotificationModel model = new NotificationModel(customerModel, dateString, temp.getSubject(), finalStr, customerModel.getLanguage());
        return model;
    }

    private static String replacePlaceholders(String input, String placeholder, String value) {
        return input.replace(placeholder, value);
    }
}
