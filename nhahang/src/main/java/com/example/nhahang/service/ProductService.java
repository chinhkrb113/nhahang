package com.example.nhahang.service;

import java.util.List;
import java.util.Optional;

import com.example.nhahang.entity.Product;
import com.example.nhahang.entity.User;
import com.example.nhahang.model.request.CreateProductRequest;

public interface ProductService {
    
    List<Product> getList();

    List<Product> getListNewst(int number);

    List<Product> getListByPrice();

    List<Product> findRelatedProduct(long id);

    List<Product> getListProductByCategory(long id);
    List<Product> getListProductByUser(long id);

    List<Product> getListByPriceRange(long id,int min, int max);

    List<Product> searchProduct(String keyword);

    Product getProduct(long id);

    Product createProduct(CreateProductRequest request);

    Product updateProduct(long id, CreateProductRequest request);
    Product updateProductgiamgia(long id, CreateProductRequest request);

    void deleteProduct(long id);
    
    void updateProductQuantity(long id, int quantity ,int quantitybuy);
   
}
