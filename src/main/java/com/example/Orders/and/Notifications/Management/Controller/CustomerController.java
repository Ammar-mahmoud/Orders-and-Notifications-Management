package com.example.Orders.and.Notifications.Management.Controller;

import com.example.Orders.and.Notifications.Management.Model.CustomerModel;
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
}
