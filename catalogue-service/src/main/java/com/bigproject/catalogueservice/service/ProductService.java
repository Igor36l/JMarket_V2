package com.bigproject.catalogueservice.service;

import com.bigproject.catalogueservice.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Iterable<Product> findAllProducts(String filter);


    Product createProduct(String title, String details, String imageFileName);

    Optional<Product> findProduct(int productId);


    void updateProduct(Integer id, String title, String details);

    void deleteProduct(Integer id);
}
