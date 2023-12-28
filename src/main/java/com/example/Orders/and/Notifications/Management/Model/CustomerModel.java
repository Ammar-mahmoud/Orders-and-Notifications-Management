package com.example.Orders.and.Notifications.Management.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor

public class CustomerModel implements Modelable{
    private int  ID;
    private String name;
    private String location;
    private String gender;
    private String language;
    private double balance;
    private String movbileNumber;
    private String email;
    private String preferedCommunicationWay;
}
