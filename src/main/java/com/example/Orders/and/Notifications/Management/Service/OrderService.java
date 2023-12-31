package com.example.Orders.and.Notifications.Management.Service;

import com.example.Orders.and.Notifications.Management.Model.*;
import com.example.Orders.and.Notifications.Management.Repo.*;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final SimpleOrderRepo simpleOrderDB;
    private final CompoundOrderDB compoundOrderDB;
    private final ProductDB productDB;
    private final CustomerDB customerDB;
    private final NotificationDB notificationDB;

    public OrderService(SimpleOrderRepo simpleOrderDB, CompoundOrderDB compoundOrderDB, ProductDB productDB, CustomerDB customerDB , NotificationDB notificationDB ) {
        this. simpleOrderDB =  simpleOrderDB;
        this.compoundOrderDB = compoundOrderDB;
        this.productDB = productDB;
        this.customerDB = customerDB;
        this.notificationDB = notificationDB;
    }

    public boolean addNewSimpleOrder(SimpleOrder simpleOrder){
        CustomerModel customerModel=customerDB.search(simpleOrder.getCustomerName());
        double total_price = calc_totalPrice(customerModel.getShoppingCartModel());
        simpleOrder.setTotalPrice(total_price);
        simpleOrder.setShoppingCartModel(customerModel.getShoppingCartModel());
        if(updateCustomerBalance(simpleOrder.getCustomerName(),total_price)){
            updateProductsQuantity(customerModel.getShoppingCartModel());
            simpleOrderDB.add(simpleOrder);
            notificationDB.makeNotificationforSimpleOrder(simpleOrder,1);
            return true;
        }
        else {
            return false;
        }
    }
    public boolean addCompositeOrder(CompoundOrder compoundOrder)
    {
        for(SimpleOrder order: compoundOrder.getOrders()){
            CustomerModel customerModel=customerDB.search(order.getCustomerName());
            order.setShoppingCartModel(customerModel.getShoppingCartModel());
            double total_price = calc_totalPrice(customerModel.getShoppingCartModel());
            order.setTotalPrice(total_price);
            if(updateCustomerBalance(customerModel.getName(),total_price)){
                updateProductsQuantity(customerModel.getShoppingCartModel());
            }
            else {
                return false;
            }
        }
        compoundOrderDB.add(compoundOrder);
        notificationDB.makeNotificationforCompositeOrder(compoundOrder,1);
        return true;
    }
    public double calc_totalPrice(ShoppingCartModel shoppingCartModel){
        double total_price=0;
        for (String key : shoppingCartModel.getProducts().keySet()) {
            ProductModel desired_product = productDB.search(key);
            Integer value = shoppingCartModel.getProducts().get(key);
            total_price+=(value*desired_product.getPrice());
        }
        return total_price;
    }

    public void updateProductsQuantity(ShoppingCartModel shoppingCartModel){
        for (String key : shoppingCartModel.getProducts().keySet()) {
            ProductModel desired_product = productDB.search(key);
            int Available_Quantity = desired_product.getQuantity();
            int Available_Remaining = desired_product.getRemainingCount();
            Integer value = shoppingCartModel.getProducts().get(key);
            desired_product.setQuantity(Available_Quantity - value);
//            desired_product.setRemainingCount(Available_Remaining - value);
            productDB.updateRemainingPartsOfCategory(desired_product.getCategory(), Available_Remaining - value);
        }
    }

    public boolean updateCustomerBalance(String name, double total_Price){
        CustomerModel customer = customerDB.search(name);
        return customerDB.updateBalance(customer, total_Price);
    }

    public OrderModel getOrder(int id){
        SimpleOrder order1=simpleOrderDB.search(id);
        CompoundOrder order2= compoundOrderDB.search(id);
        if(order1 != null)return order1;
        if(order2 != null)return order2;
        else return null;
    }
    public int cancelOrder(int id){
        OrderModel orderModel=getOrder(id);
        if(orderModel==null)return -1;          // -1  order doesn't exist
        if(orderModel.is_shipped())return 1;    // 1 order exist but already shipped
        if (simpleOrderDB.search(id)!=null){ // // 2 order accepted to be cancelled
            SimpleOrder simpleOrder=simpleOrderDB.search(id);
            CustomerModel customerModel=customerDB.search(simpleOrder.getCustomerName());
            customerDB.updateBalanceaftercancel(customerModel,simpleOrder.getTotalPrice());
            simpleOrderDB.delete(simpleOrder);
        }
        else {
            CompoundOrder compoundOrder = compoundOrderDB.search(id);
            for(SimpleOrder simpleOrder: compoundOrder.getOrders()){
                CustomerModel customerModel=customerDB.search(simpleOrder.getCustomerName());
                customerDB.updateBalanceaftercancel(customerModel,simpleOrder.getTotalPrice());
            }
            compoundOrderDB.delete(compoundOrder);
        }
        return 2;

    }
}
