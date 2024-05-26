package com.bigproject.client_view.client.reviewClient;

import com.bigproject.client_view.controllers.payload.NewProductReviewPayload;

public interface ReviewProductsClient {

    NewProductReviewPayload createProductReview(Integer productId, Integer rating, String review);

    NewProductReviewPayload[] findProductReviewsByProductId(int productId);
}
