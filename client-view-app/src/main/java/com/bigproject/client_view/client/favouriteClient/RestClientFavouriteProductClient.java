package com.bigproject.client_view.client.favouriteClient;

import com.bigproject.client_view.client.payload.FavouriteProductPayload;
import com.bigproject.client_view.entity.FavouriteProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class RestClientFavouriteProductClient implements FavouriteProductClient {

    private final RestClient restClient;

    @Override
    public FavouriteProduct[] findFavouriteProducts() {
        return this.restClient.get()
                .uri("/feedback-api/favourite-products")
                .retrieve()
                .body(FavouriteProduct[].class);
    }

    @Override
    public FavouriteProductPayload findFavouriteProductByProductId(int id) {
        return this.restClient.get()
                    .uri("feedback-api/favourite-products/by-product-id/{productId:\\d+}", id)
                    .retrieve()
                    .body(FavouriteProductPayload.class);

    }

    @Override
    public FavouriteProductPayload addProductsToFavourites(Integer productId) {
        return this.restClient.post()
                .uri("feedback-api/favourite-products")
                .body(new FavouriteProductPayload(productId))
                .retrieve()
                .body(FavouriteProductPayload.class);
    }

    @Override
    public void removeProductsToFavourites(int id) {
         this.restClient.delete()
                .uri("/feedback-api/favourite-products/by-product-id/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
