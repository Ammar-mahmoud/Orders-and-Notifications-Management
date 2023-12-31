package com.example.Orders.and.Notifications.Management.Controller;

import com.example.Orders.and.Notifications.Management.Model.*;
import com.example.Orders.and.Notifications.Management.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/Order")
public class OrderController implements controllable {

    @Autowired
    OrderService orderService;
    @PostMapping("/createOrder")
    public Response CreateOrder(@RequestBody SimpleOrder simpleOrder)
    {
        Response response = new Response();
        boolean res = orderService.addNewSimpleOrder(simpleOrder);
        if (!res) {
            response.setStatus(false);
            response.setMessage("Order cannot be created");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Order created successfully");
        return response;
    }
    @PostMapping("/createCompoundOrder")
    public Response CreateOrder(@RequestBody CompoundOrder compoundOrder)
    {
        Response response = new Response();
        boolean res = orderService.addCompositeOrder(compoundOrder);
        if (!res) {
            response.setStatus(false);
            response.setMessage("Order cannot be created");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Order created successfully");
        return response;
    }

    @GetMapping("/GetOrderInfo/{orderID}")
    public OrderResponse getOrderInfo(@PathVariable int orderID){
        OrderResponse response = new OrderResponse();
        OrderModel res = orderService.getOrder(orderID);
        if (res == null) {
            response.setStatus(false);
            response.setMessage("There is No Order With this ID");
            response.setOrderModel(null);
            return response;
        }
        response.setStatus(true);
        response.setOrderModel(res);
        response.setMessage("This Order information");
        return response;
    }

    @DeleteMapping ("/CancelOrder/{orderID}")
    public Response CancelOrder(@PathVariable int orderID){
        Response response = new Response();
        int res = orderService.cancelOrder(orderID);
        if (res == -1) {
            response.setStatus(false);
            response.setMessage("There is No Order With this ID");
            return response;
        }
        else if(res==1){
            response.setStatus(false);
            response.setMessage("This order is shipped and can't be canceled");
            return response;
        }
        response.setStatus(true);
        response.setMessage("Order Cancelled Successfully");
        return response;
    }


}
