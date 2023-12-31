package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.CustomerModel;

public interface Observer {
    public boolean updateBalance(CustomerModel customerModel, double balance);
}
