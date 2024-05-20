package com.bigproject.customerapp.client;

import com.bigproject.customerapp.client.exception.ClientBadRequestException;
import com.bigproject.customerapp.client.payload.NewFavouriteProductPayload;
import com.bigproject.customerapp.entity.FavouriteProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class WebClientFavouriteProductsClient implements FavouriteProductsClient {

    private final WebClient webClient;

    @Override
    public Flux<FavouriteProduct> findFavouriteProducts() {
        return this.webClient.get()
                .uri("/feedback-api/favourite-products")
                .retrieve()
                .bodyToFlux(FavouriteProduct.class);
    }

    @Override
    public Mono<FavouriteProduct> findFavouriteProductByProductId(int id) {
        return this.webClient.get()
                .uri("/feedback-api/favourite-products/by-product-id/{id}", id)
                .retrieve()
                .bodyToMono(FavouriteProduct.class)
                .onErrorComplete(WebClientResponseException.NotFound.class);
    }

    @Override
    public Mono<FavouriteProduct> addProductsToFavourites(int productId) {
        return webClient.post()
                .uri("/feedback-api/favourite-products")
                .bodyValue(new NewFavouriteProductPayload(productId))
                .retrieve()
                .bodyToMono(FavouriteProduct.class)
                .onErrorMap(WebClientResponseException.BadRequest.class,
                        exception -> new ClientBadRequestException(exception,
                                ((List<String>) exception.getResponseBodyAs(ProblemDetail.class)
                                        .getProperties().get("errors"))));
    }

    @Override
    public Mono<Void> removeProductFromFavourites(int productId) {
        return webClient.delete()
                .uri("/feedback-api/favourite-products/by-product-id/{id}", productId)
                .retrieve()
                .toBodilessEntity()
                .then();
    }


}
