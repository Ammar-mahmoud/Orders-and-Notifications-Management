package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.Modelable;
import com.example.Orders.and.Notifications.Management.Model.ProductModel;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Component
public class ProductDB implements Repoable {
    private final static List<ProductModel> productDB;
    static {
        productDB = new ArrayList<>();
        productDB.add(new ProductModel("SN001", "book", "Ahmed", "Stationery", 5.0, 10, 94));
        productDB.add(new ProductModel("SN002", "T-shirt", "Mohammed", "cloth", 25.0, 20, 12));
        productDB.add(new ProductModel("SN003", "rice", "Ammar", "food", 50.0, 20, 50));
        productDB.add(new ProductModel("SN004", "pen", "Ahmed", "Stationery", 15.0, 20, 30));
        productDB.add(new ProductModel("SN005", "milk", "Ammar", "food", 40.0, 20, 25));
        productDB.add(new ProductModel("SN006", "jacket", "Mohammed", "cloth", 200.0, 20, 60));

    }
    public void addProduct(ProductModel productModel){productDB.add(productModel);}
    public void deleteProduct(ProductModel  productModel) {
        productDB.remove(productModel);
    }
    public ProductModel search(String id) {
        for(ProductModel model : productDB){
            if(model.getSerialNumber().equals(id)) {
                return model;
            }
        }
        return null;
    }
    public int  countRemainingPartsOfCategory(String category){
        int cnt=0;
        for(ProductModel model : productDB){
            if(model.getCategory().equals(category)) {
                cnt += model.getRemainingCount();
            }
        }
        return cnt;
    }
    public List<ProductModel> getAllProducts()
    {
        return this.productDB;
    }

}
