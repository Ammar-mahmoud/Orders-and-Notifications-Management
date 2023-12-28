package com.example.Orders.and.Notifications.Management.Repo;

import com.example.Orders.and.Notifications.Management.Model.Modelable;
import com.example.Orders.and.Notifications.Management.Model.ProductModel;
import org.springframework.boot.Banner;

import java.util.List;

public class ProductDB implements Repoable {
    public static List<ProductModel> productDB;
    public void addProduct(ProductModel productModel){productDB.add(productModel);}
    public void deleteProduct(ProductModel  productModel) {
        productDB.remove(productModel);
    }
    public Modelable search(String id) {
        for(ProductModel model : productDB){
            if(model.getSerialNumber().equals(id)) {
                return model;
            }
        }
        return null;
    }
    public int  countRemaningPartsOfCategory(String category){
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
