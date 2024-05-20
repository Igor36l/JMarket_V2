package com.bigproject.feedback.service;

import com.bigproject.feedback.entity.FavouriteProduct;
import io.micrometer.observation.ObservationFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FavouriteProductsService {

    Mono<FavouriteProduct> addProductsToFavourites(int productId, String userId);

    Mono<Void> removeProductFromFavourites(int productId, String userId);

    Mono<FavouriteProduct> findFavouriteProductByProduct(int productId, String userId);

    Flux<FavouriteProduct> findFavouriteProducts(String userId);
}
