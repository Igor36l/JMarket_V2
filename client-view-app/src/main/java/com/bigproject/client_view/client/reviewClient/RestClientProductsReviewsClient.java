package com.bigproject.client_view.client.reviewClient;

import com.bigproject.client_view.client.payload.ProductReviewPayload;
import com.bigproject.client_view.controllers.payload.NewProductReviewPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class RestClientProductsReviewsClient implements ReviewProductsClient {

    private final RestClient restClient;

    @Override
    public NewProductReviewPayload createProductReview(Integer productId, Integer rating, String review) {
        return restClient.post()
                .uri("/feedback-api/product-reviews")
                .body(new ProductReviewPayload(productId, rating, review))
                .retrieve()
                .body(NewProductReviewPayload.class);
    }

    @Override
    public NewProductReviewPayload[] findProductReviewsByProductId(int productId) {
        return restClient.get()
                .uri("/feedback-api/product-reviews/by-product-id/{productId}",productId)
                .retrieve()
                .body(NewProductReviewPayload[].class);
    }
}
