package com.example.Orders.and.Notifications.Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrdersAndNotificationsManagementApplication {


	public static void main(String[] args) {
		SpringApplication.run(OrdersAndNotificationsManagementApplication.class, args);
	}

}
