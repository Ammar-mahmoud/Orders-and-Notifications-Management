package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.CustomerModel;
import com.example.Orders.and.Notifications.Management.Model.Modelable;
import com.example.Orders.and.Notifications.Management.Model.ProductModel;
import com.example.Orders.and.Notifications.Management.Model.ShoppingCartModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class CustomerDB implements Repoable , Observer{
    private final List<CustomerModel> customerDB;

    public void add(CustomerModel modelable) {
        customerDB.add(modelable);
    }

    public void delete(CustomerModel modelable) {
        customerDB.remove(modelable);
    }

    public CustomerModel search(String name) {
        for (CustomerModel customerModel : customerDB){
            if (customerModel.getName().equals(name)){
                return customerModel;
            }
        }
        return null;
    }
    @Override
    public boolean updateBalance(CustomerModel customerModel, double balance){
        if (customerModel.getBalance() >= balance){
            customerModel.setBalance(customerModel.getBalance() - balance);
            return true;
        }
        return false;
    }

    public void addToShoppingCart(CustomerModel customerModel, String productId, int quantity , double productprice){
        ShoppingCartModel shoppingCartModel = customerModel.getShoppingCartModel();
        Map<String , Integer> mm=shoppingCartModel.getProducts();
        mm.put(productId,quantity);
        shoppingCartModel.setProducts(mm);
        shoppingCartModel.setTotalPrice(shoppingCartModel.getTotalPrice() + (productprice * quantity));
    }

    public void updateBalanceaftercancel(CustomerModel customerModel , double balance){
        customerModel.setBalance(customerModel.getBalance()+balance);
    }
}
