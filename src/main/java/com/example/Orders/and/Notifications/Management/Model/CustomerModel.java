package com.example.Orders.and.Notifications.Management.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor

public class CustomerModel implements Modelable{
    private String name; // unique
    private String location;
    private String gender;
    private String language;
    private double balance;
    private String mobileNumber;
    private String email;
    private String password;
    private String preferredCommunicationWay;

}
