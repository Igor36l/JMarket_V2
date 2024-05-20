package com.bigproject.customerapp.client;

import com.bigproject.customerapp.entity.FavouriteProduct;
import io.micrometer.observation.ObservationFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FavouriteProductsClient {
    Mono<FavouriteProduct> findFavouriteProductByProductId(int id);

    Mono<FavouriteProduct> addProductsToFavourites(int productId);

    Mono<Void> removeProductFromFavourites(int productId);

    Flux<FavouriteProduct> findFavouriteProducts();
}
