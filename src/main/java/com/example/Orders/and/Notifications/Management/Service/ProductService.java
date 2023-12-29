package com.example.Orders.and.Notifications.Management.Service;
import com.example.Orders.and.Notifications.Management.Model.ProductModel;
import com.example.Orders.and.Notifications.Management.Repo.ProductDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductDB productDB;

    public ProductService(ProductDB productDB) {
        this.productDB = productDB;
    }

    public List<ProductModel> getProducts()
    {
        return productDB.getAllProducts();
    }
}
