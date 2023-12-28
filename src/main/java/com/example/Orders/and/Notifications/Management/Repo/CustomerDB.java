package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.CustomerModel;
import com.example.Orders.and.Notifications.Management.Model.Modelable;
import com.example.Orders.and.Notifications.Management.Model.ProductModel;
import lombok.Getter;

import java.util.List;

public class CustomerDB implements Repoable {
    public static List<CustomerModel> customerDB;

    public void add(CustomerModel modelable) {
        customerDB.add(modelable);
    }

    public void delete(CustomerModel modelable) {
        customerDB.remove(modelable);
    }

    public Modelable search(int id) {
        for (CustomerModel customerModel : customerDB){
            if (customerModel.getID() == id){
                return customerModel;
            }
        }
        return null;
    }

    public boolean updateBalance(CustomerModel customerModel, double balance){
        if (customerModel.getBalance() >= balance){
            customerModel.setBalance(customerModel.getBalance() - balance);
            return true;
        }
        return false;
    }
}
