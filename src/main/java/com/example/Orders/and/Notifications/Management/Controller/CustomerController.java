package com.example.Orders.and.Notifications.Management.Controller;

import com.example.Orders.and.Notifications.Management.Model.CustomerModel;
import com.example.Orders.and.Notifications.Management.Model.ProductModel;
import com.example.Orders.and.Notifications.Management.Model.Response;
import com.example.Orders.and.Notifications.Management.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/Customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public Response registerCustomer(@RequestBody CustomerModel customer) {
        boolean res = customerService.addCustomer(customer);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Customer Already Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Account created successfully");
        return response;
    }

    @GetMapping ("/login/{name}/{password}")
    public Response login(@PathVariable String name,@PathVariable String password){
        int res = customerService.login(name, password);
        Response response = new Response();
        if (res == -1) {
            response.setStatus(false);
            response.setMessage("Customer Doesn't have an account");
            return response;
        }else if (res == 0){
            response.setStatus(false);
            response.setMessage("Wrong Password!");
        }else {
            response.setStatus(true);
            response.setMessage("Customer login successfully");
        }
        return response;
    }

    @PutMapping ("/addBalance/{name}/{amount}")
    public Response addToBalance(@PathVariable String name,@PathVariable double amount){
        double res = customerService.addToBalance(name,amount);
        Response response = new Response();
        response.setStatus(true);
        response.setMessage("Balance added successfully, balance now: " + res);
        return response;
    }

    @GetMapping("/Shopping/{name}/{ProductSerial}/{amount}")
//    String customerName, String productID, int Quantity
    public Response addToShoppingCart(@PathVariable String name, @PathVariable String ProductSerial, @PathVariable int amount) {
        boolean res = customerService.addToShoppingCart(name, ProductSerial, amount);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Product can not be added to shoppingCart!");
            return response;
        }
        response.setStatus(true);
        response.setMessage("product added to shoppingCart successfully!");
        return response;
    }

    @GetMapping("/getCustomer/{name}")
    public CustomerModel getCustomerDetails(@PathVariable String name)
    {
        return customerService.getCustomerDetails(name);
    }
}
