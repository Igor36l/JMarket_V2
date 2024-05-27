package com.bigproject.catalogueservice.service;

import com.bigproject.catalogueservice.entity.Product;
import com.bigproject.catalogueservice.entity.Vendor;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Iterable<Product> findAllProducts(String filter);


    Product createProduct(String title, String details, String imageFileName, Vendor ownerProduct, Integer price);

    Optional<Product> findProduct(Integer productId);

    void updateProduct(Integer id, String title, String details);

    void updateProduct(Integer id, Double averageRating);

    void deleteProduct(Integer id);
}
