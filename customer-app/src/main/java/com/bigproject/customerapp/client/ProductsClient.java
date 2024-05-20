package com.bigproject.customerapp.client;

import com.bigproject.customerapp.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductsClient {

    Flux<Product> findAllProducts(String filter);

    Mono<Product> findProduct(int id);
}
