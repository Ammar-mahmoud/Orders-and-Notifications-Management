package com.example.Orders.and.Notifications.Management.Controller;
import com.example.Orders.and.Notifications.Management.Model.ProductModel;
import com.example.Orders.and.Notifications.Management.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class productController {
    @Autowired
    private ProductService productService;

    @GetMapping("/allProducts")
    public List<ProductModel> getAllProducts() {
        return productService.getProducts();
    }

}
