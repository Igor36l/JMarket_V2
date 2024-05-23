package com.bigproject.manager.client.productClient;

import com.bigproject.manager.controllers.payload.VendorPayload;
import com.bigproject.manager.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRestClient {

    List<Product>findAllProducts(String filter);

    Product createProduct(String title, String details, String imageFileName, String ownerProduct);

    Optional<Product> findProduct(int productId);

    void updateProduct(int productId, String title, String details);

    void deleteProduct(int productId);


}
