package com.example.Orders.and.Notifications.Management.Service;

import com.example.Orders.and.Notifications.Management.Model.CustomerModel;
import com.example.Orders.and.Notifications.Management.Model.ProductModel;
import com.example.Orders.and.Notifications.Management.Repo.CustomerDB;
import com.example.Orders.and.Notifications.Management.Repo.ProductDB;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerDB customerDB;
    private final ProductDB productDB;

    public CustomerService(CustomerDB customerDB, ProductDB productDB) {
        this.customerDB = customerDB;
        this.productDB = productDB;
    }

    public boolean addCustomer(CustomerModel customerModel){
        if (customerDB.search(customerModel.getName()) == null) {
            customerDB.add(customerModel);
            return true;
        }
        return false;
    }

    public double addToBalance(String name, double amount)
    {
        CustomerModel c = customerDB.search(name);
        c.setBalance(c.getBalance()+amount);
        return c.getBalance();
    }

    public boolean addToShoppingCart(String customerName, String productID, int Quantity){
        CustomerModel c = customerDB.search(customerName);
        ProductModel p = productDB.search(productID);
        if (p.getQuantity() >= Quantity){
            if(c != null) {
                customerDB.addToShoppingCart(c, productID, Quantity, p.getPrice());
                return true;
            }
            else {
                return false;
            }
        }else {
            return false;
        }
    }
    public CustomerModel getCustomerDetails(String name){
        return customerDB.search(name);
    }

    public int login(String name, String password){ // -1 -> not have account , 0 -> wrong password
        CustomerModel c = customerDB.search(name);  //  1 -> login correct
        if (c == null){
            return -1;
        }
        else {
            if (c.getPassword().equals(password))
                return 1;
            else
                return 0;
        }
    }

}
